package rs.ac.bg.etf.pp1;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.AssignmentExpr;
import rs.ac.bg.etf.pp1.ast.BoolConst;
import rs.ac.bg.etf.pp1.ast.CharConst;
import rs.ac.bg.etf.pp1.ast.ConstDeclaration;
import rs.ac.bg.etf.pp1.ast.ConstPart;
import rs.ac.bg.etf.pp1.ast.DesignatorAssignment;
import rs.ac.bg.etf.pp1.ast.DesignatorIdent;
import rs.ac.bg.etf.pp1.ast.DesignatorPartArray;
import rs.ac.bg.etf.pp1.ast.DesignatorPartField;
import rs.ac.bg.etf.pp1.ast.ExprMulti;
import rs.ac.bg.etf.pp1.ast.ExprNegMulti;
import rs.ac.bg.etf.pp1.ast.ExprNegSingle;
import rs.ac.bg.etf.pp1.ast.ExprSingle;
import rs.ac.bg.etf.pp1.ast.FactorBoolConst;
import rs.ac.bg.etf.pp1.ast.FactorCharConst;
import rs.ac.bg.etf.pp1.ast.FactorDesignator;
import rs.ac.bg.etf.pp1.ast.FactorExpr;
import rs.ac.bg.etf.pp1.ast.FactorNew;
import rs.ac.bg.etf.pp1.ast.FactorNewArray;
import rs.ac.bg.etf.pp1.ast.FactorNumConst;
import rs.ac.bg.etf.pp1.ast.FuncCall;
import rs.ac.bg.etf.pp1.ast.FuncCallParams;
import rs.ac.bg.etf.pp1.ast.GlobalVarArray;
import rs.ac.bg.etf.pp1.ast.GlobalVarDeclaration;
import rs.ac.bg.etf.pp1.ast.GlobalVarNormal;
import rs.ac.bg.etf.pp1.ast.MethodTypeDeclaration;
import rs.ac.bg.etf.pp1.ast.MethodTypeName;
import rs.ac.bg.etf.pp1.ast.MethodVoidDeclaration;
import rs.ac.bg.etf.pp1.ast.MethodVoidName;
import rs.ac.bg.etf.pp1.ast.NumConst;
import rs.ac.bg.etf.pp1.ast.ParamArray;
import rs.ac.bg.etf.pp1.ast.ParamNormal;
import rs.ac.bg.etf.pp1.ast.ProgName;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.ast.StmtReturnExpr;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.TermMulti;
import rs.ac.bg.etf.pp1.ast.TermSingle;
import rs.ac.bg.etf.pp1.ast.VarArray;
import rs.ac.bg.etf.pp1.ast.VarDeclaration;
import rs.ac.bg.etf.pp1.ast.VarNormal;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.ac.bg.etf.pp1.ast.Type;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class SemanticAnalyzer extends VisitorAdaptor {
	
	// ubacivanje bool tipa u tabelu simbola
	private static Struct boolType = Tab.insert(Obj.Type, "bool", new Struct(5)).getType();
	
	int nVars;
	boolean errorDetected = false;
	boolean returnValue = false;
	
	private ArrayList<Variable> declarationVariables = new ArrayList<Variable>();
	private Obj currentMethod = null;
	private String currentDesignatorName;
	private Struct assignmentRight = null;
	 
	Logger log = Logger.getLogger(getClass());
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	// *** VISIT METODE ***
	
	// Program
	public void visit(Program program) { 
		nVars = Tab.currentScope.getnVars();
    	Tab.chainLocalSymbols(program.getProgName().obj);
    	Tab.closeScope();
    	report_info("Kraj obrade programa '" + program.getProgName().getProgName() + "'", program);
	}
	
	// ProgName
	public void visit(ProgName progName){
    	progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
    	Tab.openScope();
    	report_info("Pocetak obrade programa '" + progName.getProgName() + "'", progName);
    }
	
	// Type
	public void visit(Type type) {
    	Obj typeNode = Tab.find(type.getTypeName());
		if (typeNode == Tab.noObj) {
			report_error("Semanticka greska - nije pronadjen tip '" + type.getTypeName() + "' u tabeli simbola!", null);
			type.struct = Tab.noType;
		} 
		else {
			if (Obj.Type == typeNode.getKind()) {
				type.struct = typeNode.getType();
			} 
			else {
				report_error("Semanticka greska - '" + type.getTypeName() + "' ne predstavlja tip", type);
				type.struct = Tab.noType;
			}
		}
    }
	
	// VarDecl
	public void visit(VarDeclaration varDeclaration) {
		Struct type = varDeclaration.getType().struct;
		for (Variable var : declarationVariables) {
	    	if (var.getArray()) {
	    		report_info("Deklarisan niz '" + var.getName() + "'", varDeclaration); 
	    		Obj obj = Tab.insert(Obj.Var, var.getName(), new Struct(Struct.Array, type));
//	    		obj.setFpPos(-1); // redni broj argumenta u definiciji metode? samo nivo C?
	    	} else {
	    		report_info("Deklarisana promenljiva '" + var.getName() + "'", varDeclaration);
	    		Obj obj = Tab.insert(Obj.Var, var.getName(), type);
//	    		obj.setFpPos(-1);
	    	}    	
    	}
    	declarationVariables.clear();
	}
	
	// VarPart
	public void visit(VarNormal varNormal) {
		if (Tab.find(varNormal.getVarName()) == Tab.noObj) {
			String varName = varNormal.getVarName();
			for (Variable var : declarationVariables) {
				if(var.getName().equals(varName)) {
					report_error("Semanticka greska - '" + varNormal.getVarName() + "' je vec deklarisano", varNormal);
					return;
				}
			}
			declarationVariables.add(new Variable(varNormal.getVarName(), false, null));			
		} else { 			
			report_error("Semanticka greska - '" + varNormal.getVarName() + "' je vec deklarisano", varNormal);
		}
	}
	
	public void visit(VarArray varArray) { 
		if (Tab.find(varArray.getVarName()) == Tab.noObj) { // provera da li vec postoji u tabeli simbola
			String varName = varArray.getVarName();
			for (Variable var : declarationVariables) {
				if(var.getName().equals(varName)) { // provera da li je vec deklarisan u trenutnom nizu deklaracija
					report_error("Semanticka greska - '" + varArray.getVarName() + "' je vec deklarisano", varArray);
					return;
				}
			}
			declarationVariables.add(new Variable(varArray.getVarName(), true, null));			
		} else { 
			report_error("Semanticka greska - '" + varArray.getVarName() + "' je vec deklarisano", varArray);
		}
    }
    
	// GlobalVarDecl
    public void visit(GlobalVarDeclaration globalVarDeclaration) {
		Struct type = globalVarDeclaration.getType().struct;
		for (Variable var : declarationVariables) {
	    	if (var.getArray()) {
	    		report_info("Deklarisan globalni niz '" + var.getName() + "'", globalVarDeclaration); 
	    		Obj obj = Tab.insert(Obj.Var, var.getName(), new Struct(Struct.Array, type));
//	    		obj.setFpPos(-1); // redni broj argumenta u definiciji metode?
	    	} else {
	    		report_info("Deklarisana globalna promenljiva '" + var.getName() + "'", globalVarDeclaration);
	    		Obj obj = Tab.insert(Obj.Var, var.getName(), type);
//	    		obj.setFpPos(-1);
	    	}    	
    	}
    	declarationVariables.clear();
	}
	
    // GlobalVarPart
    public void visit(GlobalVarNormal globalVarNormal) {
		if (Tab.find(globalVarNormal.getVarName()) == Tab.noObj) {
			String varName = globalVarNormal.getVarName();
			for (Variable var : declarationVariables) {
				if(var.getName().equals(varName)) {
					report_error("Semanticka greska - '" + globalVarNormal.getVarName() + "' je vec deklarisano", globalVarNormal);
					return;
				}
			}
			declarationVariables.add(new Variable(globalVarNormal.getVarName(), false, null));			
		} else { 			
			report_error("Semanticka greska - '" + globalVarNormal.getVarName() + "' je vec deklarisano", globalVarNormal);
		}
	}
    
	public void visit(GlobalVarArray globalVarArray) { 
		if (Tab.find(globalVarArray.getVarName()) == Tab.noObj) { // provera da li vec postoji u tabeli simbola
			String varName = globalVarArray.getVarName();
			for (Variable var : declarationVariables) {
				if(var.getName().equals(varName)) { // provera da li je vec deklarisan u trenutnom nizu deklaracija
					report_error("Semanticka greska - '" + globalVarArray.getVarName() + "' je vec deklarisano", globalVarArray);
					return;
				}
			}
			declarationVariables.add(new Variable(globalVarArray.getVarName(), true, null));			
		} else { 
			report_error("Semanticka greska - '" + globalVarArray.getVarName() + "' je vec deklarisano", globalVarArray);
		}
	}
    
	// ConstDecl
    public void visit(ConstDeclaration constDeclaration) {	
		Struct type = constDeclaration.getType().struct;
		for (Variable var : declarationVariables) {
			report_info("Deklarisana konstanta '" + var.getName() + "'", constDeclaration);
			Tab.insert(Obj.Con, var.getName(), type);
		}
		declarationVariables.clear();
	}	

    // ConstPart
	public void visit(ConstPart constPart) { 
		if (Tab.find(constPart.getConstName()) == Tab.noObj) { // ako vec postoji u tabeli simbola
			String varName = constPart.getConstName();
			for (Variable var : declarationVariables) {
				if(var.getName().equals(varName)) {
					report_error("Semanticka greska - '" + constPart.getConstName() + "' je vec deklarisano", constPart);
					return;
				}
			}
			declarationVariables.add(new Variable(constPart.getConstName(), false, null));
		} else {			
			report_error("Semanticka greska - '" + constPart.getConstName() + "' je vec deklarisano", constPart);
		}
	}
	
	// Value
	public void visit(NumConst numConst) { // CEMU SLUZI OVO???
		numConst.struct = Tab.intType;
	}
	
	public void visit(BoolConst boolConst) { 
		boolConst.struct = boolType;
	}
	
	public void visit(CharConst charConst) { 
		charConst.struct = Tab.charType;
	}  
	
	// MethVoidName
	public void visit(MethodVoidName methodVoidName) { 
		currentMethod = Tab.insert(Obj.Meth, methodVoidName.getMethodName(), Tab.noType);
		// mListOfMethods.add(new Method(methodVoidName.getMethodName()));		
		Tab.openScope();
		report_info("Pocetak obrade metode " + methodVoidName.getMethodName(), methodVoidName);
	}
	
	// MethodDec
	public void visit(MethodVoidDeclaration methodVoidDeclaration) { 
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		report_info("Kraj obrade metode " + currentMethod.getName(), methodVoidDeclaration);
		currentMethod = null;
		returnValue = false;
	}
	
	// MethTypeName
	public void visit(MethodTypeName methodTypeName) { 
		currentMethod = Tab.insert(Obj.Meth, methodTypeName.getMethodName(), methodTypeName.getType().struct);
		// mListOfMethods.add(new Method(methodVoidName.getMethodName()));		
		Tab.openScope();
		report_info("Pocetak obrade metode " + methodTypeName.getMethodName(), methodTypeName);
	}
	
	// MethodTypeDecl
	public void visit(MethodTypeDeclaration methodTypeDeclaration) { 
		if (!returnValue) {
			report_error("Semanticka greska - metoda '" + currentMethod.getName() + "' treba da ima povratnu vrednost!", null);
		}
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		report_info("Kraj obrade metode " + currentMethod.getName(), methodTypeDeclaration);
		currentMethod = null;
		returnValue = false;
	}
	
	// FormParam
	public void visit(ParamNormal paramNormal) {
		if (Tab.find(paramNormal.getParamName()) == Tab.noObj) { // isto kao i gore
	    	Obj obj = Tab.insert(Obj.Var, paramNormal.getParamName(), paramNormal.getType().struct);
//	    	if (mListOfMethods.size() > 0) {
//	    		mListOfMethods.get(mListOfMethods.size() - 1).getParameters().add(obj.getType());
//	    	}
//	    	obj.setFpPos(currentMethod.getLevel());
	    	currentMethod.setLevel(currentMethod.getLevel() + 1);
	    	report_info("Formalni parametar funkcije " + currentMethod.getName() + ": '" + paramNormal.getParamName() + "'", paramNormal);
		} else {
			report_error("Semanticka greska - '" + paramNormal.getParamName() + "' vec postoji", paramNormal);
		}
    }
	
	public void visit(ParamArray paramArray) {
		if (Tab.find(paramArray.getParamName()) == Tab.noObj){ // ovo treba da se trazi samo u trenutnom opsegu, jer moze da se sakriva ime iz spoljasnjeg opsega
			Obj obj = Tab.insert(Obj.Var, paramArray.getParamName(), new Struct(Struct.Array, paramArray.getType().struct));
//			if (mListOfMethods.size() > 0) {
//				mListOfMethods.get(mListOfMethods.size() - 1).getParameters().add(obj.getType());
//			}			
//			obj.setFpPos(currentMethod.getLevel()); // ne kapiram??
			currentMethod.setLevel(currentMethod.getLevel() + 1);
			report_info("Formalni parametar funkcije " + currentMethod.getName() + ": '" + paramArray.getParamName() + "'", paramArray);
		} else {
			report_error("Semanticka greska - '" + paramArray.getParamName() + "' vec postoji", paramArray);
		}
	}
	
	// Statement
    public void visit(StmtReturnExpr stmtReturnExpr) {
    	returnValue = true;
    	if (currentMethod.getType() == Tab.noType) {
    		report_error("Semanticka greska - return naredba u funkciji koja nema povratnu vrednost", stmtReturnExpr);
    		return;
    	}
    	if (!currentMethod.getType().compatibleWith(stmtReturnExpr.getExpr().struct)) {
    		report_error("Semanticka greska - tip povratne vrednosti metode i tip vrednosti izraza u return naredbi se ne slazu", stmtReturnExpr);
    		return;
    	}
    }
    
    // DesignatorName
    public void visit(DesignatorIdent designatorIdent) {
    	currentDesignatorName = designatorIdent.getDesignatorIdent();
    	if (Tab.find(currentDesignatorName) == Tab.noObj) { 
			report_error("Semanticka greska - '" + currentDesignatorName + "' nije deklarisano", designatorIdent);
		}
    }
	
	// Expr1
	public void visit(ExprNegMulti exprNegMulti) { 
		exprNegMulti.struct = exprNegMulti.getTerm().struct;
	}
	
	public void visit(ExprNegSingle exprNegSingle) {
		exprNegSingle.struct = exprNegSingle.getTerm().struct;
	}
	
	public void visit(ExprSingle exprSingle) { 
		exprSingle.struct = exprSingle.getTerm().struct;
	}
	
	public void visit(ExprMulti exprMulti) { 
		exprMulti.struct = exprMulti.getTerm().struct;
	}
	
	// Term
	public void visit(TermMulti termMulti) { 
		termMulti.struct = termMulti.getFactor().struct;
	}
	
	public void visit(TermSingle termSingle) { 
		termSingle.struct = termSingle.getFactor().struct;
	}
	
	// Factor
	public void visit(FuncCallParams funcCallParams){
    	Obj func = funcCallParams.getDesignator().obj;
    	if (Obj.Meth == func.getKind()) {
    		report_info("Pronadjen poziv funkcije '" + func.getName() + "'", funcCallParams);
    		funcCallParams.struct = func.getType();
    	} else {
    		report_error("Semanticka greska - ime '" + func.getName() + "' nije funkcija", funcCallParams);
			funcCallParams.struct = Tab.noType;
    	}
    }
	
	public void visit(FuncCall funcCall){
    	Obj func = funcCall.getDesignator().obj;
    	if (Obj.Meth == func.getKind()) {
			report_info("Pronadjen poziv funkcije '" + func.getName() + "'", funcCall);
			funcCall.struct = func.getType(); // kako ovde uzima tip??
    	} else {
			report_error("Semanticka greska - ime '" + func.getName() + "' nije funkcija", funcCall);
			funcCall.struct = Tab.noType;
    	}
    }
	
	public void visit(FactorDesignator factorDesignator) { // puca NULL prilikom pristupa func(1), obj = NULL
//		 factorDesignator.struct = factorDesignator.getDesignator().obj.getType();
	}
	
	public void visit(FactorNumConst factorNumConst) { 
		factorNumConst.struct = Tab.intType;
	}
	
	public void visit(FactorCharConst factorCharConst) { 
		factorCharConst.struct = Tab.charType;
	}
	
	public void visit(FactorBoolConst factorBoolConst) { 
		factorBoolConst.struct = boolType;
	}

	// faktor za kljucnu rec NEW [ NE RADI SE ] ????
	public void visit(FactorNew factorNew) {
		factorNew.struct = factorNew.getType().struct;
	} 
	
	public void visit(FactorNewArray factorNewArray) {
		if (factorNewArray.getExpr().struct == Tab.intType) {
			factorNewArray.struct = new Struct(Struct.Array, factorNewArray.getType().struct);			
		} else {
			report_error("Semanticka greska - tip za alociranje niza moze biti jedino int", factorNewArray);
		}
	}
	
	public void visit(FactorExpr factorExpr) { 
		factorExpr.struct = factorExpr.getExpr().struct;
	}

	// DesignatorStatement
	public void visit(DesignatorAssignment designatorAssignment) {	// puca NullPointerException, obj = null
		Struct assignmentLeft = designatorAssignment.getDesignator().obj.getType();
		if (!assignmentRight.assignableTo(assignmentLeft)) {
			report_error("Semanticka greska - nekompatibilni tipovi za dodelu vrednosti", designatorAssignment);
		}
	}
	
	// Assignment
	public void visit(AssignmentExpr assignmentExpr) {
		assignmentRight = assignmentExpr.getExpr().struct;		
	}
	
	// DesignatorPart
    public void visit(DesignatorPartField designatorPartField) {
//    	Obj obj = Tab.find(designatorPartField.getID());
//		if (obj == Tab.noObj) { 
//			report_error("Semanticka greska - ime " + designatorPartField.getID() + " nije deklarisano", designatorPartField);
//		}		
//		designatorPartField.obj = obj;
	} 
	
	public void visit(DesignatorPartArray designatorPartArray) { 
		Obj obj = Tab.find(currentDesignatorName);
		if (Tab.find(currentDesignatorName) != Tab.noObj) {  
			if (designatorPartArray.getExpr().struct != Tab.intType) { // opet ne prepoznaje int
				report_error("Semanticka greska - invalidan pristup elementu niza '" + currentDesignatorName + "'", designatorPartArray);
			} else if (obj.getType().getKind() != Struct.Array){
				report_error("Semanticka greska - ime '" + currentDesignatorName + "' nije niz", designatorPartArray);
			} else {
				report_info("Pristup elementu niza '" + currentDesignatorName + "'", designatorPartArray);
			}
		}
		designatorPartArray.obj = new Obj(Obj.Elem, obj.getName(), obj.getType().getElemType());	
	}
}
