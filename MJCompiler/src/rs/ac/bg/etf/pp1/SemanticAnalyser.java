package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class SemanticAnalyser extends VisitorAdaptor {
	// ubacivanje bool tipa u tabelu simbola
	private static Struct boolType = Tab.insert(Obj.Type, "bool", new Struct(5)).getType();
	
}
