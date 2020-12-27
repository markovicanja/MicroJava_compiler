// generated with ast extension for cup
// version 0.8
// 27/11/2020 17:21:55


package rs.ac.bg.etf.pp1.ast;

public class ConstPart implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String I1;
    private Value Value;

    public ConstPart (String I1, Value Value) {
        this.I1=I1;
        this.Value=Value;
        if(Value!=null) Value.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public Value getValue() {
        return Value;
    }

    public void setValue(Value Value) {
        this.Value=Value;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Value!=null) Value.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Value!=null) Value.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Value!=null) Value.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstPart(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(Value!=null)
            buffer.append(Value.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstPart]");
        return buffer.toString();
    }
}
