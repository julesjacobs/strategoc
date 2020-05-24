object compiler {

  abstract class Term

  case class Constr(name: String, args: List[Term]) extends Term

  case class Var(name: String) extends Term

  case class Apply(f: Strat, arg: Term) extends Term

  class Strat {
    def mif(a:Strat, b:Strat):Strat = (a,b) match {
      case (Fail(),Fail()) => Fail()
      case (Id(),Fail()) => this
      case (_,Fail()) => If(this,a,b)
      case (_,_) => if(this.cantFail()) this.mif(a,Fail()) else If(this,a,b)
    }

    def cantFail():Boolean = this match {
      case Id() => true
      case Put(t) => cantFailT(t)
      case _ => false
    }

    def cantFailT(t: Term):Boolean = t match {
      case Constr(_, args) => args.forall(cantFailT)
      case Var(_) => true
      case Apply(_,_) => false
    }

    def %>(that: Strat): Strat = this.mif(that, Fail())

    def +>(that: Strat): Strat = this.mif(Id(), that)
  }

  case class Id() extends Strat

  case class Fail() extends Strat

  case class SVar(name: String) extends Strat

  case class If(cond: Strat, then_s: Strat, else_s: Strat) extends Strat

  case class Call(name: String, args: List[Strat]) extends Strat

  case class Match(branches: List[(Term, Strat)], default: Strat) extends Strat

  case class Put(term: Term) extends Strat

  case class Norm(norm: Strat, body: Strat) extends Strat

  case class Let(name: String, body: Strat) extends Strat

  case class Case(name: String, constName: String, argNames: List[String], body: Strat, default: Strat) extends Strat

  type Program = Map[String, (List[String], Strat)]
  type Rules = Map[String, Strat]

  def allS(a: Strat): Strat = Call("all", List(a))

  val s: Strat = SVar("s")

  def constructors(p: Program): List[(String, Int)] = {
    val constrs = new collection.mutable.HashSet[(String, Int)]()

    def iter(x: Strat): Unit = x match {
      case Id() | Fail() | SVar(_) => ()
      case If(a, b, c) => iter(a); iter(b); iter(c)
      case Call(_, args) => args.foreach(iter)
      case Match(branches, a) => for ((t, b) <- branches) {
        iterT(t); iter(b)
      }; iter(a)
      case Put(t) => iterT(t)
      case Norm(a, b) => iter(a); iter(b)
    }

    def iterT(x: Term): Unit = x match {
      case Constr(name, args) => constrs.add((name, args.length)); args.foreach(iterT)
      case Var(_) => ()
      case Apply(a, t) => iter(a); iterT(t)
    }

    for ((_, (_, a)) <- p) iter(a)
    List() ++ constrs
  }

  def desugarAll(p: Program): Program = {
    p + ("all" -> ((List("s"),
      Match(constructors(p).map { case (f, n) =>
        val vars = (1 to n).map(i => Var("x" + i.toString)).toList
        Constr(f, vars) -> Put(Constr(f, vars.map(v => Apply(s, v))))
      }, Fail()))))
  }

  def desugarNorm(p: Program): Program = {
    // add extra strategy argument to each rule
    // main transformed with Id()
    // this doesn't work, we need to actually create a recursive strategy
    val newrules = new collection.mutable.HashMap[String, (List[String], Strat)]
    def iter(s: Strat, norm: Strat): Strat = s match {
      case Id() | Fail() | SVar(_) => s
      case If(a, b, c) => If(iter(a,norm), iter(b,norm), iter(c,norm))
      case Call(name, args) => Call(name, args.map(iter(_,norm)) :+ norm)
      case Match(branches, a) =>
        Match(branches.map{case (t,b) => (t, iter(b,norm))}, iter(a,norm))
      case Put(t) => Put(iterT(t,norm))
      case Norm(Call(name, List()), a) =>
        val rulename = gensym()
        newrules(rulename) = (List(),Call(name, List(Call(rulename,List()))))
        iter(a, Call(rulename,List()))
    }

    def iterT(x: Term, norm: Strat): Term = x match {
      case Constr(name, args) => Apply(norm, Constr(name, args.map(iterT(_,norm))))
      case Var(_) => x
      case Apply(a, t) => Apply(iter(a,norm), iterT(t,norm))
    }

    val rules = new collection.mutable.HashMap[String, (List[String], Strat)]()
    for((n,(args,s)) <- p){
      if(n == "main") rules("main") = (args, iter(s,Id()))
      else rules(n) = (args :+ "norm", iter(s,SVar("norm")))
    }
    rules.toMap ++ newrules
  }

  var lastvar = 0

  def gensym(): String = {
    lastvar += 1
    "x" + lastvar
  }

  def desugarCall(p: Program): Rules = {
    val ruleNames = new collection.mutable.HashMap[Strat, String]()
    val rules = new collection.mutable.HashMap[String, Strat]()

    def compile(s: Strat): Strat = {
      s match {
        case SVar(_) => s
//        case Call(_, _) => elimCalls(s)
        case s =>
          if (!ruleNames.contains(s)) {
            val v = gensym()
            ruleNames(s) = v
            rules(v) = elimCalls(s)
          }
          SVar(ruleNames(s))
      }
    }

    def elimCalls(s: Strat): Strat = {
      s match {
        case Id() | Fail() | SVar(_) => s
        case If(a, b, c) => If(elimCalls(a), elimCalls(b), elimCalls(c))
        case Call(f, args) =>
          if (!p.contains(f)) throw new Exception("Rule " + f + " not found in " + s.toString)
          val args2 = args.map(compile)
          val (argNames, body) = p(f)
          if (args2.length != argNames.length) throw new Exception("The rule " + f + " has different number of arguments than call " + s.toString)
          val env: Map[String, Strat] = argNames.zip(args2).toMap

          def subst(s: Strat): Strat = {
            s match {
              case Id() | Fail() => s
              case If(a, b, c) => If(subst(a), subst(b), subst(c))
              case SVar(x) => if (env.contains(x)) env(x) else s
              case Call(f, args) => Call(f, args.map(subst))
              case Match(branches, a) => Match(branches.map { case (pat, body) => (pat, subst(body)) }, subst(a))
              case Put(t) => Put(substT(t))
              case Norm(_, _) => throw new Exception("Norm should have been desugared already.")
            }
          }

          def substT(x: Term): Term = x match {
            case Constr(name, args) => Constr(name, args.map(substT))
            case Var(_) => x
            case Apply(a, t) => Apply(subst(a), substT(t))
          }

          compile(subst(body))
        case Match(branches, a) => Match(branches.map { case (pat, body) => (pat, elimCalls(body)) }, elimCalls(a))
        case Put(t) => Put(elimCallsT(t))
        case Norm(_, _) => throw new Exception("Norm should have been desugared already.")
      }
    }

    def elimCallsT(x: Term): Term = x match {
      case Constr(name, args) => Constr(name, args.map(elimCallsT))
      case Var(_) => x
      case Apply(a, t) => Apply(elimCalls(a), elimCallsT(t))
    }

    val main = compile(Call("main", List()))
    Map() ++ rules + ("main" -> main)
  }

  type Clause = (Map[String, Term], Strat)

  def desugarMatch(p: Rules): Rules = {
    def genMatch(pats: List[Clause], default: Strat): Strat = {
      if (pats.isEmpty) return default
      // substitute Var equations
      val newpats = pats.map { pat =>
        val env = new collection.mutable.HashMap[String, String]()
        val pats2 = new collection.mutable.HashMap[String, Term]()
        for ((v, p) <- pat._1) p match {
          case Var(v2) => env(v2) = v
          case _ => pats2(v) = p
        }

        def subst(s: Strat): Strat = s match {
          case Id() | Fail() | SVar(_) => s
          case If(a, b, c) => If(subst(a), subst(b), subst(c))
          case Call(f, args) => Call(f, args.map(subst))
          case Match(branches, a) => Match(branches.map { case (pat, body) => (pat, subst(body)) }, subst(a))
          case Put(t) => Put(substT(t))
        }

        def substT(x: Term): Term = x match {
          case Constr(name, args) => Constr(name, args.map(substT))
          case Var(a) => if (env.contains(a)) Var(env(a)) else x
          case Apply(a, t) => Apply(subst(a), substT(t))
        }

        (pats2.toMap, subst(pat._2))
      }
      if (newpats.head._1.isEmpty) return newpats.head._2 +> genMatch(newpats.tail, default)
      // select the variable that causes least code duplication
      val (firstpat, _) = newpats.head
      val selected = firstpat.keys.maxBy(n => newpats.count(_._1.contains(n)))
      // filter the patterns matching firstpat(selected), and those not matching
      firstpat(selected) match {
        case Constr(constrName, args) =>
          val argNames = args.map(_ => gensym())
          val thens = collection.mutable.ListBuffer[Clause]()
          val elses = collection.mutable.ListBuffer[Clause]()
          for((eqns,bod) <- newpats){
            if(eqns.contains(selected)){
              eqns(selected) match {
                case Constr(constrName2, args2) =>
                  if (constrName == constrName2 && args.length == args2.length) {
                    // add to then branch
                    var neweqns = eqns - selected
                    for((argName,arg) <- argNames.zip(args2)){
                      neweqns += (argName -> arg)
                    }
                    thens += ((neweqns,bod))
                  } else {
                    // add to else branch
                    elses += ((eqns,bod))
                  }
                case _ => throw new Exception("Should be a constructor.")
              }
            }else{
              // add to both branches
              thens += ((eqns,bod))
              elses += ((eqns,bod))
            }
          }
          val thenbranch = genMatch(thens.toList, default)
          val elsebranch = genMatch(elses.toList, default)
          Case(selected, constrName, argNames, thenbranch, elsebranch)
        case _ => throw new Exception("Should be a constructor.")
      }
    }

    def iter(x: Strat): Strat = x match {
      case Id() | Fail() | SVar(_) => x
      case If(a, b, c) => If(iter(a), iter(b), iter(c))
      case Call(f, args) => Call(f, args.map(iter))
      case Match(branches, a) =>
        val name = gensym()
        Let(name, genMatch(branches.map{ case (t,s) => (Map(name -> t), s) }, a))
      case Put(t) => Put(iterT(t))
      case Norm(_, _) => throw new Exception("Norm should have been desugared already.")
    }

    def iterT(x: Term): Term = x match {
      case Constr(name, args) => Constr(name, args.map(iterT))
      case Var(_) => x
      case Apply(a, t) => Apply(iter(a), iterT(t))
    }

    p.view.mapValues(iter).toMap
  }

  def inline(p: Rules): Rules ={
    val useCounts = new collection.mutable.HashMap[String,Int];
    def incr(s: String):Unit ={
      if(useCounts.contains(s)) useCounts(s) += 1
      else useCounts(s) = 1
    }
    def iter(s: Strat):Unit = s match {
      case Id() | Fail() => ()
      case SVar(x) => incr(x)
      case If(a,b,c) => iter(a); iter(b); iter(c)
      case Put(t) => iterT(t)
      case Let(_,a) => iter(a)
      case Case(_, _, _, a, b) => iter(a); iter(b)
    }
    def iterT(t: Term):Unit = t match {
      case Var(_) => ()
      case Apply(s,t) => iter(s); iterT(t)
      case Constr(_, ts) => ts.foreach(iterT)
    }

    for((_,s) <- p) iter(s)

    val rules = new collection.mutable.HashMap[String,Strat]()

    def subst(s: Strat):Strat = s match {
      case Id() | Fail() => s
      case SVar(x) => if(useCounts(x) == 1) subst(p(x)) else { addRule(x); s }
      case If(a,b,c) => subst(a).mif(subst(b), subst(c))
      case Put(t) => Put(substT(t))
      case Let(x,a) => Let(x,subst(a))
      case Case(x, y, z, a, b) => Case(x,y,z,subst(a),subst(b))
    }
    def substT(t: Term):Term = t match {
      case Var(_) => t
      case Apply(s,t) => Apply(subst(s), substT(t))
      case Constr(x, ts) => Constr(x, ts.map(substT))
    }
    def addRule(s: String):Unit ={
      if(!rules.contains(s)){
        rules(s) = Id()
        rules(s) = subst(p(s))
      }
    }
    addRule("main")
    rules.toMap
  }

  def codegen(p: Rules): String = {
    val out = new collection.mutable.StringBuilder()
    var indentN = 0
    def outln(s: String): Unit ={
      out ++= "  " * indentN + s + "\n"
    }
    def indent(head: String, f: => Unit): Unit ={
      outln(s"$head{")
      indentN += 1
      f
      indentN -= 1
      out ++= "  " * indentN + "}"
    }
    def indentln(head: String, f: => Unit): Unit ={
      indent(head, { f })
      out ++= "\n"
    }
    def block(f: String => Unit):Unit ={
      val label = gensym()
      indentln(s"$label: ", {
        f(s"break $label;")
      })
    }
    def break(label: String): String ={
      outln(label)
      "null"
    }

    val constrs = new collection.mutable.HashSet[(String,Int)]

    def gen(s: Strat, in: String, fail: String): String ={
      s match {
        case Id() => in
        case Fail() => break(fail)
        case SVar(name) =>
          val out = gensym()
          outln(s"Term $out = $name($in);")
          outln(s"if($out == null) $fail")
          out
        case If(a,b,Fail()) =>
          val av = gen(a, in, fail)
          gen(b, av, fail)
        case If(a,b,c) =>
          val outv = gensym()
          outln(s"Term $outv;")
          block { label0 =>
            block { label1 =>
              val av = gen(a, in, label1)
              val bv = gen(b, av, fail)
              outln(s"$outv = $bv;")
              break(label0)
            }
            val cv = gen(c, in, fail)
            outln(s"$outv = $cv;")
          }
          outv
        case Put(t) => genT(t, fail)
        case Let(name, a) =>
          outln(s"Term $name = $in;")
          gen(a, in, fail)
        case Case(name, constrName, argNames, a, b) =>
          val x = gensym()
          outln(s"Term $x = null;")
          if(argNames.isEmpty && zeroArgConstrOpt && matchRefEqOpt){
            indent(s"if($name == ${constrName.toLowerCase})", {
              val r = gen(a, in, fail)
              outln(s"$x = $r;")
            })
            out ++= "else{\n"
            val r = gen(b, in, fail)
            if(r != "null") outln(s"$x = $r;")
            outln("}")
          }else{
            indent(s"if($name instanceof $constrName)", {
              val n = gensym()
              outln(s"$constrName $n = ($constrName)$name;")
              for((an,i) <- argNames.zipWithIndex){
                outln(s"Term $an = $n.f$i;")
              }
              val r = gen(a, in, fail)
              outln(s"$x = $r;")
            })
            out ++= "else{\n"
            val r = gen(b, in, fail)
            if(r != "null") outln(s"$x = $r;")
            outln("}")
          }
          x
      }
    }

    def genT(t: Term, fail: String):String ={
      t match {
        case Constr(name,args) =>
          constrs += ((name,args.length))
          if(args.isEmpty && zeroArgConstrOpt) return name.toLowerCase
          val ys = args.map { s => genT(s, fail) }
          val r = gensym()
          outln(s"Term $r = new $name(${ys.mkString(",")});")
          r
        case Var(name) => name
        case Apply(f, arg) =>
          val x = genT(arg, fail)
          return gen(f, x, fail)
      }
    }

    indentln("class Rewriter ", {
      for((n,s) <- p){
        indentln(s"static Term $n(Term it)", {
          val ret = gen(s, "it", "return null;")
          outln(s"return $ret;")
        })
      }
      indentln("public static void main(String args[])", {
        indentln("for(int i=0; i<10; i++)", {
          outln("System.out.println(\"Started...\");");
          outln("long startTime = System.currentTimeMillis();")
          outln("main((Term)null);")
          outln("long endTime = System.currentTimeMillis();")
          outln("System.out.println(\"Total execution time: \" + (endTime - startTime) + \"ms\");");
          if(showresult){
            outln("main((Term)null).print();")
            outln("System.out.println();")
          }
        })
      })
      for((constr,n) <- constrs) if(n==0 && zeroArgConstrOpt) {
        outln(s"static final Term ${constr.toLowerCase} = new $constr();")
      }
    })

    outln("interface Term { void print(); }")

    for((constr,n) <- constrs){
      indentln(s"final class $constr implements Term ", {
        indentln(s"public $constr(${(0 until n).map("Term x" + _).mkString(", ")})",{
          for(i <- 0 until n) outln(s"this.f$i = x$i;")
        })
        for(i <- 0 until n) outln(s"public Term f$i;")
        indentln(s"public void print()",{
          outln(s"""System.out.print(\"$constr(\");""")
          for(i <- 0 until n) {
            if(i != 0) outln("System.out.print(\",\");")
            outln(s"f$i.print();")
          }
          outln(s"""System.out.print(\")\");""")
        })
      })
    }

    println(out.toString)

    val outp:String = out.toString

    import java.io.PrintWriter
    new PrintWriter("/Users/jules/IdeaProjects/strategoc/src/main/scala/Rewriter.java") { write(outp); close() }
    ""
  }

//  norm
//  match-constructor
  val zeroArgConstrOpt = true
  val matchRefEqOpt = true
  val showresult = false

  def main(args: Array[String]): Unit = {
    def tryS(a: Strat) = a +> Id()

    def bottomupS(a: Strat) = Call("bottomup", List(a))

    def innermostS(a: Strat) = Call("innermost", List(a))

    val x = Var("x")
    val y = Var("y")
    val z = Var("z")
    val xx = Constr("X",List())
    val zero = Constr("Zero", List())

    def suc(a: Term) = Constr("Suc", List(a))

    def add(a: Term, b: Term) = Constr("Add", List(a, b))

    def mul(a: Term, b: Term) = Constr("Mul", List(a, b))

    def pow(a: Term, b: Term) = Constr("Pow", List(a, b))

    def num(n: Int) = {
      var t = zero
      for (_ <- 0 until n) t = suc(t)
      t
    }

    val example = Map(
      "simpl" -> ((List(), Match(List(
        add(zero, x) -> Put(x),
        add(x,zero) -> Put(x),
        mul(zero,x) -> Put(zero),
        mul(x,zero) -> Put(zero),
        add(suc(x), y) -> Put(suc(add(x, y))),
        add(x, suc(y)) -> Put(suc(add(x, y))),
        mul(suc(x), y) -> Put(add(mul(x, y), y)),
        mul(x, suc(y)) -> Put(add(x, mul(x, y))),
        mul(add(x, y), z) -> Put(add(mul(x, z), mul(y, z))),
        mul(z, add(x, y)) -> Put(add(mul(x, z), mul(y, z))),
        mul(mul(x, y), z) -> Put(mul(x, mul(y, z))),
        pow(x, suc(y)) -> Put(mul(x, pow(x,y))),
        pow(x, zero) -> Put(num(1))
      ), Id()))),
      "copy" -> ((List(), allS(Call("copy",List())))),
      "main" -> ((List(), Put(pow(add(num(1),xx), num(18))) %> Norm(Call("simpl",List()), Call("copy",List()))))
//      "bottomup" -> ((List("s"), allS(bottomupS(s)) %> s)),
//      "innermost" -> ((List("s"), bottomupS(tryS(s %> innermostS(s))))),
//      "main" -> ((List(), Put(pow(add(num(1),xx), num(2))) %> innermostS(Call("simpl", List()))))
    )

    // vs Stratego: 23
    //Pow(Add(Suc(Zero),X), Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Zero))))))))))))))))))))))));
    //Total execution time: 7447ms

    // Mini Stratego: Total execution time: 47190ms


    val desugaredAll = desugarAll(example)
    val desugaredNorm = desugarNorm(desugaredAll)
//    val desugaredNorm = desugaredAll
    val desugaredCall = desugarCall(desugaredNorm)
    val desugaredMatch = desugarMatch(desugaredCall)
    val inlined = inline(desugaredMatch)
    pprint.pprintln(desugaredCall)

    //    pprint.pprintln(desugaredCall)
//    pprint.pprintln(inlined)
    codegen(inlined)
//    Rewriter.main(List("").toArray)
//    print(Rewriter.main(null))
//    println("-")
  }
}