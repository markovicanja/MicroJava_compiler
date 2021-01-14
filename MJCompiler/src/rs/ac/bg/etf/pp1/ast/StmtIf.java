// generated with ast extension for cup
// version 0.8
// 14/0/2021 23:12:10


package rs.ac.bg.etf.pp1.ast;

public class StmtIf extends Statement {

    private IfKw IfKw;
    private StatementIfBody StatementIfBody;

    public StmtIf (IfKw IfKw, StatementIfBody StatementIfBody) {
        this.IfKw=IfKw;
        if(IfKw!=null) IfKw.setParent(this);
        this.StatementIfBody=StatementIfBody;
        if(StatementIfBody!=null) StatementIfBody.setParent(this);
    }

    public IfKw getIfKw() {
        return IfKw;
    }

    public void setIfKw(IfKw IfKw) {
        this.IfKw=IfKw;
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
        if(IfKw!=null) IfKw.accept(visitor);
        if(StatementIfBody!=null) StatementIfBody.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfKw!=null) IfKw.traverseTopDown(visitor);
        if(StatementIfBody!=null) StatementIfBody.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfKw!=null) IfKw.traverseBottomUp(visitor);
        if(StatementIfBody!=null) StatementIfBody.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StmtIf(\n");

        if(IfKw!=null)
            buffer.append(IfKw.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

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
