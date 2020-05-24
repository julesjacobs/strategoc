import org.strategoxt.stratego_lib.*;
import org.strategoxt.lang.*;import org.spoofax.interpreter.terms.*;import static org.strategoxt.lang.Term.*;

import java.lang.ref.WeakReference;
@SuppressWarnings("all")public class testmyinnermost {
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
        synchronized(testmyinnermost.class){
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
        constZero0=termFactory.makeAppl(testmyinnermost._consZero_0,NO_TERMS);
        constSuc0=termFactory.makeAppl(testmyinnermost._consSuc_1,new IStrategoTerm[]{testmyinnermost.constZero0});
    }
    @SuppressWarnings("all")public static class $E_0_0 extends Strategy{
        public static  $E_0_0 instance=new $E_0_0();
        @Override public IStrategoTerm invoke(Context context,IStrategoTerm term){
            ITermFactory termFactory=context.getFactory();
            Fail0:{
                IStrategoTerm term0=term;
                IStrategoConstructor cons0=term.getTermType()==IStrategoTerm.APPL?((IStrategoAppl)term).getConstructor():null;
                Success0:{
                    if(cons0== testmyinnermost._consAdd_2){
                        Fail1:{
                            IStrategoTerm x0=null;
                            IStrategoTerm arg0=term.getSubterm(0);
                            if(arg0.getTermType()!=IStrategoTerm.APPL|| testmyinnermost._consZero_0!=((IStrategoAppl)arg0).getConstructor())break Fail1;
                            x0=term.getSubterm(1);
                            term=x0;
                            if(true)break Success0;
                        }
                        term=term0;
                    }
                    Success1:{
                        if(cons0== testmyinnermost._consAdd_2){
                            Fail2:{
                                IStrategoTerm x1=null;
                                x1=term.getSubterm(0);
                                IStrategoTerm arg1=term.getSubterm(1);
                                if(arg1.getTermType()!=IStrategoTerm.APPL|| testmyinnermost._consZero_0!=((IStrategoAppl)arg1).getConstructor())break Fail2;
                                term=x1;
                                if(true)break Success1;
                            }
                            term=term0;
                        }
                        Success2:{
                            if(cons0== testmyinnermost._consMul_2){
                                Fail3:{
                                    IStrategoTerm arg2=term.getSubterm(0);
                                    if(arg2.getTermType()!=IStrategoTerm.APPL|| testmyinnermost._consZero_0!=((IStrategoAppl)arg2).getConstructor())break Fail3;
                                    term= testmyinnermost.constZero0;
                                    if(true)break Success2;
                                }
                                term=term0;
                            }
                            Success3:{
                                if(cons0== testmyinnermost._consMul_2){
                                    Fail4:{
                                        IStrategoTerm arg3=term.getSubterm(1);
                                        if(arg3.getTermType()!=IStrategoTerm.APPL|| testmyinnermost._consZero_0!=((IStrategoAppl)arg3).getConstructor())break Fail4;
                                        term= testmyinnermost.constZero0;
                                        if(true)break Success3;
                                    }
                                    term=term0;
                                }
                                Success4:{
                                    if(cons0== testmyinnermost._consAdd_2){
                                        Fail5:{
                                            IStrategoTerm x4=null;
                                            IStrategoTerm y0=null;
                                            IStrategoTerm arg4=term.getSubterm(0);
                                            if(arg4.getTermType()!=IStrategoTerm.APPL|| testmyinnermost._consSuc_1!=((IStrategoAppl)arg4).getConstructor())break Fail5;
                                            x4=arg4.getSubterm(0);
                                            y0=term.getSubterm(1);
                                            term=termFactory.makeAppl(testmyinnermost._consSuc_1,new IStrategoTerm[]{termFactory.makeAppl(testmyinnermost._consAdd_2,new IStrategoTerm[]{x4,y0})});
                                            if(true)break Success4;
                                        }
                                        term=term0;
                                    }
                                    Success5:{
                                        if(cons0== testmyinnermost._consAdd_2){
                                            Fail6:{
                                                IStrategoTerm x5=null;
                                                IStrategoTerm y1=null;
                                                x5=term.getSubterm(0);
                                                IStrategoTerm arg5=term.getSubterm(1);
                                                if(arg5.getTermType()!=IStrategoTerm.APPL|| testmyinnermost._consSuc_1!=((IStrategoAppl)arg5).getConstructor())break Fail6;
                                                y1=arg5.getSubterm(0);
                                                term=termFactory.makeAppl(testmyinnermost._consSuc_1,new IStrategoTerm[]{termFactory.makeAppl(testmyinnermost._consAdd_2,new IStrategoTerm[]{x5,y1})});
                                                if(true)break Success5;
                                            }
                                            term=term0;
                                        }
                                        Success6:{
                                            if(cons0== testmyinnermost._consMul_2){
                                                Fail7:{
                                                    IStrategoTerm x6=null;
                                                    IStrategoTerm y2=null;
                                                    IStrategoTerm arg6=term.getSubterm(0);
                                                    if(arg6.getTermType()!=IStrategoTerm.APPL|| testmyinnermost._consSuc_1!=((IStrategoAppl)arg6).getConstructor())break Fail7;
                                                    x6=arg6.getSubterm(0);
                                                    y2=term.getSubterm(1);
                                                    term=termFactory.makeAppl(testmyinnermost._consAdd_2,new IStrategoTerm[]{termFactory.makeAppl(testmyinnermost._consMul_2,new IStrategoTerm[]{x6,y2}),y2});
                                                    if(true)break Success6;
                                                }
                                                term=term0;
                                            }
                                            Success7:{
                                                if(cons0== testmyinnermost._consMul_2){
                                                    Fail8:{
                                                        IStrategoTerm x7=null;
                                                        IStrategoTerm y3=null;
                                                        x7=term.getSubterm(0);
                                                        IStrategoTerm arg7=term.getSubterm(1);
                                                        if(arg7.getTermType()!=IStrategoTerm.APPL|| testmyinnermost._consSuc_1!=((IStrategoAppl)arg7).getConstructor())break Fail8;
                                                        y3=arg7.getSubterm(0);
                                                        term=termFactory.makeAppl(testmyinnermost._consAdd_2,new IStrategoTerm[]{x7,termFactory.makeAppl(testmyinnermost._consMul_2,new IStrategoTerm[]{x7,y3})});
                                                        if(true)break Success7;
                                                    }
                                                    term=term0;
                                                }
                                                Success8:{
                                                    if(cons0== testmyinnermost._consMul_2){
                                                        Fail9:{
                                                            IStrategoTerm x8=null;
                                                            IStrategoTerm y4=null;
                                                            IStrategoTerm z0=null;
                                                            IStrategoTerm arg8=term.getSubterm(0);
                                                            if(arg8.getTermType()!=IStrategoTerm.APPL|| testmyinnermost._consAdd_2!=((IStrategoAppl)arg8).getConstructor())break Fail9;
                                                            x8=arg8.getSubterm(0);
                                                            y4=arg8.getSubterm(1);
                                                            z0=term.getSubterm(1);
                                                            term=termFactory.makeAppl(testmyinnermost._consAdd_2,new IStrategoTerm[]{termFactory.makeAppl(testmyinnermost._consMul_2,new IStrategoTerm[]{x8,z0}),termFactory.makeAppl(testmyinnermost._consMul_2,new IStrategoTerm[]{y4,z0})});
                                                            if(true)break Success8;
                                                        }
                                                        term=term0;
                                                    }
                                                    Success9:{
                                                        if(cons0== testmyinnermost._consMul_2){
                                                            Fail10:{
                                                                IStrategoTerm x9=null;
                                                                IStrategoTerm y5=null;
                                                                IStrategoTerm z1=null;
                                                                z1=term.getSubterm(0);
                                                                IStrategoTerm arg9=term.getSubterm(1);
                                                                if(arg9.getTermType()!=IStrategoTerm.APPL|| testmyinnermost._consAdd_2!=((IStrategoAppl)arg9).getConstructor())break Fail10;
                                                                x9=arg9.getSubterm(0);
                                                                y5=arg9.getSubterm(1);
                                                                term=termFactory.makeAppl(testmyinnermost._consAdd_2,new IStrategoTerm[]{termFactory.makeAppl(testmyinnermost._consMul_2,new IStrategoTerm[]{x9,z1}),termFactory.makeAppl(testmyinnermost._consMul_2,new IStrategoTerm[]{y5,z1})});
                                                                if(true)break Success9;
                                                            }
                                                            term=term0;
                                                        }
                                                        Success10:{
                                                            if(cons0== testmyinnermost._consMul_2){
                                                                Fail11:{
                                                                    IStrategoTerm x10=null;
                                                                    IStrategoTerm y6=null;
                                                                    IStrategoTerm z2=null;
                                                                    IStrategoTerm arg10=term.getSubterm(0);
                                                                    if(arg10.getTermType()!=IStrategoTerm.APPL|| testmyinnermost._consMul_2!=((IStrategoAppl)arg10).getConstructor())break Fail11;
                                                                    x10=arg10.getSubterm(0);
                                                                    y6=arg10.getSubterm(1);
                                                                    z2=term.getSubterm(1);
                                                                    term=termFactory.makeAppl(testmyinnermost._consMul_2,new IStrategoTerm[]{x10,termFactory.makeAppl(testmyinnermost._consMul_2,new IStrategoTerm[]{y6,z2})});
                                                                    if(true)break Success10;
                                                                }
                                                                term=term0;
                                                            }
                                                            Success11:{
                                                                if(cons0== testmyinnermost._consPow_2){
                                                                    Fail12:{
                                                                        IStrategoTerm x11=null;
                                                                        IStrategoTerm y7=null;
                                                                        x11=term.getSubterm(0);
                                                                        IStrategoTerm arg11=term.getSubterm(1);
                                                                        if(arg11.getTermType()!=IStrategoTerm.APPL|| testmyinnermost._consSuc_1!=((IStrategoAppl)arg11).getConstructor())break Fail12;
                                                                        y7=arg11.getSubterm(0);
                                                                        term=termFactory.makeAppl(testmyinnermost._consMul_2,new IStrategoTerm[]{x11,termFactory.makeAppl(testmyinnermost._consPow_2,new IStrategoTerm[]{x11,y7})});
                                                                        if(true)break Success11;
                                                                    }
                                                                    term=term0;
                                                                }
                                                                if(cons0== testmyinnermost._consPow_2){
                                                                    IStrategoTerm arg12=term.getSubterm(1);
                                                                    if(arg12.getTermType()!=IStrategoTerm.APPL|| testmyinnermost._consZero_0!=((IStrategoAppl)arg12).getConstructor())break Fail0;
                                                                    term= testmyinnermost.constSuc0;
                                                                }
                                                                else {
                                                                    break Fail0;
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
                if(true)return term;
            }
            context.push("E_0_0");
            context.popOnFailure();
            return null;
        }
    }
    @SuppressWarnings("all")public static class myinnermost_1_0 extends Strategy{
        public static  myinnermost_1_0 instance=new myinnermost_1_0();
        @Override public IStrategoTerm invoke(Context context,IStrategoTerm term,Strategy s2){
            context.push("myinnermost_1_0");
            Fail13:{
                myinnermost_1_0$lifted0 myinnermost_1_0$lifted00=new myinnermost_1_0$lifted0();
                myinnermost_1_0$lifted00.s2=s2;
                term=bottomup_1_0.instance.invoke(context,term,myinnermost_1_0$lifted00);
                if(term==null)break Fail13;
                context.popOnSuccess();
                if(true)return term;
            }
            context.popOnFailure();
            return null;
        }
    }
    @SuppressWarnings("all")public static class main_0_0 extends Strategy{
        public static  main_0_0 instance=new main_0_0();
        @Override public IStrategoTerm invoke(Context context,IStrategoTerm term){
            context.push("main_0_0");
            Fail14:{
                term=io_wrap_1_0.instance.invoke(context,term,eval_0_0.instance);
                if(term==null)break Fail14;
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
            Fail15:{
                for(int i=0; i<10; i++) {
                    long startTime = System.currentTimeMillis();
                    System.out.println("Started...");
                    myinnermost_1_0.instance.invoke(context, term, $E_0_0.instance);
                    long endTime = System.currentTimeMillis();
                    System.out.println("Total execution time: " + (endTime - startTime) + "ms");
                }
                System.exit(0);
                if(term==null)break Fail15;
                context.popOnSuccess();
                if(true)return term;
            }
            context.popOnFailure();
            return null;
        }
    }
    @SuppressWarnings("all")private static final class myinnermost_1_0$lifted0 extends Strategy{
        Strategy s2;
        @Override public IStrategoTerm invoke(Context context,IStrategoTerm term){
            Fail16:{
                myinnermost_1_0$lifted1 myinnermost_1_0$lifted10=new myinnermost_1_0$lifted1();
                myinnermost_1_0$lifted10.s2=s2;
                term=try_1_0.instance.invoke(context,term,myinnermost_1_0$lifted10);
                if(term==null)break Fail16;
                if(true)return term;
            }
            return null;
        }
    }
    @SuppressWarnings("all")private static final class myinnermost_1_0$lifted1 extends Strategy{
        Strategy s2;
        @Override public IStrategoTerm invoke(Context context,IStrategoTerm term){
            Fail17:{
                term=s2.invoke(context,term);
                if(term==null)break Fail17;
                inn_fusion00 inn_fusion000=new inn_fusion00();
                inn_fusion000.s2=s2;
                term=bottomup_1_00.instance.invoke(context,term,inn_fusion000);
                if(term==null)break Fail17;
                if(true)return term;
            }
            return null;
        }
    }
    @SuppressWarnings("all")private static final class inn_fusion00 extends Strategy{
        Strategy s2;
        @Override public IStrategoTerm invoke(Context context,IStrategoTerm term){
            Fail18:{
                IStrategoTerm term12=term;
                Success12:{
                    Fail19:{
                        term=s2.invoke(context,term);
                        if(term==null)break Fail19;
                        term=bottomup_1_00.instance.invoke(context,term,this);
                        if(term==null)break Fail19;
                        if(true)break Success12;
                    }
                    term=term12;
                }
                if(true)return term;
            }
            return null;
        }
    }
    @SuppressWarnings("all")private static final class bottomup_1_00 extends Strategy{
        public static final  bottomup_1_00 instance=new bottomup_1_00();
        @Override public IStrategoTerm invoke(Context context,IStrategoTerm term,Strategy s3){
            Fail20:{
                myinnermost_1_0$lifted2 myinnermost_1_0$lifted20=new myinnermost_1_0$lifted2();
                myinnermost_1_0$lifted20.s3=s3;
                term=SRTS_all.instance.invoke(context,term,myinnermost_1_0$lifted20);
                if(term==null)break Fail20;
                term=s3.invoke(context,term);
                if(term==null)break Fail20;
                if(true)return term;
            }
            return null;
        }
    }
    @SuppressWarnings("all")private static final class myinnermost_1_0$lifted2 extends Strategy{
        Strategy s3;
        @Override public IStrategoTerm invoke(Context context,IStrategoTerm term){
            Fail21:{
                term=bottomup_1_00.instance.invoke(context,term,s3);
                if(term==null)break Fail21;
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
            testmyinnermost.init(compiledContext);
            varScope.addSVar("E_0_0",new InteropSDefT($E_0_0.instance,context));
            varScope.addSVar("myinnermost_1_0",new InteropSDefT(myinnermost_1_0.instance,context));
            varScope.addSVar("main_0_0",new InteropSDefT(main_0_0.instance,context));
            varScope.addSVar("eval_0_0",new InteropSDefT(eval_0_0.instance,context));
        }
        private void registerLazy(org.spoofax.interpreter.core.IContext context,Context compiledContext,ClassLoader classLoader,org.spoofax.interpreter.core.VarScope varScope){
            compiledContext.registerComponent("test");
            testmyinnermost.init(compiledContext);
            varScope.addSVar("E_0_0",new InteropSDefT(classLoader,"test$$E_0_0",context));
            varScope.addSVar("myinnermost_1_0",new InteropSDefT(classLoader,"test$myinnermost_1_0",context));
            varScope.addSVar("main_0_0",new InteropSDefT(classLoader,"test$main_0_0",context));
            varScope.addSVar("eval_0_0",new InteropSDefT(classLoader,"test$eval_0_0",context));
        }
    }
}
