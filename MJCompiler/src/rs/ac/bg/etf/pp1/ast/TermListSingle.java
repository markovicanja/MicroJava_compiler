// generated with ast extension for cup
// version 0.8
// 5/0/2021 21:5:56


package rs.ac.bg.etf.pp1.ast;

public class TermListSingle extends TermList {

    private TermPart TermPart;

    public TermListSingle (TermPart TermPart) {
        this.TermPart=TermPart;
        if(TermPart!=null) TermPart.setParent(this);
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
        if(TermPart!=null) TermPart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TermPart!=null) TermPart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TermPart!=null) TermPart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TermListSingle(\n");

        if(TermPart!=null)
            buffer.append(TermPart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TermListSingle]");
        return buffer.toString();
    }
}
