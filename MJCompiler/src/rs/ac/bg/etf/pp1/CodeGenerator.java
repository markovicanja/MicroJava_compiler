package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Stack;

import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {
	
	private Logger log = Logger.getLogger(getClass());
	
	private int mainPc;
	private Obj outerScope = null;
	private Variable variable = null;
	private Obj currentMethod = null;
	private ArrayList<Variable> variables = new ArrayList<Variable>();
	private int jmpAfterExpr1Pc, jmpAfterExpr2Pc;
	private int condCnt = 0; // serijski brojac, broji sve do pojave sledeceg OR prelazeci (inkrementirajuci se) preko AND-ova
	
	private LinkedList<ArrayList<IfCondJcc>> ifCondsStack = new LinkedList<ArrayList<IfCondJcc>>();
	private LinkedList<Integer> ifPcStack = new LinkedList<Integer>(); 
	private LinkedList<Integer> retWhilePcStack = new LinkedList<Integer>(); //povratna adresa do do-while;
	
	private Stack<Integer> relopStack = new Stack<>();
	private Stack<Integer> addopStack = new Stack<>();
	private Stack<Integer> mulopStack = new Stack<>();

	public CodeGenerator(Obj outerScope) {
		super();
		this.outerScope = outerScope;
	}
	
	public int getMainPc() {
		return mainPc;
	}
	
	public void report_error(String message, SyntaxNode info) {
//		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0) {
			msg.append(" na liniji ").append(line).append("!");
		}
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0) {
			msg.append(" na liniji ").append(line);
		}
		log.info(msg.toString());
	}
	
	public Obj getMethObj(String methName) {
		Obj obj = Tab.find(methName);
		if (obj == Tab.noObj) {
			Collection<Obj> localSymbols = outerScope.getLocalSymbols();
			for (Obj o : localSymbols) {
				if (o.getKind() == Obj.Meth && o.getName().equals(methName)) {
					return o;
				}
			}
		} else {
			return obj;
		}
		return null;
	}
	
	public Obj getVarConst(String objName) {		 
		Obj obj = Tab.find(objName);
		if (obj == Tab.noObj) {
			Collection<Obj> localSymbols = outerScope.getLocalSymbols();
			for (Obj o : localSymbols) {				
				if (o.getKind() != Obj.Meth && o.getName().equals(objName)) {
					return o;
				}
			}			
			if (currentMethod != null) {
				localSymbols = currentMethod.getLocalSymbols();
				for (Obj o: localSymbols) {		
					if (o.getName().equals(objName)) {
						return o;
					}
				}
			}		
		} else {
			return obj;
		}
		return null;
	}
	
	// JEL SE OVO POZIVA SAMO ZA POSLEDNJI U LISTI I AKO DA ZASTO?? I ZASTO SE POSTAVLJA DA JE MODIFIED??
	private void fixJmpPc(IfCondJcc ifCondJcc) { // preskakanje uslova ako je moguce i direktan skok u if naredbu
		int oldPc = Code.pc;
		Code.pc = ifCondJcc.getPc() - 1;
		if (ifCondJcc.getRelop() == -1) 
			Code.put(Code.jcc + Code.ne);
		else
			Code.put(Code.jcc + ifCondJcc.getRelop());
		
		Code.pc = oldPc;
		Code.put2(ifCondJcc.getPc(), Code.pc - ifCondJcc.getPc() + 1);
		ifCondJcc.setModified(true);
	}
	
	private void jmpAndSavePc(boolean isDoWhile, int ordNum, int relOp) { // postavljanje skoka i cuvanje uslova na koje treba dodati adresu skoka
		if (!isDoWhile) {
			if (relOp == - 1) { // ako je relop == - 1 nema relacionih operatora, poslato je true ili false
				Code.loadConst(0);
				Code.put(Code.jcc + Code.eq); // skace ako je false
			} else {
				Code.put(Code.jcc + Code.inverse[relOp]); // skace se ako je suprotno od relopa
			}
			ArrayList<IfCondJcc> markedConds = ifCondsStack.getLast();
			markedConds.add(new IfCondJcc(Code.pc, ordNum, relOp)); // ne znamo adresu skoka pa ne mozemo ni da je stavimo ali je stavljamo na spisak za dodati
			Code.pc += 2; // preskacemo 2 adrese na koje treba da se doda adresa skoka
		} else {
			if (relOp == - 1) { // ako je rel. op - 1 nema relacionih operatora, poslato je true ili false
				Code.loadConst(0);
				Code.put(Code.jcc + Code.ne);
			} else {
				Code.put(Code.jcc + relOp);
			}			
			Code.put2(retWhilePcStack.removeLast() - Code.pc + 1); // mesto u kodu gde se vracamo (jedno jedino, gore) oznaceno sa retMarkerPc	
		}
	}
	
	private SyntaxNode prepare(SyntaxNode syntaxNode) {
		while (syntaxNode.getClass() != StmtIf.class
				&& syntaxNode.getClass() != StmtIfElse.class
				&& syntaxNode.getClass() != StmtDoWhile.class
				&& syntaxNode != null) {
			syntaxNode = syntaxNode.getParent();
		}		
		return syntaxNode;
	}	
	
	// *** VISIT METODE *** 
	
	// Program
	public void visit(Program program) {		
		Code.dataSize = outerScope.getLocalSymbols().size();
//		errorDetected = errorDetected || Code.greska;		
	}
	
	// ConstDecl
	public void visit(ConstDeclaration constDeclaration) { // uzima konstante iz liste i postavlja za adresu vrednost konstante, i load-uje tu vrednost
		for (int i = 0; i < variables.size(); i++) {
			Obj o = getVarConst(variables.get(i).getName());			
			if (o != null) {
				Object val = variables.get(i).getValue();
				if (val instanceof Integer) {
					o.setAdr((Integer) val);
				} else if (val instanceof Character) {
					o.setAdr((Character) val);
				} else if (val instanceof Boolean) {				
					o.setAdr((Boolean) val ? 1 : 0);
				} else {
					o.setAdr(0);
				}
				Code.load(o);
			}
		}
		variables.clear();
	}	
	
	// ConstPart
	public void visit(ConstPart constPart) { // dodaje konstantu u listu
		variable.setName(constPart.getConstName());
		variables.add(variable);
		variable = null;
	}
	
	// MethodVoid
	public void visit(MethodVoidDeclaration methodVoidDeclaration) {
		Code.put(Code.exit); 
		Code.put(Code.return_);
		currentMethod = null;
	}
	
	// MethodVoidName
	public void visit(MethodVoidName methodVoidName) { // postavlja mainPc ako je main funkcija, postavlja joj adresu i generise enter instrukciju
		currentMethod = getMethObj(methodVoidName.getMethodName());	
		if (methodVoidName.getMethodName().equals("main")) {
			mainPc = Code.pc;			
		}
		currentMethod.setAdr(Code.pc);
        Code.put(Code.enter);
        Code.put(currentMethod.getLevel());
        Code.put(currentMethod.getLocalSymbols().size());               
	}
	
	// MethodTypeDecl
	public void visit(MethodTypeDeclaration methodTypeDeclaration) { // izaziva Runtime gresku jer nema return
		Code.put(Code.trap); 
		Code.put(1);
		currentMethod = null;
	}
	
	// MethodTypeName
	public void visit(MethodTypeName methodTypeName) { 		
		currentMethod = getMethObj(methodTypeName.getMethodName());
		currentMethod.setAdr(Code.pc);
        Code.put(Code.enter);
        Code.put(currentMethod.getLevel());
        Code.put(currentMethod.getLocalSymbols().size());               
	}
	
	// Value	
	public void visit(NumConst numConst) { // kreira promenljivu
		variable = new Variable("", false, numConst.getN1());
	}
	
	public void visit(BoolConst boolConst) { 
		variable = new Variable("", false, boolConst.getB1());
	}    
	
	public void visit(CharConst charConst) { 
		variable = new Variable("", false, charConst.getC1());
	}	
	
	// Statement
	public void visit(DesignatorAssignment designatorAssignment) { // storuje vrednost sa expr steka u designator
		Code.store(designatorAssignment.getDesignator().obj);	
	}
	
	public void visit(StmtPrint stmtPrint) {
		Struct struct = stmtPrint.getExpr().struct;
		if (struct.equals(Tab.intType) || struct.equals(SemanticAnalyzer.boolType)) {
			Code.loadConst(0); // sto mora ovo??
			Code.put(Code.print);
		} else if (struct.equals(Tab.charType)) {
			Code.loadConst(1);
			Code.put(Code.bprint);
		}	
	}
	
	public void visit(StmtPrintNumConst stmtPrintNumConst) {
		Struct struct = stmtPrintNumConst.getExpr().struct;
		if (struct.equals(Tab.intType) || struct.equals(SemanticAnalyzer.boolType)) {
			Code.loadConst(stmtPrintNumConst.getN2());
			Code.put(Code.print);
		} else if (struct.equals(Tab.charType)) {
			Code.loadConst(1);
			Code.put(Code.bprint);
		}		
	}
	
	public void visit(StmtRead stmtRead) {		
		Obj obj = stmtRead.getDesignator().obj;
        if (obj.getType().equals(Tab.charType))
            Code.put(Code.bread);
        else
            Code.put(Code.read);
        Code.store(obj);
	}
	
	public void visit(StmtReturnExpr stmtReturnExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(StmtReturn stmtReturn) { 
		Code.put(Code.exit);
		Code.put(Code.return_);
	}  
	
	public void visit(StmtIf stmtIf) { // izlazna tacka iz if
		ArrayList<IfCondJcc> ifConds = ifCondsStack.removeLast();
		for (IfCondJcc ifCond : ifConds) {
			if (!ifCond.isModified()) {
				Code.put2(ifCond.getPc(), Code.pc - ifCond.getPc() + 1); // postavljamo adresu skoka na prvu posle ifa
			}
		}
		ifConds.clear();
	}	
	
	public void visit(StmtIfElse stmtIfElse) { // postavlja adresu skoka na else granu
		ArrayList<IfCondJcc> ifConds = ifCondsStack.removeLast();
		int elsePc = ifPcStack.removeLast(); // LIFO
		for (int i = 0; i < ifConds.size(); i++) {
			if (!ifConds.get(i).isModified()) {
				Code.put2(ifConds.get(i).getPc(),  elsePc - ifConds.get(i).getPc() + 3);
			}
		}						
		ifConds.clear();
		Code.put2(elsePc, Code.pc - elsePc + 1); // popunjavamo adresu skoka na prvu posle else grane
	}	 
	
	// IfKw
	public void visit(IfKeyword ifKeyword) { // pravi se nova lista sa uslovima za svaki ugnezdjeni if
		ifCondsStack.add(new ArrayList<IfCondJcc>());
	}	
	
	// StatementIfBody
	public void visit(StmtIfBody stmtIfBody) { // dodajemo adresu skoka na prvu posle else grane ako ona postoji			
		if (stmtIfBody.getParent().getClass() == StmtIfElse.class) {
			Code.put(Code.jmp);
			ifPcStack.addLast(Code.pc);
			Code.pc += 2; // ostavimo dva prazna mesta jer je adresa skoka dva bajta;
		}
	}
	
	// Designator statement
	public void visit(DesignatorIncrement designatorIncrement) {
		if (designatorIncrement.getDesignator().obj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
		}
		Code.load(designatorIncrement.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(designatorIncrement.getDesignator().obj);		
	}
	
	public void visit(DesignatorDecrement designatorDecrement) {
		if(designatorDecrement.getDesignator().obj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
		}
		Code.load(designatorDecrement.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(designatorDecrement.getDesignator().obj);		
	}
	
	public void visit(DesignatorMethodCallParams designatorMethodCallParams) { // PROVERI - ZASTO SE NIGDE NE STAVLJA invokevirtual??
		Obj method = designatorMethodCallParams.getDesignator().obj;
		if (outerScope.getLocalSymbols().contains(method)) {
			int offset = method.getAdr() - Code.pc;
			Code.put(Code.call);
			Code.put2(offset);
		}
	}

	public void visit(DesignatorMethodCall designatorMethodCall) { 
		Obj method = designatorMethodCall.getDesignator().obj;
		if (outerScope.getLocalSymbols().contains(method)) {
			int offset = method.getAdr() - Code.pc;
			Code.put(Code.call);
			Code.put2(offset);
		}
	}
	
	// Condition
	public void visit(CondOr condOr) {	
		condCnt = 0;
		ArrayList<IfCondJcc> markedConds = ifCondsStack.getLast(); // uslovi iz trenutnog if-a
		fixJmpPc(markedConds.get(markedConds.size() - 1));
	}		
	
	public void visit(CondSingle condSingle) {
		condCnt = 0;
	}
		
    public void visit(CondTermAnd condTermAnd) {
		condCnt++; 
    }
    
	public void visit(CondTermSingle condTermSingle) {
		condCnt++; 
	}
	
	public void visit(CondFactRelop condFactRelop) { // ubacuje u listu i generise instrukcije skoka	
		SyntaxNode stmt = prepare(condFactRelop);
		jmpAndSavePc(stmt.getClass() == StmtDoWhile.class, condCnt, relopStack.pop());
	}
	
	public void visit(CondFactSingle condFactSingle) {		
		SyntaxNode stmt = prepare(condFactSingle); // dohvata roditelja StmtIf, StmtIfElse, StmtDoWhile
		jmpAndSavePc(stmt.getClass() == StmtDoWhile.class, condCnt, -1);
	}
	
	// ExprTernary
	public void visit(TernaryCond ternaryCond) {		
		Code.loadConst(1);
		Code.putFalseJump(Code.eq, 0);
		jmpAfterExpr1Pc = Code.pc - 2;
	}
	
	// TernaryExpr
	public void visit(TerExpr1 terExpr1) {
		Code.putJump(0);
		jmpAfterExpr2Pc = Code.pc - 2;
		Code.fixup(jmpAfterExpr1Pc);
	}
	
	public void visit(TerExpr2 terExpr2) {
		Code.fixup(jmpAfterExpr2Pc);
	}
	
	// Expr1
	public void visit(ExprAddop exprAddop) {
        Code.put(addopStack.pop());
    }
	
	public void visit(ExprNeg exprNeg) {
        Code.put(Code.neg);
    }
	
	// Term
	public void visit(TermMulop termMulop) {
		Code.put(mulopStack.pop());
	}
	
	// Factor
	public void visit(FactorNumConst factorNumConst) {
		Code.loadConst(factorNumConst.getN1());
	}
	
	public void visit(FactorCharConst factorCharConst) {
		Code.loadConst(factorCharConst.getC1());
	}
	
	public void visit(FactorBoolConst factorBoolConst) {
		Code.loadConst(factorBoolConst.getB1() ? 1 : 0);		
	}
	
	public void visit(FactorNewArray factorNewArray) {		
		Code.put(Code.newarray);
        if (factorNewArray.getType().struct == Tab.charType) {
			Code.put(0); 
        } else { 
			Code.put(1);
        }        
	}
	
	public void visit(FactorDesignator factorDesignator) {
		Obj o = getVarConst(factorDesignator.getDesignator().obj.getName());
		if (o != null) {
			Code.load(factorDesignator.getDesignator().obj);
		}
	}
	
	public void visit(FuncCallParams funcCallParams) {
		Obj method = funcCallParams.getDesignator().obj;
		if (outerScope.getLocalSymbols().contains(method)) {
			int offset = method.getAdr() - Code.pc;
			Code.put(Code.call);
			Code.put2(offset);
		}
	}

	public void visit(FuncCall funcCall) {
		Obj method = funcCall.getDesignator().obj;
		if (outerScope.getLocalSymbols().contains(method)) {
			int offset = method.getAdr() - Code.pc;
			Code.put(Code.call);
			Code.put2(offset);
		}
	}
	
	// Designator	
	public void visit(DesignatorArray designatorArray) { // prvo je stavljen index, pa onda desigName, pa se rotiraju
		Obj o = null;
		o = getVarConst(designatorArray.getDesignatorName());	
		if (o != null) {
			Code.load(o);
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
		}	
	}
	
	//Relop
	public void visit(RelopEQ relopEQ) { 
		relopStack.push(Code.eq); 		
	}
	
	public void visit(RelopNE relopNE) { 
		relopStack.push(Code.ne); 	
	}
	
	public void visit(RelopGT relopGT) { 
		relopStack.push(Code.gt); 	
	}
	
	public void visit(RelopGE relopGE) {
		relopStack.push(Code.ge); 	
	}
	
	public void visit(RelopLT relopLT) { 
		relopStack.push(Code.lt); 	
	}
	
    public void visit(RelopLE relopLE) { 
    	relopStack.push(Code.le);
    } 	
	
	// Addop
	public void visit(AddopPlus addopPlus) {
		addopStack.push(Code.add);
	}
	
	public void visit(AddopMinus addopMinus) {
		addopStack.push(Code.sub);
	}
	
	// Mulop
	public void visit(MulopMul mulopMul) {		
		mulopStack.push(Code.mul);
	}

	public void visit(MulopDiv mulopDiv) {
		mulopStack.push(Code.div);
	}
	
	public void visit(MulopMod mulopMod) {
		mulopStack.push(Code.rem);
	}
}
