// generated with ast extension for cup
// version 0.8
// 6/0/2021 22:20:46


package rs.ac.bg.etf.pp1.ast;

public class DesignatorListMulti extends DesignatorList {

    private DesignatorList DesignatorList;
    private DesignatorPart DesignatorPart;

    public DesignatorListMulti (DesignatorList DesignatorList, DesignatorPart DesignatorPart) {
        this.DesignatorList=DesignatorList;
        if(DesignatorList!=null) DesignatorList.setParent(this);
        this.DesignatorPart=DesignatorPart;
        if(DesignatorPart!=null) DesignatorPart.setParent(this);
    }

    public DesignatorList getDesignatorList() {
        return DesignatorList;
    }

    public void setDesignatorList(DesignatorList DesignatorList) {
        this.DesignatorList=DesignatorList;
    }

    public DesignatorPart getDesignatorPart() {
        return DesignatorPart;
    }

    public void setDesignatorPart(DesignatorPart DesignatorPart) {
        this.DesignatorPart=DesignatorPart;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorList!=null) DesignatorList.accept(visitor);
        if(DesignatorPart!=null) DesignatorPart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorList!=null) DesignatorList.traverseTopDown(visitor);
        if(DesignatorPart!=null) DesignatorPart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorList!=null) DesignatorList.traverseBottomUp(visitor);
        if(DesignatorPart!=null) DesignatorPart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorListMulti(\n");

        if(DesignatorList!=null)
            buffer.append(DesignatorList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorPart!=null)
            buffer.append(DesignatorPart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorListMulti]");
        return buffer.toString();
    }
}
