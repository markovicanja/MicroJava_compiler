// generated with ast extension for cup
// version 0.8
// 27/11/2020 17:21:55


package rs.ac.bg.etf.pp1.ast;

public class MethodVoid3 extends MethodVoidDecl {

    private String I1;
    private FormParams FormParams;

    public MethodVoid3 (String I1, FormParams FormParams) {
        this.I1=I1;
        this.FormParams=FormParams;
        if(FormParams!=null) FormParams.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
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
        if(FormParams!=null) FormParams.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormParams!=null) FormParams.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormParams!=null) FormParams.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodVoid3(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(FormParams!=null)
            buffer.append(FormParams.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodVoid3]");
        return buffer.toString();
    }
}
