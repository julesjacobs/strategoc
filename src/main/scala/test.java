import org.strategoxt.stratego_lib.*;import org.strategoxt.stratego_lib.*;import org.strategoxt.lang.*;import org.spoofax.interpreter.terms.*;import static org.strategoxt.lang.Term.*;import org.spoofax.interpreter.library.AbstractPrimitive;
import java.util.ArrayList;
import java.lang.ref.WeakReference;
@SuppressWarnings("all")public class test{
    protected static final  boolean TRACES_ENABLED=true;
    protected static  ITermFactory constantFactory;
    private static  WeakReference<Context> initedContext;
    private static  boolean isIniting;
    protected static  IStrategoTerm constSuc0;
    protected static  IStrategoTerm constZero0;
    public static  IStrategoConstructor _consConc_2;
    public static  IStrategoConstructor _consNone_0;
    public static  IStrategoConstructor _consSome_1;
    public static  IStrategoConstructor _consPow_2;
    public static  IStrategoConstructor _consMul_2;
    public static  IStrategoConstructor _consAdd_2;
    public static  IStrategoConstructor _consSuc_1;
    public static  IStrategoConstructor _consZero_0;
    public static Context init(Context context){
        synchronized(test.class){
            if(isIniting)return null;
            try{
                isIniting=true;
                ITermFactory termFactory=context.getFactory();
                if(constantFactory==null){
                    initConstructors(termFactory);
                    initConstants(termFactory);
                }
                if(initedContext==null||initedContext.get()!=context){
                    org.strategoxt.stratego_lib.Main.init(context);
                    org.strategoxt.stratego_lib.Main.init(context);
                    context.registerComponent("test");
                }
                initedContext=new WeakReference<Context>(context);
                constantFactory=termFactory;
            }
            finally{
                isIniting=false;
            }
            return context;
        }
    }
    public static Context init(){
        return init(new Context());
    }
    public static void main(String args[]){
        Context context=init();
        context.setStandAlone(true);
        try{
            IStrategoTerm result;
            try{
                result=context.invokeStrategyCLI(main_0_0.instance,"test",args);
            }
            finally{
                context.getIOAgent().closeAllFiles();
            }
            if(result==null){
                System.err.println("test"+(TRACES_ENABLED?": rewriting failed, trace:":": rewriting failed"));
                context.printStackTrace();
                context.setStandAlone(false);
                System.exit(1);
            }
            else {
                System.out.println(result);
                context.setStandAlone(false);
                System.exit(0);
            }
        }
        catch(StrategoErrorExit exit){
            context.setStandAlone(false);
            System.err.println(exit.getLocalizedMessage());
            System.exit(exit.getValue());
        }
        catch(StrategoExit exit){
            context.setStandAlone(false);
            System.exit(exit.getValue());
        }
    }
    public static IStrategoTerm mainNoExit(String...args)throws StrategoExit{
        return mainNoExit(new Context(),args);
    }
    public static IStrategoTerm mainNoExit(Context context,String...args)throws StrategoExit{
        try{
            init(context);
            return context.invokeStrategyCLI(main_0_0.instance,"test",args);
        }
        finally{
            context.getIOAgent().closeAllFiles();
        }
    }
    public static Strategy getMainStrategy(){
        return main_0_0.instance;
    }
    public static void initConstructors(ITermFactory termFactory){
        _consConc_2=termFactory.makeConstructor("Conc",2);
        _consNone_0=termFactory.makeConstructor("None",0);
        _consSome_1=termFactory.makeConstructor("Some",1);
        _consPow_2=termFactory.makeConstructor("Pow",2);
        _consMul_2=termFactory.makeConstructor("Mul",2);
        _consAdd_2=termFactory.makeConstructor("Add",2);
        _consSuc_1=termFactory.makeConstructor("Suc",1);
        _consZero_0=termFactory.makeConstructor("Zero",0);
    }
    public static void initConstants(ITermFactory termFactory){
        constZero0=termFactory.makeAppl(test._consZero_0,NO_TERMS);
        constSuc0=termFactory.makeAppl(test._consSuc_1,new IStrategoTerm[]{test.constZero0});
    }
    @SuppressWarnings("all")public static class main_0_0 extends Strategy{
        public static  main_0_0 instance=new main_0_0();
        @Override public IStrategoTerm invoke(Context context,IStrategoTerm term){
            context.push("main_0_0");
            Fail0:{
                term=io_wrap_1_0.instance.invoke(context,term,eval_0_0.instance);
                if(term==null)break Fail0;
                context.popOnSuccess();
                if(true)return term;
            }
            context.popOnFailure();
            return null;
        }
    }
    @SuppressWarnings("all")public static class eval_0_0 extends Strategy{
        public static  eval_0_0 instance=new eval_0_0();
        @Override public IStrategoTerm invoke(Context context,IStrategoTerm term){
            context.push("eval_0_0");
            Fail1:{
                for(int i=0;i<10;i++) {
                    long startTime = System.currentTimeMillis();
                    System.out.println("Started...");
                    bottomup_1_00.instance.invoke(context, term, inn_fusion00.instance);
                    long endTime = System.currentTimeMillis();
                    System.out.println("Total execution time: " + (endTime - startTime) + "ms");
                }
                term = bottomup_1_00.instance.invoke(context, term, inn_fusion00.instance);
                System.exit(0);
                if(term==null)break Fail1;
                context.popOnSuccess();
                if(true)return term;
            }
            context.popOnFailure();
            return null;
        }
    }
    @SuppressWarnings("all")private static final class inn_fusion00 extends Strategy{
        public static final  inn_fusion00 instance=new inn_fusion00();
        @Override public IStrategoTerm invoke(Context context,IStrategoTerm term){
            ITermFactory termFactory=context.getFactory();
            Fail2:{
                IStrategoTerm term0=term;
                Success0:{
                    Fail3:{
                        IStrategoTerm term1=term;
                        IStrategoConstructor cons0=term.getTermType()==IStrategoTerm.APPL?((IStrategoAppl)term).getConstructor():null;
                        Success1:{
                            if(cons0==test._consAdd_2){
                                Fail4:{
                                    IStrategoTerm x000=null;
                                    IStrategoTerm arg0=term.getSubterm(0);
                                    if(arg0.getTermType()!=IStrategoTerm.APPL||test._consZero_0!=((IStrategoAppl)arg0).getConstructor())break Fail4;
                                    x000=term.getSubterm(1);
                                    term=x000;
                                    if(true)break Success1;
                                }
                                term=term1;
                            }
                            Success2:{
                                if(cons0==test._consAdd_2){
                                    Fail5:{
                                        IStrategoTerm x130=null;
                                        x130=term.getSubterm(0);
                                        IStrategoTerm arg1=term.getSubterm(1);
                                        if(arg1.getTermType()!=IStrategoTerm.APPL||test._consZero_0!=((IStrategoAppl)arg1).getConstructor())break Fail5;
                                        term=x130;
                                        if(true)break Success2;
                                    }
                                    term=term1;
                                }
                                Success3:{
                                    if(cons0==test._consMul_2){
                                        Fail6:{
                                            IStrategoTerm arg2=term.getSubterm(0);
                                            if(arg2.getTermType()!=IStrategoTerm.APPL||test._consZero_0!=((IStrategoAppl)arg2).getConstructor())break Fail6;
                                            term=test.constZero0;
                                            if(true)break Success3;
                                        }
                                        term=term1;
                                    }
                                    Success4:{
                                        if(cons0==test._consMul_2){
                                            Fail7:{
                                                IStrategoTerm arg3=term.getSubterm(1);
                                                if(arg3.getTermType()!=IStrategoTerm.APPL||test._consZero_0!=((IStrategoAppl)arg3).getConstructor())break Fail7;
                                                term=test.constZero0;
                                                if(true)break Success4;
                                            }
                                            term=term1;
                                        }
                                        Success5:{
                                            if(cons0==test._consAdd_2){
                                                Fail8:{
                                                    IStrategoTerm x400=null;
                                                    IStrategoTerm y000=null;
                                                    IStrategoTerm arg4=term.getSubterm(0);
                                                    if(arg4.getTermType()!=IStrategoTerm.APPL||test._consSuc_1!=((IStrategoAppl)arg4).getConstructor())break Fail8;
                                                    x400=arg4.getSubterm(0);
                                                    y000=term.getSubterm(1);
                                                    term=termFactory.makeAppl(test._consAdd_2,new IStrategoTerm[]{x400,y000});
                                                    term=this.invoke(context,term);
                                                    if(term==null)break Fail8;
                                                    term=termFactory.makeAppl(test._consSuc_1,new IStrategoTerm[]{term});
                                                    if(true)break Success5;
                                                }
                                                term=term1;
                                            }
                                            Success6:{
                                                if(cons0==test._consAdd_2){
                                                    Fail9:{
                                                        IStrategoTerm x500=null;
                                                        IStrategoTerm y100=null;
                                                        x500=term.getSubterm(0);
                                                        IStrategoTerm arg5=term.getSubterm(1);
                                                        if(arg5.getTermType()!=IStrategoTerm.APPL||test._consSuc_1!=((IStrategoAppl)arg5).getConstructor())break Fail9;
                                                        y100=arg5.getSubterm(0);
                                                        term=termFactory.makeAppl(test._consAdd_2,new IStrategoTerm[]{x500,y100});
                                                        term=this.invoke(context,term);
                                                        if(term==null)break Fail9;
                                                        term=termFactory.makeAppl(test._consSuc_1,new IStrategoTerm[]{term});
                                                        if(true)break Success6;
                                                    }
                                                    term=term1;
                                                }
                                                Success7:{
                                                    if(cons0==test._consMul_2){
                                                        Fail10:{
                                                            IStrategoTerm x600=null;
                                                            IStrategoTerm y200=null;
                                                            IStrategoTerm arg6=term.getSubterm(0);
                                                            if(arg6.getTermType()!=IStrategoTerm.APPL||test._consSuc_1!=((IStrategoAppl)arg6).getConstructor())break Fail10;
                                                            x600=arg6.getSubterm(0);
                                                            y200=term.getSubterm(1);
                                                            term=termFactory.makeAppl(test._consMul_2,new IStrategoTerm[]{x600,y200});
                                                            term=this.invoke(context,term);
                                                            if(term==null)break Fail10;
                                                            term=termFactory.makeAppl(test._consAdd_2,new IStrategoTerm[]{term,y200});
                                                            term=this.invoke(context,term);
                                                            if(term==null)break Fail10;
                                                            if(true)break Success7;
                                                        }
                                                        term=term1;
                                                    }
                                                    Success8:{
                                                        if(cons0==test._consMul_2){
                                                            Fail11:{
                                                                IStrategoTerm x700=null;
                                                                IStrategoTerm y300=null;
                                                                x700=term.getSubterm(0);
                                                                IStrategoTerm arg7=term.getSubterm(1);
                                                                if(arg7.getTermType()!=IStrategoTerm.APPL||test._consSuc_1!=((IStrategoAppl)arg7).getConstructor())break Fail11;
                                                                y300=arg7.getSubterm(0);
                                                                term=termFactory.makeAppl(test._consMul_2,new IStrategoTerm[]{x700,y300});
                                                                term=this.invoke(context,term);
                                                                if(term==null)break Fail11;
                                                                term=termFactory.makeAppl(test._consAdd_2,new IStrategoTerm[]{x700,term});
                                                                term=this.invoke(context,term);
                                                                if(term==null)break Fail11;
                                                                if(true)break Success8;
                                                            }
                                                            term=term1;
                                                        }
                                                        Success9:{
                                                            if(cons0==test._consMul_2){
                                                                Fail12:{
                                                                    IStrategoTerm x800=null;
                                                                    IStrategoTerm y400=null;
                                                                    IStrategoTerm z000=null;
                                                                    IStrategoTerm lift_app_in_build_m40=null;
                                                                    IStrategoTerm arg8=term.getSubterm(0);
                                                                    if(arg8.getTermType()!=IStrategoTerm.APPL||test._consAdd_2!=((IStrategoAppl)arg8).getConstructor())break Fail12;
                                                                    x800=arg8.getSubterm(0);
                                                                    y400=arg8.getSubterm(1);
                                                                    z000=term.getSubterm(1);
                                                                    term=termFactory.makeAppl(test._consMul_2,new IStrategoTerm[]{x800,z000});
                                                                    term=this.invoke(context,term);
                                                                    if(term==null)break Fail12;
                                                                    lift_app_in_build_m40=term;
                                                                    term=termFactory.makeAppl(test._consMul_2,new IStrategoTerm[]{y400,z000});
                                                                    term=this.invoke(context,term);
                                                                    if(term==null)break Fail12;
                                                                    term=termFactory.makeAppl(test._consAdd_2,new IStrategoTerm[]{lift_app_in_build_m40,term});
                                                                    term=this.invoke(context,term);
                                                                    if(term==null)break Fail12;
                                                                    if(true)break Success9;
                                                                }
                                                                term=term1;
                                                            }
                                                            Success10:{
                                                                if(cons0==test._consMul_2){
                                                                    Fail13:{
                                                                        IStrategoTerm x900=null;
                                                                        IStrategoTerm y500=null;
                                                                        IStrategoTerm z100=null;
                                                                        IStrategoTerm lift_app_in_build_m60=null;
                                                                        z100=term.getSubterm(0);
                                                                        IStrategoTerm arg9=term.getSubterm(1);
                                                                        if(arg9.getTermType()!=IStrategoTerm.APPL||test._consAdd_2!=((IStrategoAppl)arg9).getConstructor())break Fail13;
                                                                        x900=arg9.getSubterm(0);
                                                                        y500=arg9.getSubterm(1);
                                                                        term=termFactory.makeAppl(test._consMul_2,new IStrategoTerm[]{x900,z100});
                                                                        term=this.invoke(context,term);
                                                                        if(term==null)break Fail13;
                                                                        lift_app_in_build_m60=term;
                                                                        term=termFactory.makeAppl(test._consMul_2,new IStrategoTerm[]{y500,z100});
                                                                        term=this.invoke(context,term);
                                                                        if(term==null)break Fail13;
                                                                        term=termFactory.makeAppl(test._consAdd_2,new IStrategoTerm[]{lift_app_in_build_m60,term});
                                                                        term=this.invoke(context,term);
                                                                        if(term==null)break Fail13;
                                                                        if(true)break Success10;
                                                                    }
                                                                    term=term1;
                                                                }
                                                                Success11:{
                                                                    if(cons0==test._consMul_2){
                                                                        Fail14:{
                                                                            IStrategoTerm x1000=null;
                                                                            IStrategoTerm y600=null;
                                                                            IStrategoTerm z200=null;
                                                                            IStrategoTerm arg10=term.getSubterm(0);
                                                                            if(arg10.getTermType()!=IStrategoTerm.APPL||test._consMul_2!=((IStrategoAppl)arg10).getConstructor())break Fail14;
                                                                            x1000=arg10.getSubterm(0);
                                                                            y600=arg10.getSubterm(1);
                                                                            z200=term.getSubterm(1);
                                                                            term=termFactory.makeAppl(test._consMul_2,new IStrategoTerm[]{y600,z200});
                                                                            term=this.invoke(context,term);
                                                                            if(term==null)break Fail14;
                                                                            term=termFactory.makeAppl(test._consMul_2,new IStrategoTerm[]{x1000,term});
                                                                            term=this.invoke(context,term);
                                                                            if(term==null)break Fail14;
                                                                            if(true)break Success11;
                                                                        }
                                                                        term=term1;
                                                                    }
                                                                    Success12:{
                                                                        if(cons0==test._consPow_2){
                                                                            Fail15:{
                                                                                IStrategoTerm x1100=null;
                                                                                IStrategoTerm y700=null;
                                                                                x1100=term.getSubterm(0);
                                                                                IStrategoTerm arg11=term.getSubterm(1);
                                                                                if(arg11.getTermType()!=IStrategoTerm.APPL||test._consSuc_1!=((IStrategoAppl)arg11).getConstructor())break Fail15;
                                                                                y700=arg11.getSubterm(0);
                                                                                term=termFactory.makeAppl(test._consPow_2,new IStrategoTerm[]{x1100,y700});
                                                                                term=this.invoke(context,term);
                                                                                if(term==null)break Fail15;
                                                                                term=termFactory.makeAppl(test._consMul_2,new IStrategoTerm[]{x1100,term});
                                                                                term=this.invoke(context,term);
                                                                                if(term==null)break Fail15;
                                                                                if(true)break Success12;
                                                                            }
                                                                            term=term1;
                                                                        }
                                                                        if(cons0==test._consPow_2){
                                                                            IStrategoTerm arg12=term.getSubterm(1);
                                                                            if(arg12.getTermType()!=IStrategoTerm.APPL||test._consZero_0!=((IStrategoAppl)arg12).getConstructor())break Fail3;
                                                                            term=test.constSuc0;
                                                                        }
                                                                        else {
                                                                            break Fail3;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if(true)break Success0;
                    }
                    term=term0;
                }
                if(true)return term;
            }
            return null;
        }
    }
    @SuppressWarnings("all")private static final class bottomup_1_00 extends Strategy{
        public static final  bottomup_1_00 instance=new bottomup_1_00();
        @Override public IStrategoTerm invoke(Context context,IStrategoTerm term,Strategy s3){
            Fail16:{
                eval_0_0$lifted0 eval_0_0$lifted00=new eval_0_0$lifted0();
                eval_0_0$lifted00.s3=s3;
                term=SRTS_all.instance.invoke(context,term,eval_0_0$lifted00);
                if(term==null)break Fail16;
                term=s3.invoke(context,term);
                if(term==null)break Fail16;
                if(true)return term;
            }
            return null;
        }
    }
    @SuppressWarnings("all")private static final class eval_0_0$lifted0 extends Strategy{
        Strategy s3;
        @Override public IStrategoTerm invoke(Context context,IStrategoTerm term){
            Fail17:{
                term=bottomup_1_00.instance.invoke(context,term,s3);
                if(term==null)break Fail17;
                if(true)return term;
            }
            return null;
        }
    }
    public static void registerInterop(org.spoofax.interpreter.core.IContext context,Context compiledContext){
        new InteropRegisterer().registerLazy(context,compiledContext,InteropRegisterer.class.getClassLoader());
    }
    @SuppressWarnings("unused")public static class InteropRegisterer extends org.strategoxt.lang.InteropRegisterer{
        @Override public void register(org.spoofax.interpreter.core.IContext context,Context compiledContext){
            register(context,compiledContext,context.getVarScope());
        }
        @Override public void registerLazy(org.spoofax.interpreter.core.IContext context,Context compiledContext,ClassLoader classLoader){
            registerLazy(context,compiledContext,classLoader,context.getVarScope());
        }
        private void register(org.spoofax.interpreter.core.IContext context,Context compiledContext,org.spoofax.interpreter.core.VarScope varScope){
            compiledContext.registerComponent("test");
            test.init(compiledContext);
            varScope.addSVar("main_0_0",new InteropSDefT(main_0_0.instance,context));
            varScope.addSVar("eval_0_0",new InteropSDefT(eval_0_0.instance,context));
        }
        private void registerLazy(org.spoofax.interpreter.core.IContext context,Context compiledContext,ClassLoader classLoader,org.spoofax.interpreter.core.VarScope varScope){
            compiledContext.registerComponent("test");
            test.init(compiledContext);
            varScope.addSVar("main_0_0",new InteropSDefT(classLoader,"test$main_0_0",context));
            varScope.addSVar("eval_0_0",new InteropSDefT(classLoader,"test$eval_0_0",context));
        }
    }
}
