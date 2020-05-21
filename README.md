# Mini Stratego compiler

This compiler supports a minuscule subset of Stratego:
* Rules parameterised by strategies & all: `bottomup(s) = all(bottomup(s)); s`
* Conditionals: `a < b + c`, `id`, `fail`
* Term construction and strategy application `!Add(<s> Succ(x),y)`
* Pattern matching: `Add(Succ(x),y) -> ...` 

The compiler currently does not have a parser, so you have to write down the AST.

It has the following features:
* It compiles each term constructor to a Java class
* It compiles pattern matching to nested case trees
* It compiles strategy control flow directly to Java control flow by (ab)using labeled `break`.
* It (blindly) specializes all strategy arguments (in certain cases this may cause the compiler to loop -- so a realistic compiler would need to detect this and compile those calls differently)
* It inlines all strategies that are called only once
* It does some local optimisations