package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Collection;
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
	
	// *** VISIT METODE *** 
	
	// Program
	public void visit(Program program) {		
		Code.dataSize = outerScope.getLocalSymbols().size();
//		errorDetected = errorDetected || Code.greska;		
	}
	
	// ConstDecl
	public void visit(ConstDeclaration constDeclaration) {	
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
	public void visit(ConstPart constPart) {
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
	public void visit(MethodVoidName methodVoidName) { 		
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
	public void visit(MethodTypeDeclaration methodTypeDeclaration) {
		// end of function reached without a return statement
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
	public void visit(NumConst numConst) { 
		variable = new Variable("", false, numConst.getN1());
	}
	
	public void visit(BoolConst boolConst) { 
		variable = new Variable("", false, boolConst.getB1());
	}    
	
	public void visit(CharConst charConst) { 
		variable = new Variable("", false, charConst.getC1());
	}	
	
	// Statement
	public void visit(DesignatorAssignment designatorAssignment) {
		Code.store(designatorAssignment.getDesignator().obj);	
	}
	
	public void visit(StmtPrint stmtPrint) {
		Struct struct = stmtPrint.getExpr().struct;
		if (struct.equals(Tab.intType) || struct.equals(SemanticAnalyzer.boolType)) {
			Code.loadConst(0); // A STO JE OVDE 0???
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
		Code.put(Code.read);
		Code.store(stmtRead.getDesignator().obj);		
	}
	
	// Designator statement
	public void visit(DesignatorIncrement designatorIncrement) {
		if(designatorIncrement.getDesignator().obj.getKind() == Obj.Elem) {
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
	
	public void visit(DesignatorMethodCallParams designatorMethodCallParams) {
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
	
	// Term
	public void visit(TermSingle termSingle) { 		
		SyntaxNode parent = (SyntaxNode) termSingle.getParent();
		if (parent.getClass() == ExprNeg.class) {
			Code.put(Code.neg);
		}
	} 
	
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
	public void visit(DesignatorArray designatorArray) { //STA JE OVO dup_x1??
		Obj o = null;
		o = getVarConst(designatorArray.getDesignatorName());	
		if (o != null) {
			Code.load(o);
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
		}	
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
