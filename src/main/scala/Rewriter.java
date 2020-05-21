class Rewriter {
    static Term main(Term it){
        Term x49 = new Zero();
        Term x50 = new Suc(x49);
        Term x51 = new X();
        Term x52 = new Add(x50,x51);
        Term x53 = new X();
        Term x54 = new Zero();
        Term x55 = new Suc(x54);
        Term x56 = new Suc(x55);
        Term x57 = new Add(x53,x56);
        Term x58 = new Mul(x52,x57);
        Term x59 = x4(x58);
        if(x59 == null) return null;
        return x59;
    }
    static Term x4(Term it){
        Term x6 = it;
        Term x60 = null;
        if(x6 instanceof Suc){
            Suc x61 = (Suc)x6;
            Term x7 = x61.f0;
            Term x62 = x4(x7);
            if(x62 == null) return null;
            Term x63 = new Suc(x62);
            x60 = x63;
        }
        else{
            Term x64 = null;
            if(x6 instanceof Zero){
                Zero x65 = (Zero)x6;
                Term x66 = new Zero();
                x64 = x66;
            }
            else{
                Term x67 = null;
                if(x6 instanceof Mul){
                    Mul x68 = (Mul)x6;
                    Term x8 = x68.f0;
                    Term x9 = x68.f1;
                    Term x69 = x4(x8);
                    if(x69 == null) return null;
                    Term x70 = x4(x9);
                    if(x70 == null) return null;
                    Term x71 = new Mul(x69,x70);
                    x67 = x71;
                }
                else{
                    Term x72 = null;
                    if(x6 instanceof Add){
                        Add x73 = (Add)x6;
                        Term x10 = x73.f0;
                        Term x11 = x73.f1;
                        Term x74 = x4(x10);
                        if(x74 == null) return null;
                        Term x75 = x4(x11);
                        if(x75 == null) return null;
                        Term x76 = new Add(x74,x75);
                        x72 = x76;
                    }
                    else{
                        Term x77 = null;
                        if(x6 instanceof X){
                            X x78 = (X)x6;
                            Term x79 = new X();
                            x77 = x79;
                        }
                        else{
                            return null;
                        }
                        x72 = x77;
                    }
                    x67 = x72;
                }
                x64 = x67;
            }
            x60 = x64;
        }
        Term x80;
        x81:
        do{
            x82:
            do{
                Term x12 = x60;
                Term x83 = null;
                if(x12 instanceof Add){
                    Add x84 = (Add)x12;
                    Term x13 = x84.f0;
                    Term x14 = x84.f1;
                    Term x85 = null;
                    if(x13 instanceof Zero){
                        Zero x86 = (Zero)x13;
                        x85 = x14;
                    }
                    else{
                        Term x87 = null;
                        if(x14 instanceof Zero){
                            Zero x88 = (Zero)x14;
                            x87 = x13;
                        }
                        else{
                            Term x89 = null;
                            if(x13 instanceof Suc){
                                Suc x90 = (Suc)x13;
                                Term x17 = x90.f0;
                                Term x91 = new Add(x17,x14);
                                Term x92 = new Suc(x91);
                                x89 = x92;
                            }
                            else{
                                Term x93 = null;
                                if(x14 instanceof Suc){
                                    Suc x94 = (Suc)x14;
                                    Term x19 = x94.f0;
                                    Term x95 = new Add(x13,x19);
                                    Term x96 = new Suc(x95);
                                    x93 = x96;
                                }
                                else{
                                    break x82;
                                }
                                x89 = x93;
                            }
                            x87 = x89;
                        }
                        x85 = x87;
                    }
                    x83 = x85;
                }
                else{
                    Term x97 = null;
                    if(x12 instanceof Mul){
                        Mul x98 = (Mul)x12;
                        Term x20 = x98.f0;
                        Term x21 = x98.f1;
                        Term x99 = null;
                        if(x20 instanceof Zero){
                            Zero x100 = (Zero)x20;
                            Term x101 = new Zero();
                            x99 = x101;
                        }
                        else{
                            Term x102 = null;
                            if(x21 instanceof Zero){
                                Zero x103 = (Zero)x21;
                                Term x104 = new Zero();
                                x102 = x104;
                            }
                            else{
                                Term x105 = null;
                                if(x20 instanceof Suc){
                                    Suc x106 = (Suc)x20;
                                    Term x30 = x106.f0;
                                    Term x107 = new Mul(x30,x21);
                                    Term x108 = new Add(x107,x21);
                                    x105 = x108;
                                }
                                else{
                                    Term x109 = null;
                                    if(x21 instanceof Suc){
                                        Suc x110 = (Suc)x21;
                                        Term x34 = x110.f0;
                                        Term x111 = new Mul(x20,x34);
                                        Term x112 = new Add(x20,x111);
                                        x109 = x112;
                                    }
                                    else{
                                        Term x113 = null;
                                        if(x20 instanceof Add){
                                            Add x114 = (Add)x20;
                                            Term x39 = x114.f0;
                                            Term x40 = x114.f1;
                                            Term x115 = new Mul(x39,x21);
                                            Term x116 = new Mul(x40,x21);
                                            Term x117 = new Add(x115,x116);
                                            x113 = x117;
                                        }
                                        else{
                                            Term x118 = null;
                                            if(x21 instanceof Add){
                                                Add x119 = (Add)x21;
                                                Term x43 = x119.f0;
                                                Term x44 = x119.f1;
                                                Term x120 = new Mul(x43,x20);
                                                Term x121 = new Mul(x44,x20);
                                                Term x122 = new Add(x120,x121);
                                                x118 = x122;
                                            }
                                            else{
                                                Term x123 = null;
                                                if(x20 instanceof Mul){
                                                    Mul x124 = (Mul)x20;
                                                    Term x47 = x124.f0;
                                                    Term x48 = x124.f1;
                                                    Term x125 = new Mul(x48,x21);
                                                    Term x126 = new Mul(x47,x125);
                                                    x123 = x126;
                                                }
                                                else{
                                                    break x82;
                                                }
                                                x118 = x123;
                                            }
                                            x113 = x118;
                                        }
                                        x109 = x113;
                                    }
                                    x105 = x109;
                                }
                                x102 = x105;
                            }
                            x99 = x102;
                        }
                        x97 = x99;
                    }
                    else{
                        break x82;
                    }
                    x83 = x97;
                }
                Term x127 = x4(x83);
                if(x127 == null) break x82;
                x80 = x127;
                break x81;
            }while(false);
            x80 = x60;
        }while(false);
        return x80;
    }
}
interface Term { void print(); }
final class Suc implements Term {
    public Suc(Term x0){
        this.f0 = x0;
    }
    public Term f0;
    public void print(){
        System.out.print("Suc(");
        f0.print();
        System.out.print(")");
    }
}
final class Zero implements Term {
    public Zero(){
    }
    public void print(){
        System.out.print("Zero(");
        System.out.print(")");
    }
}
final class Mul implements Term {
    public Mul(Term x0, Term x1){
        this.f0 = x0;
        this.f1 = x1;
    }
    public Term f0;
    public Term f1;
    public void print(){
        System.out.print("Mul(");
        f0.print();
        System.out.print(",");
        f1.print();
        System.out.print(")");
    }
}
final class Add implements Term {
    public Add(Term x0, Term x1){
        this.f0 = x0;
        this.f1 = x1;
    }
    public Term f0;
    public Term f1;
    public void print(){
        System.out.print("Add(");
        f0.print();
        System.out.print(",");
        f1.print();
        System.out.print(")");
    }
}
final class X implements Term {
    public X(){
    }
    public void print(){
        System.out.print("X(");
        System.out.print(")");
    }
}
