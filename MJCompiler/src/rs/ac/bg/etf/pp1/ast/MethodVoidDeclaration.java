// generated with ast extension for cup
// version 0.8
// 11/0/2021 21:3:43


package rs.ac.bg.etf.pp1.ast;

public class MethodVoidDeclaration extends MethodDec {

    private MethodVoidDecl MethodVoidDecl;

    public MethodVoidDeclaration (MethodVoidDecl MethodVoidDecl) {
        this.MethodVoidDecl=MethodVoidDecl;
        if(MethodVoidDecl!=null) MethodVoidDecl.setParent(this);
    }

    public MethodVoidDecl getMethodVoidDecl() {
        return MethodVoidDecl;
    }

    public void setMethodVoidDecl(MethodVoidDecl MethodVoidDecl) {
        this.MethodVoidDecl=MethodVoidDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodVoidDecl!=null) MethodVoidDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodVoidDecl!=null) MethodVoidDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodVoidDecl!=null) MethodVoidDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodVoidDeclaration(\n");

        if(MethodVoidDecl!=null)
            buffer.append(MethodVoidDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodVoidDeclaration]");
        return buffer.toString();
    }
}
