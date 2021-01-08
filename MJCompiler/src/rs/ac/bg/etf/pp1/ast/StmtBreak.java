// generated with ast extension for cup
// version 0.8
// 8/0/2021 19:50:34


package rs.ac.bg.etf.pp1.ast;

public class StmtBreak extends Statement {

    public StmtBreak () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StmtBreak(\n");

        buffer.append(tab);
        buffer.append(") [StmtBreak]");
        return buffer.toString();
    }
}
