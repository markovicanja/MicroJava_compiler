// generated with ast extension for cup
// version 0.8
// 27/11/2020 17:21:55


package rs.ac.bg.etf.pp1.ast;

public class RelopLE extends Relop {

    public RelopLE () {
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
        buffer.append("RelopLE(\n");

        buffer.append(tab);
        buffer.append(") [RelopLE]");
        return buffer.toString();
    }
}