// generated with ast extension for cup
// version 0.8
// 5/0/2021 22:27:54


package rs.ac.bg.etf.pp1.ast;

public class CaseSingle extends CaseList {

    private CasePart CasePart;

    public CaseSingle (CasePart CasePart) {
        this.CasePart=CasePart;
        if(CasePart!=null) CasePart.setParent(this);
    }

    public CasePart getCasePart() {
        return CasePart;
    }

    public void setCasePart(CasePart CasePart) {
        this.CasePart=CasePart;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CasePart!=null) CasePart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CasePart!=null) CasePart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CasePart!=null) CasePart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CaseSingle(\n");

        if(CasePart!=null)
            buffer.append(CasePart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CaseSingle]");
        return buffer.toString();
    }
}
