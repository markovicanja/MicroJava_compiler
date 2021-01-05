// generated with ast extension for cup
// version 0.8
// 5/0/2021 23:19:26


package rs.ac.bg.etf.pp1.ast;

public class CaseMulti extends CaseList {

    private CaseList CaseList;
    private CasePart CasePart;

    public CaseMulti (CaseList CaseList, CasePart CasePart) {
        this.CaseList=CaseList;
        if(CaseList!=null) CaseList.setParent(this);
        this.CasePart=CasePart;
        if(CasePart!=null) CasePart.setParent(this);
    }

    public CaseList getCaseList() {
        return CaseList;
    }

    public void setCaseList(CaseList CaseList) {
        this.CaseList=CaseList;
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
        if(CaseList!=null) CaseList.accept(visitor);
        if(CasePart!=null) CasePart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CaseList!=null) CaseList.traverseTopDown(visitor);
        if(CasePart!=null) CasePart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CaseList!=null) CaseList.traverseBottomUp(visitor);
        if(CasePart!=null) CasePart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CaseMulti(\n");

        if(CaseList!=null)
            buffer.append(CaseList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CasePart!=null)
            buffer.append(CasePart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CaseMulti]");
        return buffer.toString();
    }
}
