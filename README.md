# Mini Stratego compiler

This compiler supports a minuscule subset of Stratego:
* Conditionals `a < b + c`, `id`, `fail`, and `all`
* Rules parameterised by strategies: `bottomup(s) = all(bottomup(s)); s`
* Term construction and strategy application `!Add(<s> Succ(x),y)`
* Pattern matching: `Add(Succ(x),y) -> ...` 


It has the following (mis)features:
* It compiles each term constructor to a Java class
* It compiles pattern matching to nested case trees
* It compiles strategy control flow directly to Java control flow by (ab)using labeled `break`
* It (blindly) specializes all strategy arguments (in certain cases this may cause the compiler to loop -- so a realistic compiler would need to detect this and compile those calls differently)
* It inlines all strategies that are called only once
* It does some local optimisations
* It does not have a parser, so you have to write down the AST
