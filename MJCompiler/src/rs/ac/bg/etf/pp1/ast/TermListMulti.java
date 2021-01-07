// generated with ast extension for cup
// version 0.8
// 7/0/2021 22:39:39


package rs.ac.bg.etf.pp1.ast;

public class TermListMulti extends TermList {

    private TermList TermList;
    private TermPart TermPart;

    public TermListMulti (TermList TermList, TermPart TermPart) {
        this.TermList=TermList;
        if(TermList!=null) TermList.setParent(this);
        this.TermPart=TermPart;
        if(TermPart!=null) TermPart.setParent(this);
    }

    public TermList getTermList() {
        return TermList;
    }

    public void setTermList(TermList TermList) {
        this.TermList=TermList;
    }

    public TermPart getTermPart() {
        return TermPart;
    }

    public void setTermPart(TermPart TermPart) {
        this.TermPart=TermPart;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(TermList!=null) TermList.accept(visitor);
        if(TermPart!=null) TermPart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TermList!=null) TermList.traverseTopDown(visitor);
        if(TermPart!=null) TermPart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TermList!=null) TermList.traverseBottomUp(visitor);
        if(TermPart!=null) TermPart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TermListMulti(\n");

        if(TermList!=null)
            buffer.append(TermList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(TermPart!=null)
            buffer.append(TermPart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TermListMulti]");
        return buffer.toString();
    }
}
