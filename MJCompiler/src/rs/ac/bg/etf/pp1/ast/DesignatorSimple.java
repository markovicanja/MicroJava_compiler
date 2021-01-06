// generated with ast extension for cup
// version 0.8
// 6/0/2021 22:20:46


package rs.ac.bg.etf.pp1.ast;

public class DesignatorSimple extends Designator {

    private String designatorName;

    public DesignatorSimple (String designatorName) {
        this.designatorName=designatorName;
    }

    public String getDesignatorName() {
        return designatorName;
    }

    public void setDesignatorName(String designatorName) {
        this.designatorName=designatorName;
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
        buffer.append("DesignatorSimple(\n");

        buffer.append(" "+tab+designatorName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorSimple]");
        return buffer.toString();
    }
}
