// generated with ast extension for cup
// version 0.8
// 7/0/2021 22:39:39


package rs.ac.bg.etf.pp1.ast;

public class FactorListSingle extends FactorList {

    private FactorPart FactorPart;

    public FactorListSingle (FactorPart FactorPart) {
        this.FactorPart=FactorPart;
        if(FactorPart!=null) FactorPart.setParent(this);
    }

    public FactorPart getFactorPart() {
        return FactorPart;
    }

    public void setFactorPart(FactorPart FactorPart) {
        this.FactorPart=FactorPart;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FactorPart!=null) FactorPart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FactorPart!=null) FactorPart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FactorPart!=null) FactorPart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactorListSingle(\n");

        if(FactorPart!=null)
            buffer.append(FactorPart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorListSingle]");
        return buffer.toString();
    }
}
