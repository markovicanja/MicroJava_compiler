// generated with ast extension for cup
// version 0.8
// 11/0/2021 21:3:43


package rs.ac.bg.etf.pp1.ast;

public class MethodType0 extends MethodTypeDecl {

    private MethTypeName MethTypeName;
    private FormParams FormParams;
    private VarDeclList VarDeclList;
    private StatementList StatementList;

    public MethodType0 (MethTypeName MethTypeName, FormParams FormParams, VarDeclList VarDeclList, StatementList StatementList) {
        this.MethTypeName=MethTypeName;
        if(MethTypeName!=null) MethTypeName.setParent(this);
        this.FormParams=FormParams;
        if(FormParams!=null) FormParams.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public MethTypeName getMethTypeName() {
        return MethTypeName;
    }

    public void setMethTypeName(MethTypeName MethTypeName) {
        this.MethTypeName=MethTypeName;
    }

    public FormParams getFormParams() {
        return FormParams;
    }

    public void setFormParams(FormParams FormParams) {
        this.FormParams=FormParams;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethTypeName!=null) MethTypeName.accept(visitor);
        if(FormParams!=null) FormParams.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethTypeName!=null) MethTypeName.traverseTopDown(visitor);
        if(FormParams!=null) FormParams.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethTypeName!=null) MethTypeName.traverseBottomUp(visitor);
        if(FormParams!=null) FormParams.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodType0(\n");

        if(MethTypeName!=null)
            buffer.append(MethTypeName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormParams!=null)
            buffer.append(FormParams.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodType0]");
        return buffer.toString();
    }
}
