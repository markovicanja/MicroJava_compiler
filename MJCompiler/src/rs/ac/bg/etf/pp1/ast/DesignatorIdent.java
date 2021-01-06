// generated with ast extension for cup
// version 0.8
// 6/0/2021 22:20:46


package rs.ac.bg.etf.pp1.ast;

public class DesignatorIdent extends DesignatorName {

    private String designatorIdent;

    public DesignatorIdent (String designatorIdent) {
        this.designatorIdent=designatorIdent;
    }

    public String getDesignatorIdent() {
        return designatorIdent;
    }

    public void setDesignatorIdent(String designatorIdent) {
        this.designatorIdent=designatorIdent;
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
        buffer.append("DesignatorIdent(\n");

        buffer.append(" "+tab+designatorIdent);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorIdent]");
        return buffer.toString();
    }
}
