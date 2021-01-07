// generated with ast extension for cup
// version 0.8
// 7/0/2021 22:39:39


package rs.ac.bg.etf.pp1.ast;

public class MethodVoid6 extends MethodVoidDecl {

    private MethVoidName MethVoidName;
    private StatementList StatementList;

    public MethodVoid6 (MethVoidName MethVoidName, StatementList StatementList) {
        this.MethVoidName=MethVoidName;
        if(MethVoidName!=null) MethVoidName.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public MethVoidName getMethVoidName() {
        return MethVoidName;
    }

    public void setMethVoidName(MethVoidName MethVoidName) {
        this.MethVoidName=MethVoidName;
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
        if(MethVoidName!=null) MethVoidName.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethVoidName!=null) MethVoidName.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethVoidName!=null) MethVoidName.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodVoid6(\n");

        if(MethVoidName!=null)
            buffer.append(MethVoidName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodVoid6]");
        return buffer.toString();
    }
}
