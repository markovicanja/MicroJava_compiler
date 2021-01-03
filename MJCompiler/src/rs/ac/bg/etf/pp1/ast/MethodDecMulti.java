// generated with ast extension for cup
// version 0.8
// 3/0/2021 15:48:49


package rs.ac.bg.etf.pp1.ast;

public class MethodDecMulti extends MethodDeclList {

    private MethodDeclList MethodDeclList;
    private MethodDec MethodDec;

    public MethodDecMulti (MethodDeclList MethodDeclList, MethodDec MethodDec) {
        this.MethodDeclList=MethodDeclList;
        if(MethodDeclList!=null) MethodDeclList.setParent(this);
        this.MethodDec=MethodDec;
        if(MethodDec!=null) MethodDec.setParent(this);
    }

    public MethodDeclList getMethodDeclList() {
        return MethodDeclList;
    }

    public void setMethodDeclList(MethodDeclList MethodDeclList) {
        this.MethodDeclList=MethodDeclList;
    }

    public MethodDec getMethodDec() {
        return MethodDec;
    }

    public void setMethodDec(MethodDec MethodDec) {
        this.MethodDec=MethodDec;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodDeclList!=null) MethodDeclList.accept(visitor);
        if(MethodDec!=null) MethodDec.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseTopDown(visitor);
        if(MethodDec!=null) MethodDec.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodDeclList!=null) MethodDeclList.traverseBottomUp(visitor);
        if(MethodDec!=null) MethodDec.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDecMulti(\n");

        if(MethodDeclList!=null)
            buffer.append(MethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDec!=null)
            buffer.append(MethodDec.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDecMulti]");
        return buffer.toString();
    }
}
