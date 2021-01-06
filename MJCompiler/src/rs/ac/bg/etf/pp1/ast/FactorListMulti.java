// generated with ast extension for cup
// version 0.8
// 6/0/2021 22:20:46


package rs.ac.bg.etf.pp1.ast;

public class FactorListMulti extends FactorList {

    private FactorList FactorList;
    private FactorPart FactorPart;

    public FactorListMulti (FactorList FactorList, FactorPart FactorPart) {
        this.FactorList=FactorList;
        if(FactorList!=null) FactorList.setParent(this);
        this.FactorPart=FactorPart;
        if(FactorPart!=null) FactorPart.setParent(this);
    }

    public FactorList getFactorList() {
        return FactorList;
    }

    public void setFactorList(FactorList FactorList) {
        this.FactorList=FactorList;
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
        if(FactorList!=null) FactorList.accept(visitor);
        if(FactorPart!=null) FactorPart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FactorList!=null) FactorList.traverseTopDown(visitor);
        if(FactorPart!=null) FactorPart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FactorList!=null) FactorList.traverseBottomUp(visitor);
        if(FactorPart!=null) FactorPart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactorListMulti(\n");

        if(FactorList!=null)
            buffer.append(FactorList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FactorPart!=null)
            buffer.append(FactorPart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorListMulti]");
        return buffer.toString();
    }
}
