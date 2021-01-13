// generated with ast extension for cup
// version 0.8
// 13/0/2021 15:10:24


package rs.ac.bg.etf.pp1.ast;

public class StmtDoWhile extends Statement {

    private DoKw DoKw;
    private StatementWhileBody StatementWhileBody;
    private Condition Condition;

    public StmtDoWhile (DoKw DoKw, StatementWhileBody StatementWhileBody, Condition Condition) {
        this.DoKw=DoKw;
        if(DoKw!=null) DoKw.setParent(this);
        this.StatementWhileBody=StatementWhileBody;
        if(StatementWhileBody!=null) StatementWhileBody.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
    }

    public DoKw getDoKw() {
        return DoKw;
    }

    public void setDoKw(DoKw DoKw) {
        this.DoKw=DoKw;
    }

    public StatementWhileBody getStatementWhileBody() {
        return StatementWhileBody;
    }

    public void setStatementWhileBody(StatementWhileBody StatementWhileBody) {
        this.StatementWhileBody=StatementWhileBody;
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DoKw!=null) DoKw.accept(visitor);
        if(StatementWhileBody!=null) StatementWhileBody.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DoKw!=null) DoKw.traverseTopDown(visitor);
        if(StatementWhileBody!=null) StatementWhileBody.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DoKw!=null) DoKw.traverseBottomUp(visitor);
        if(StatementWhileBody!=null) StatementWhileBody.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StmtDoWhile(\n");

        if(DoKw!=null)
            buffer.append(DoKw.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementWhileBody!=null)
            buffer.append(StatementWhileBody.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StmtDoWhile]");
        return buffer.toString();
    }
}
