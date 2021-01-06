// generated with ast extension for cup
// version 0.8
// 6/0/2021 22:20:46


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclSingle extends MethodDeclList {

    private MethodDec MethodDec;

    public MethodDeclSingle (MethodDec MethodDec) {
        this.MethodDec=MethodDec;
        if(MethodDec!=null) MethodDec.setParent(this);
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
        if(MethodDec!=null) MethodDec.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodDec!=null) MethodDec.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodDec!=null) MethodDec.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDeclSingle(\n");

        if(MethodDec!=null)
            buffer.append(MethodDec.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclSingle]");
        return buffer.toString();
    }
}
