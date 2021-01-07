// generated with ast extension for cup
// version 0.8
// 7/0/2021 22:39:39


package rs.ac.bg.etf.pp1.ast;

public class StmtIf extends Statement {

    private StatementIfBody StatementIfBody;

    public StmtIf (StatementIfBody StatementIfBody) {
        this.StatementIfBody=StatementIfBody;
        if(StatementIfBody!=null) StatementIfBody.setParent(this);
    }

    public StatementIfBody getStatementIfBody() {
        return StatementIfBody;
    }

    public void setStatementIfBody(StatementIfBody StatementIfBody) {
        this.StatementIfBody=StatementIfBody;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StatementIfBody!=null) StatementIfBody.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StatementIfBody!=null) StatementIfBody.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StatementIfBody!=null) StatementIfBody.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StmtIf(\n");

        if(StatementIfBody!=null)
            buffer.append(StatementIfBody.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StmtIf]");
        return buffer.toString();
    }
}
