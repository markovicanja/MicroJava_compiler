# MicroJava_compiler
## Komande za generisanje koda
Pozicionirati se u src folder projekta:
- Generisanje leksera:
##### java -cp ../lib/JFlex.jar JFlex.Main -d rs\ac\bg\etf\pp1 ..\spec\mjlexer.flex >izlaz.out 2>izlaz.err
 - Generisanje parsera:
##### java -cp ../lib/cup_v10k.jar java_cup.Main -destdir rs\ac\bg\etf\pp1 -parser MJParser -ast rs.ac.bg.etf.pp1.ast -buildtree ..\spec\mjparser.cup >izlaz.out 2>izlaz.err
 - Kompajliranje:
##### test\program.mj test\program.obj (lokalni fajl Compiler.java)
 - Izvršavanje bajtkoda:
##### -debug test\program.obj (biblioteka mj-runtime-1.1.jar klasa Run.class)
 - Ispis bajtkoda:
##### test\program.obj (biblioteka mj-runtime-1.1.jar klasa disasm.class)
