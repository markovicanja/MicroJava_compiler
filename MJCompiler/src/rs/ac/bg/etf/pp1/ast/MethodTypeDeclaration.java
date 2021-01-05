// generated with ast extension for cup
// version 0.8
// 5/0/2021 22:27:54


package rs.ac.bg.etf.pp1.ast;

public class MethodTypeDeclaration extends MethodDec {

    private MethodTypeDecl MethodTypeDecl;

    public MethodTypeDeclaration (MethodTypeDecl MethodTypeDecl) {
        this.MethodTypeDecl=MethodTypeDecl;
        if(MethodTypeDecl!=null) MethodTypeDecl.setParent(this);
    }

    public MethodTypeDecl getMethodTypeDecl() {
        return MethodTypeDecl;
    }

    public void setMethodTypeDecl(MethodTypeDecl MethodTypeDecl) {
        this.MethodTypeDecl=MethodTypeDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodTypeDecl!=null) MethodTypeDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodTypeDecl!=null) MethodTypeDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodTypeDecl!=null) MethodTypeDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodTypeDeclaration(\n");

        if(MethodTypeDecl!=null)
            buffer.append(MethodTypeDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodTypeDeclaration]");
        return buffer.toString();
    }
}
