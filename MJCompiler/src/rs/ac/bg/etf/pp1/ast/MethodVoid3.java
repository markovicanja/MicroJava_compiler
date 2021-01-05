// generated with ast extension for cup
// version 0.8
// 5/0/2021 22:27:54


package rs.ac.bg.etf.pp1.ast;

public class MethodVoid3 extends MethodVoidDecl {

    private MethVoidName MethVoidName;
    private FormParams FormParams;

    public MethodVoid3 (MethVoidName MethVoidName, FormParams FormParams) {
        this.MethVoidName=MethVoidName;
        if(MethVoidName!=null) MethVoidName.setParent(this);
        this.FormParams=FormParams;
        if(FormParams!=null) FormParams.setParent(this);
    }

    public MethVoidName getMethVoidName() {
        return MethVoidName;
    }

    public void setMethVoidName(MethVoidName MethVoidName) {
        this.MethVoidName=MethVoidName;
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
        if(MethVoidName!=null) MethVoidName.accept(visitor);
        if(FormParams!=null) FormParams.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethVoidName!=null) MethVoidName.traverseTopDown(visitor);
        if(FormParams!=null) FormParams.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethVoidName!=null) MethVoidName.traverseBottomUp(visitor);
        if(FormParams!=null) FormParams.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodVoid3(\n");

        if(MethVoidName!=null)
            buffer.append(MethVoidName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
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
