// generated with ast extension for cup
// version 0.8
// 27/11/2020 17:21:55


package rs.ac.bg.etf.pp1.ast;

public class StmtIf extends Statement {

    private StatementIf StatementIf;
    private StatementIfBody StatementIfBody;

    public StmtIf (StatementIf StatementIf, StatementIfBody StatementIfBody) {
        this.StatementIf=StatementIf;
        if(StatementIf!=null) StatementIf.setParent(this);
        this.StatementIfBody=StatementIfBody;
        if(StatementIfBody!=null) StatementIfBody.setParent(this);
    }

    public StatementIf getStatementIf() {
        return StatementIf;
    }

    public void setStatementIf(StatementIf StatementIf) {
        this.StatementIf=StatementIf;
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
        if(StatementIf!=null) StatementIf.accept(visitor);
        if(StatementIfBody!=null) StatementIfBody.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StatementIf!=null) StatementIf.traverseTopDown(visitor);
        if(StatementIfBody!=null) StatementIfBody.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StatementIf!=null) StatementIf.traverseBottomUp(visitor);
        if(StatementIfBody!=null) StatementIfBody.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StmtIf(\n");

        if(StatementIf!=null)
            buffer.append(StatementIf.toString("  "+tab));
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
