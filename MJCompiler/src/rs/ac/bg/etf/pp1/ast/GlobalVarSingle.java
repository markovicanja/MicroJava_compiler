// generated with ast extension for cup
// version 0.8
// 8/0/2021 15:58:55


package rs.ac.bg.etf.pp1.ast;

public class GlobalVarSingle extends GlobalVarList {

    private GlobalVarPart GlobalVarPart;

    public GlobalVarSingle (GlobalVarPart GlobalVarPart) {
        this.GlobalVarPart=GlobalVarPart;
        if(GlobalVarPart!=null) GlobalVarPart.setParent(this);
    }

    public GlobalVarPart getGlobalVarPart() {
        return GlobalVarPart;
    }

    public void setGlobalVarPart(GlobalVarPart GlobalVarPart) {
        this.GlobalVarPart=GlobalVarPart;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(GlobalVarPart!=null) GlobalVarPart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(GlobalVarPart!=null) GlobalVarPart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(GlobalVarPart!=null) GlobalVarPart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalVarSingle(\n");

        if(GlobalVarPart!=null)
            buffer.append(GlobalVarPart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobalVarSingle]");
        return buffer.toString();
    }
}
