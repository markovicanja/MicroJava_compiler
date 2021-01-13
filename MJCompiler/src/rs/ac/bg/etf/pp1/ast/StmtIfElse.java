// generated with ast extension for cup
// version 0.8
// 13/0/2021 15:10:24


package rs.ac.bg.etf.pp1.ast;

public class StmtIfElse extends Statement {

    private IfKw IfKw;
    private StatementIfBody StatementIfBody;
    private Statement Statement;

    public StmtIfElse (IfKw IfKw, StatementIfBody StatementIfBody, Statement Statement) {
        this.IfKw=IfKw;
        if(IfKw!=null) IfKw.setParent(this);
        this.StatementIfBody=StatementIfBody;
        if(StatementIfBody!=null) StatementIfBody.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
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

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfKw!=null) IfKw.accept(visitor);
        if(StatementIfBody!=null) StatementIfBody.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfKw!=null) IfKw.traverseTopDown(visitor);
        if(StatementIfBody!=null) StatementIfBody.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfKw!=null) IfKw.traverseBottomUp(visitor);
        if(StatementIfBody!=null) StatementIfBody.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StmtIfElse(\n");

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

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StmtIfElse]");
        return buffer.toString();
    }
}
