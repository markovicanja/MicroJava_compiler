// generated with ast extension for cup
// version 0.8
// 8/0/2021 15:58:56


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Mulop Mulop);
    public void visit(ProgramDeclarations ProgramDeclarations);
    public void visit(Relop Relop);
    public void visit(MethodVoidDecl MethodVoidDecl);
    public void visit(MethTypeName MethTypeName);
    public void visit(DoHeader DoHeader);
    public void visit(StatementList StatementList);
    public void visit(VarPart VarPart);
    public void visit(Addop Addop);
    public void visit(ProgramDeclaration ProgramDeclaration);
    public void visit(Factor Factor);
    public void visit(MethodTypeDecl MethodTypeDecl);
    public void visit(CondTerm CondTerm);
    public void visit(VarList VarList);
    public void visit(ConstList ConstList);
    public void visit(GlobalVarDecl GlobalVarDecl);
    public void visit(Designator Designator);
    public void visit(StatementIf StatementIf);
    public void visit(Term Term);
    public void visit(Condition Condition);
    public void visit(MethodDec MethodDec);
    public void visit(CaseList CaseList);
    public void visit(Value Value);
    public void visit(FormParams FormParams);
    public void visit(IfCondition IfCondition);
    public void visit(DesignatorName DesignatorName);
    public void visit(StatementIfBody StatementIfBody);
    public void visit(VarDeclList VarDeclList);
    public void visit(Expr Expr);
    public void visit(Expr1 Expr1);
    public void visit(ActPars ActPars);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(Assignment Assignment);
    public void visit(GlobalVarPart GlobalVarPart);
    public void visit(Statement Statement);
    public void visit(VarDecl VarDecl);
    public void visit(ClassDecl ClassDecl);
    public void visit(ConstDecl ConstDecl);
    public void visit(CondFact CondFact);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(GlobalVarList GlobalVarList);
    public void visit(FormParam FormParam);
    public void visit(MethVoidName MethVoidName);
    public void visit(MulopMod MulopMod);
    public void visit(MulopDiv MulopDiv);
    public void visit(MulopMul MulopMul);
    public void visit(AddopMinus AddopMinus);
    public void visit(AddopPlus AddopPlus);
    public void visit(RelopLE RelopLE);
    public void visit(RelopLT RelopLT);
    public void visit(RelopGE RelopGE);
    public void visit(RelopGT RelopGT);
    public void visit(RelopNE RelopNE);
    public void visit(RelopEQ RelopEQ);
    public void visit(DesignatorSimple DesignatorSimple);
    public void visit(DesignatorArray DesignatorArray);
    public void visit(FactorExpr FactorExpr);
    public void visit(FactorNewArray FactorNewArray);
    public void visit(FactorNew FactorNew);
    public void visit(FactorBoolConst FactorBoolConst);
    public void visit(FactorCharConst FactorCharConst);
    public void visit(FactorNumConst FactorNumConst);
    public void visit(FactorDesignator FactorDesignator);
    public void visit(FuncCall FuncCall);
    public void visit(FuncCallParams FuncCallParams);
    public void visit(TermSingle TermSingle);
    public void visit(TermMulop TermMulop);
    public void visit(ExprSingle ExprSingle);
    public void visit(ExprAddop ExprAddop);
    public void visit(ExprNeg ExprNeg);
    public void visit(ExprOne ExprOne);
    public void visit(ExprTernary ExprTernary);
    public void visit(CondFactOne CondFactOne);
    public void visit(CondFactTwo CondFactTwo);
    public void visit(CondTermOne CondTermOne);
    public void visit(CondTermTwo CondTermTwo);
    public void visit(CondOne CondOne);
    public void visit(CondTwo CondTwo);
    public void visit(ActParsSingle ActParsSingle);
    public void visit(ActParsMulti ActParsMulti);
    public void visit(AssignmentError AssignmentError);
    public void visit(AssignmentExpr AssignmentExpr);
    public void visit(DesignatorDecrement DesignatorDecrement);
    public void visit(DesignatorIncrement DesignatorIncrement);
    public void visit(DesignatorMethodCall1 DesignatorMethodCall1);
    public void visit(DesignatorMethodCall0 DesignatorMethodCall0);
    public void visit(DesignatorAssignment DesignatorAssignment);
    public void visit(CasePart CasePart);
    public void visit(CaseSingle CaseSingle);
    public void visit(CaseMulti CaseMulti);
    public void visit(IfCondError IfCondError);
    public void visit(IfCond IfCond);
    public void visit(StmtIfBody StmtIfBody);
    public void visit(DoHead DoHead);
    public void visit(StmtDoWhile StmtDoWhile);
    public void visit(StmtIf StmtIf);
    public void visit(StmtIfElse StmtIfElse);
    public void visit(Stmt Stmt);
    public void visit(StmtPrint StmtPrint);
    public void visit(StmtPrintNumConst StmtPrintNumConst);
    public void visit(StmtRead StmtRead);
    public void visit(StmtReturn StmtReturn);
    public void visit(StmtReturnExpr StmtReturnExpr);
    public void visit(StmtContinue StmtContinue);
    public void visit(StmtBreak StmtBreak);
    public void visit(StmtSwitch StmtSwitch);
    public void visit(StmtSwitchNoCase StmtSwitchNoCase);
    public void visit(StmtDesign StmtDesign);
    public void visit(StatementSingle StatementSingle);
    public void visit(StatementMulti StatementMulti);
    public void visit(Type Type);
    public void visit(ParamError ParamError);
    public void visit(ParamArray ParamArray);
    public void visit(ParamNormal ParamNormal);
    public void visit(ParamSingle ParamSingle);
    public void visit(ParamMulti ParamMulti);
    public void visit(MethodTypeName MethodTypeName);
    public void visit(MethodType7 MethodType7);
    public void visit(MethodType6 MethodType6);
    public void visit(MethodType5 MethodType5);
    public void visit(MethodType4 MethodType4);
    public void visit(MethodType3 MethodType3);
    public void visit(MethodType2 MethodType2);
    public void visit(MethodType1 MethodType1);
    public void visit(MethodType0 MethodType0);
    public void visit(MethodVoidName MethodVoidName);
    public void visit(MethodVoid7 MethodVoid7);
    public void visit(MethodVoid6 MethodVoid6);
    public void visit(MethodVoid5 MethodVoid5);
    public void visit(MethodVoid4 MethodVoid4);
    public void visit(MethodVoid3 MethodVoid3);
    public void visit(MethodVoid2 MethodVoid2);
    public void visit(MethodVoid1 MethodVoid1);
    public void visit(MethodVoid0 MethodVoid0);
    public void visit(MethodTypeDeclaration MethodTypeDeclaration);
    public void visit(MethodVoidDeclaration MethodVoidDeclaration);
    public void visit(MethodDeclSingle MethodDeclSingle);
    public void visit(MethodDecMulti MethodDecMulti);
    public void visit(VarDeclSingle VarDeclSingle);
    public void visit(VarDeclMulti VarDeclMulti);
    public void visit(ClassDecl7 ClassDecl7);
    public void visit(ClassDecl6 ClassDecl6);
    public void visit(ClassDecl5 ClassDecl5);
    public void visit(ClassDecl4 ClassDecl4);
    public void visit(ClassDecl3 ClassDecl3);
    public void visit(ClassDecl2 ClassDecl2);
    public void visit(ClassDecl1 ClassDecl1);
    public void visit(ClassDecl0 ClassDecl0);
    public void visit(VarError VarError);
    public void visit(GlobalVarArray GlobalVarArray);
    public void visit(GlobalVarNormal GlobalVarNormal);
    public void visit(GlobalVarSingle GlobalVarSingle);
    public void visit(GlobalVarMulti GlobalVarMulti);
    public void visit(GlobalVarDeclaration GlobalVarDeclaration);
    public void visit(VarArray VarArray);
    public void visit(VarNormal VarNormal);
    public void visit(VarSingle VarSingle);
    public void visit(VarMulti VarMulti);
    public void visit(VarDeclaration VarDeclaration);
    public void visit(CharConst CharConst);
    public void visit(BoolConst BoolConst);
    public void visit(NumConst NumConst);
    public void visit(ConstPart ConstPart);
    public void visit(ConstSingle ConstSingle);
    public void visit(ConstMulti ConstMulti);
    public void visit(ConstDeclaration ConstDeclaration);
    public void visit(PartClassDeclaration PartClassDeclaration);
    public void visit(PartVarDeclaration PartVarDeclaration);
    public void visit(PartConstDeclaration PartConstDeclaration);
    public void visit(ProgramDeclEps ProgramDeclEps);
    public void visit(ProgramDeclMulti ProgramDeclMulti);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
