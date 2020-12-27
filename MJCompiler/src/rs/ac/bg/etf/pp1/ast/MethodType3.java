// generated with ast extension for cup
// version 0.8
// 27/11/2020 17:21:55


package rs.ac.bg.etf.pp1.ast;

public class MethodType3 extends MethodTypeDecl {

    private Type Type;
    private String I2;
    private FormParams FormParams;

    public MethodType3 (Type Type, String I2, FormParams FormParams) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.I2=I2;
        this.FormParams=FormParams;
        if(FormParams!=null) FormParams.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getI2() {
        return I2;
    }

    public void setI2(String I2) {
        this.I2=I2;
    }

    public FormParams getFormParams() {
        return FormParams;
    }

    public void setFormParams(FormParams FormParams) {
        this.FormParams=FormParams;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(FormParams!=null) FormParams.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(FormParams!=null) FormParams.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(FormParams!=null) FormParams.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodType3(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+I2);
        buffer.append("\n");

        if(FormParams!=null)
            buffer.append(FormParams.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodType3]");
        return buffer.toString();
    }
}
