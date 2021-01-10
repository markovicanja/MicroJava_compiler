// generated with ast extension for cup
// version 0.8
// 10/0/2021 22:10:16


package rs.ac.bg.etf.pp1.ast;

public class MethodType7 extends MethodTypeDecl {

    private MethTypeName MethTypeName;

    public MethodType7 (MethTypeName MethTypeName) {
        this.MethTypeName=MethTypeName;
        if(MethTypeName!=null) MethTypeName.setParent(this);
    }

    public MethTypeName getMethTypeName() {
        return MethTypeName;
    }

    public void setMethTypeName(MethTypeName MethTypeName) {
        this.MethTypeName=MethTypeName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethTypeName!=null) MethTypeName.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethTypeName!=null) MethTypeName.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethTypeName!=null) MethTypeName.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodType7(\n");

        if(MethTypeName!=null)
            buffer.append(MethTypeName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodType7]");
        return buffer.toString();
    }
}
