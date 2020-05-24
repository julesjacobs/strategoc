class Rewriter {
  static Term x8(Term it){
    Term x58 = x7(it);
    if(x58 == null) return null;
    return x58;
  }
  static Term main(Term it){
    Term x59 = new Suc(zero);
    Term x60 = new Add(x59,x);
    Term x61 = new Suc(zero);
    Term x62 = new Suc(x61);
    Term x63 = new Suc(x62);
    Term x64 = new Suc(x63);
    Term x65 = new Suc(x64);
    Term x66 = new Suc(x65);
    Term x67 = new Suc(x66);
    Term x68 = new Suc(x67);
    Term x69 = new Suc(x68);
    Term x70 = new Suc(x69);
    Term x71 = new Suc(x70);
    Term x72 = new Suc(x71);
    Term x73 = new Suc(x72);
    Term x74 = new Suc(x73);
    Term x75 = new Suc(x74);
    Term x76 = new Suc(x75);
    Term x77 = new Suc(x76);
    Term x78 = new Suc(x77);
    Term x79 = new Pow(x60,x78);
    Term x80 = x7(x79);
    if(x80 == null) return null;
    return x80;
  }
  static Term x4(Term it){
    Term x18 = it;
    Term x81 = null;
    if(x18 instanceof Add){
      Add x82 = (Add)x18;
      Term x19 = x82.f0;
      Term x20 = x82.f1;
      Term x83 = null;
      if(x19 == zero){
        x83 = x20;
      }else{
      Term x84 = null;
      if(x20 == zero){
        x84 = x19;
      }else{
      Term x85 = null;
      if(x19 instanceof Suc){
        Suc x86 = (Suc)x19;
        Term x23 = x86.f0;
        Term x87;
        x88: {
          x89: {
            Term x90 = new Add(x23,x20);
            Term x91 = x4(x90);
            if(x91 == null) break x89;
            Term x92 = new Suc(x91);
            Term x93 = x4(x92);
            if(x93 == null) break x89;
            x87 = x93;
            break x88;
          }
          Term x94 = null;
          if(x20 instanceof Suc){
            Suc x95 = (Suc)x20;
            Term x24 = x95.f0;
            Term x96;
            x97: {
              x98: {
                Term x99 = new Add(x19,x24);
                Term x100 = x4(x99);
                if(x100 == null) break x98;
                Term x101 = new Suc(x100);
                Term x102 = x4(x101);
                if(x102 == null) break x98;
                x96 = x102;
                break x97;
              }
              x96 = it;
            }
            x94 = x96;
          }else{
          x94 = it;
          }
          x87 = x94;
        }
        x85 = x87;
      }else{
      Term x103 = null;
      if(x20 instanceof Suc){
        Suc x104 = (Suc)x20;
        Term x25 = x104.f0;
        Term x105;
        x106: {
          x107: {
            Term x108 = new Add(x19,x25);
            Term x109 = x4(x108);
            if(x109 == null) break x107;
            Term x110 = new Suc(x109);
            Term x111 = x4(x110);
            if(x111 == null) break x107;
            x105 = x111;
            break x106;
          }
          x105 = it;
        }
        x103 = x105;
      }else{
      x103 = it;
      }
      x85 = x103;
      }
      x84 = x85;
      }
      x83 = x84;
      }
      x81 = x83;
    }else{
    Term x112 = null;
    if(x18 instanceof Mul){
      Mul x113 = (Mul)x18;
      Term x26 = x113.f0;
      Term x27 = x113.f1;
      Term x114 = null;
      if(x26 == zero){
        Term x115;
        x116: {
          x117: {
            Term x118 = x4(zero);
            if(x118 == null) break x117;
            x115 = x118;
            break x116;
          }
          Term x119 = null;
          if(x27 == zero){
            Term x120;
            x121: {
              x122: {
                Term x123 = x4(zero);
                if(x123 == null) break x122;
                x120 = x123;
                break x121;
              }
              x120 = it;
            }
            x119 = x120;
          }else{
          Term x124 = null;
          if(x27 instanceof Suc){
            Suc x125 = (Suc)x27;
            Term x28 = x125.f0;
            Term x126;
            x127: {
              x128: {
                Term x129 = new Mul(x26,x28);
                Term x130 = x4(x129);
                if(x130 == null) break x128;
                Term x131 = new Add(x26,x130);
                Term x132 = x4(x131);
                if(x132 == null) break x128;
                x126 = x132;
                break x127;
              }
              x126 = it;
            }
            x124 = x126;
          }else{
          Term x133 = null;
          if(x27 instanceof Add){
            Add x134 = (Add)x27;
            Term x29 = x134.f0;
            Term x30 = x134.f1;
            Term x135;
            x136: {
              x137: {
                Term x138 = new Mul(x29,x26);
                Term x139 = x4(x138);
                if(x139 == null) break x137;
                Term x140 = new Mul(x30,x26);
                Term x141 = x4(x140);
                if(x141 == null) break x137;
                Term x142 = new Add(x139,x141);
                Term x143 = x4(x142);
                if(x143 == null) break x137;
                x135 = x143;
                break x136;
              }
              x135 = it;
            }
            x133 = x135;
          }else{
          x133 = it;
          }
          x124 = x133;
          }
          x119 = x124;
          }
          x115 = x119;
        }
        x114 = x115;
      }else{
      Term x144 = null;
      if(x27 == zero){
        Term x145;
        x146: {
          x147: {
            Term x148 = x4(zero);
            if(x148 == null) break x147;
            x145 = x148;
            break x146;
          }
          Term x149 = null;
          if(x26 instanceof Suc){
            Suc x150 = (Suc)x26;
            Term x31 = x150.f0;
            Term x151;
            x152: {
              x153: {
                Term x154 = new Mul(x31,x27);
                Term x155 = x4(x154);
                if(x155 == null) break x153;
                Term x156 = new Add(x155,x27);
                Term x157 = x4(x156);
                if(x157 == null) break x153;
                x151 = x157;
                break x152;
              }
              x151 = it;
            }
            x149 = x151;
          }else{
          Term x158 = null;
          if(x26 instanceof Add){
            Add x159 = (Add)x26;
            Term x32 = x159.f0;
            Term x33 = x159.f1;
            Term x160;
            x161: {
              x162: {
                Term x163 = new Mul(x32,x27);
                Term x164 = x4(x163);
                if(x164 == null) break x162;
                Term x165 = new Mul(x33,x27);
                Term x166 = x4(x165);
                if(x166 == null) break x162;
                Term x167 = new Add(x164,x166);
                Term x168 = x4(x167);
                if(x168 == null) break x162;
                x160 = x168;
                break x161;
              }
              x160 = it;
            }
            x158 = x160;
          }else{
          Term x169 = null;
          if(x26 instanceof Mul){
            Mul x170 = (Mul)x26;
            Term x34 = x170.f0;
            Term x35 = x170.f1;
            Term x171;
            x172: {
              x173: {
                Term x174 = new Mul(x35,x27);
                Term x175 = x4(x174);
                if(x175 == null) break x173;
                Term x176 = new Mul(x34,x175);
                Term x177 = x4(x176);
                if(x177 == null) break x173;
                x171 = x177;
                break x172;
              }
              x171 = it;
            }
            x169 = x171;
          }else{
          x169 = it;
          }
          x158 = x169;
          }
          x149 = x158;
          }
          x145 = x149;
        }
        x144 = x145;
      }else{
      Term x178 = null;
      if(x26 instanceof Suc){
        Suc x179 = (Suc)x26;
        Term x36 = x179.f0;
        Term x180;
        x181: {
          x182: {
            Term x183 = new Mul(x36,x27);
            Term x184 = x4(x183);
            if(x184 == null) break x182;
            Term x185 = new Add(x184,x27);
            Term x186 = x4(x185);
            if(x186 == null) break x182;
            x180 = x186;
            break x181;
          }
          Term x187 = null;
          if(x27 instanceof Suc){
            Suc x188 = (Suc)x27;
            Term x37 = x188.f0;
            Term x189;
            x190: {
              x191: {
                Term x192 = new Mul(x26,x37);
                Term x193 = x4(x192);
                if(x193 == null) break x191;
                Term x194 = new Add(x26,x193);
                Term x195 = x4(x194);
                if(x195 == null) break x191;
                x189 = x195;
                break x190;
              }
              x189 = it;
            }
            x187 = x189;
          }else{
          Term x196 = null;
          if(x27 instanceof Add){
            Add x197 = (Add)x27;
            Term x38 = x197.f0;
            Term x39 = x197.f1;
            Term x198;
            x199: {
              x200: {
                Term x201 = new Mul(x38,x26);
                Term x202 = x4(x201);
                if(x202 == null) break x200;
                Term x203 = new Mul(x39,x26);
                Term x204 = x4(x203);
                if(x204 == null) break x200;
                Term x205 = new Add(x202,x204);
                Term x206 = x4(x205);
                if(x206 == null) break x200;
                x198 = x206;
                break x199;
              }
              x198 = it;
            }
            x196 = x198;
          }else{
          x196 = it;
          }
          x187 = x196;
          }
          x180 = x187;
        }
        x178 = x180;
      }else{
      Term x207 = null;
      if(x27 instanceof Suc){
        Suc x208 = (Suc)x27;
        Term x40 = x208.f0;
        Term x209;
        x210: {
          x211: {
            Term x212 = new Mul(x26,x40);
            Term x213 = x4(x212);
            if(x213 == null) break x211;
            Term x214 = new Add(x26,x213);
            Term x215 = x4(x214);
            if(x215 == null) break x211;
            x209 = x215;
            break x210;
          }
          Term x216 = null;
          if(x26 instanceof Add){
            Add x217 = (Add)x26;
            Term x41 = x217.f0;
            Term x42 = x217.f1;
            Term x218;
            x219: {
              x220: {
                Term x221 = new Mul(x41,x27);
                Term x222 = x4(x221);
                if(x222 == null) break x220;
                Term x223 = new Mul(x42,x27);
                Term x224 = x4(x223);
                if(x224 == null) break x220;
                Term x225 = new Add(x222,x224);
                Term x226 = x4(x225);
                if(x226 == null) break x220;
                x218 = x226;
                break x219;
              }
              x218 = it;
            }
            x216 = x218;
          }else{
          Term x227 = null;
          if(x26 instanceof Mul){
            Mul x228 = (Mul)x26;
            Term x43 = x228.f0;
            Term x44 = x228.f1;
            Term x229;
            x230: {
              x231: {
                Term x232 = new Mul(x44,x27);
                Term x233 = x4(x232);
                if(x233 == null) break x231;
                Term x234 = new Mul(x43,x233);
                Term x235 = x4(x234);
                if(x235 == null) break x231;
                x229 = x235;
                break x230;
              }
              x229 = it;
            }
            x227 = x229;
          }else{
          x227 = it;
          }
          x216 = x227;
          }
          x209 = x216;
        }
        x207 = x209;
      }else{
      Term x236 = null;
      if(x26 instanceof Add){
        Add x237 = (Add)x26;
        Term x45 = x237.f0;
        Term x46 = x237.f1;
        Term x238;
        x239: {
          x240: {
            Term x241 = new Mul(x45,x27);
            Term x242 = x4(x241);
            if(x242 == null) break x240;
            Term x243 = new Mul(x46,x27);
            Term x244 = x4(x243);
            if(x244 == null) break x240;
            Term x245 = new Add(x242,x244);
            Term x246 = x4(x245);
            if(x246 == null) break x240;
            x238 = x246;
            break x239;
          }
          Term x247 = null;
          if(x27 instanceof Add){
            Add x248 = (Add)x27;
            Term x47 = x248.f0;
            Term x48 = x248.f1;
            Term x249;
            x250: {
              x251: {
                Term x252 = new Mul(x47,x26);
                Term x253 = x4(x252);
                if(x253 == null) break x251;
                Term x254 = new Mul(x48,x26);
                Term x255 = x4(x254);
                if(x255 == null) break x251;
                Term x256 = new Add(x253,x255);
                Term x257 = x4(x256);
                if(x257 == null) break x251;
                x249 = x257;
                break x250;
              }
              x249 = it;
            }
            x247 = x249;
          }else{
          x247 = it;
          }
          x238 = x247;
        }
        x236 = x238;
      }else{
      Term x258 = null;
      if(x27 instanceof Add){
        Add x259 = (Add)x27;
        Term x49 = x259.f0;
        Term x50 = x259.f1;
        Term x260;
        x261: {
          x262: {
            Term x263 = new Mul(x49,x26);
            Term x264 = x4(x263);
            if(x264 == null) break x262;
            Term x265 = new Mul(x50,x26);
            Term x266 = x4(x265);
            if(x266 == null) break x262;
            Term x267 = new Add(x264,x266);
            Term x268 = x4(x267);
            if(x268 == null) break x262;
            x260 = x268;
            break x261;
          }
          Term x269 = null;
          if(x26 instanceof Mul){
            Mul x270 = (Mul)x26;
            Term x51 = x270.f0;
            Term x52 = x270.f1;
            Term x271;
            x272: {
              x273: {
                Term x274 = new Mul(x52,x27);
                Term x275 = x4(x274);
                if(x275 == null) break x273;
                Term x276 = new Mul(x51,x275);
                Term x277 = x4(x276);
                if(x277 == null) break x273;
                x271 = x277;
                break x272;
              }
              x271 = it;
            }
            x269 = x271;
          }else{
          x269 = it;
          }
          x260 = x269;
        }
        x258 = x260;
      }else{
      Term x278 = null;
      if(x26 instanceof Mul){
        Mul x279 = (Mul)x26;
        Term x53 = x279.f0;
        Term x54 = x279.f1;
        Term x280;
        x281: {
          x282: {
            Term x283 = new Mul(x54,x27);
            Term x284 = x4(x283);
            if(x284 == null) break x282;
            Term x285 = new Mul(x53,x284);
            Term x286 = x4(x285);
            if(x286 == null) break x282;
            x280 = x286;
            break x281;
          }
          x280 = it;
        }
        x278 = x280;
      }else{
      x278 = it;
      }
      x258 = x278;
      }
      x236 = x258;
      }
      x207 = x236;
      }
      x178 = x207;
      }
      x144 = x178;
      }
      x114 = x144;
      }
      x112 = x114;
    }else{
    Term x287 = null;
    if(x18 instanceof Pow){
      Pow x288 = (Pow)x18;
      Term x55 = x288.f0;
      Term x56 = x288.f1;
      Term x289 = null;
      if(x56 instanceof Suc){
        Suc x290 = (Suc)x56;
        Term x57 = x290.f0;
        Term x291;
        x292: {
          x293: {
            Term x294 = new Pow(x55,x57);
            Term x295 = x4(x294);
            if(x295 == null) break x293;
            Term x296 = new Mul(x55,x295);
            Term x297 = x4(x296);
            if(x297 == null) break x293;
            x291 = x297;
            break x292;
          }
          x291 = it;
        }
        x289 = x291;
      }else{
      Term x298 = null;
      if(x56 == zero){
        Term x299;
        x300: {
          x301: {
            Term x302 = x4(zero);
            if(x302 == null) break x301;
            Term x303 = new Suc(x302);
            Term x304 = x4(x303);
            if(x304 == null) break x301;
            x299 = x304;
            break x300;
          }
          x299 = it;
        }
        x298 = x299;
      }else{
      x298 = it;
      }
      x289 = x298;
      }
      x287 = x289;
    }else{
    x287 = it;
    }
    x112 = x287;
    }
    x81 = x112;
    }
    return x81;
  }
  static Term x7(Term it){
    Term x10 = it;
    Term x305 = null;
    if(x10 instanceof Pow){
      Pow x306 = (Pow)x10;
      Term x11 = x306.f0;
      Term x12 = x306.f1;
      Term x307 = x8(x11);
      if(x307 == null) return null;
      Term x308 = x8(x12);
      if(x308 == null) return null;
      Term x309 = new Pow(x307,x308);
      Term x310 = x4(x309);
      if(x310 == null) return null;
      x305 = x310;
    }else{
    Term x311 = null;
    if(x10 instanceof Suc){
      Suc x312 = (Suc)x10;
      Term x13 = x312.f0;
      Term x313 = x8(x13);
      if(x313 == null) return null;
      Term x314 = new Suc(x313);
      Term x315 = x4(x314);
      if(x315 == null) return null;
      x311 = x315;
    }else{
    Term x316 = null;
    if(x10 == zero){
      Term x317 = x4(zero);
      if(x317 == null) return null;
      x316 = x317;
    }else{
    Term x318 = null;
    if(x10 instanceof Mul){
      Mul x319 = (Mul)x10;
      Term x14 = x319.f0;
      Term x15 = x319.f1;
      Term x320 = x8(x14);
      if(x320 == null) return null;
      Term x321 = x8(x15);
      if(x321 == null) return null;
      Term x322 = new Mul(x320,x321);
      Term x323 = x4(x322);
      if(x323 == null) return null;
      x318 = x323;
    }else{
    Term x324 = null;
    if(x10 instanceof Add){
      Add x325 = (Add)x10;
      Term x16 = x325.f0;
      Term x17 = x325.f1;
      Term x326 = x8(x16);
      if(x326 == null) return null;
      Term x327 = x8(x17);
      if(x327 == null) return null;
      Term x328 = new Add(x326,x327);
      Term x329 = x4(x328);
      if(x329 == null) return null;
      x324 = x329;
    }else{
    Term x330 = null;
    if(x10 == x){
      Term x331 = x4(x);
      if(x331 == null) return null;
      x330 = x331;
    }else{
    return null;
    }
    x324 = x330;
    }
    x318 = x324;
    }
    x316 = x318;
    }
    x311 = x316;
    }
    x305 = x311;
    }
    return x305;
  }
  public static void main(String args[]){
    for(int i=0; i<10; i++){
      System.out.println("Started...");
      long startTime = System.currentTimeMillis();
      main((Term)null);
      long endTime = System.currentTimeMillis();
      System.out.println("Total execution time: " + (endTime - startTime) + "ms");
    }
  }
  static final Term zero = new Zero();
  static final Term x = new X();
}
interface Term { void print(); }
final class Pow implements Term {
  public Pow(Term x0, Term x1){
    this.f0 = x0;
    this.f1 = x1;
  }
  public Term f0;
  public Term f1;
  public void print(){
    System.out.print("Pow(");
    f0.print();
    System.out.print(",");
    f1.print();
    System.out.print(")");
  }
}
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
