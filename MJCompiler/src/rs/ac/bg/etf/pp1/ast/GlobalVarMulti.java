// generated with ast extension for cup
// version 0.8
// 7/0/2021 22:39:39


package rs.ac.bg.etf.pp1.ast;

public class GlobalVarMulti extends GlobalVarList {

    private GlobalVarList GlobalVarList;
    private GlobalVarPart GlobalVarPart;

    public GlobalVarMulti (GlobalVarList GlobalVarList, GlobalVarPart GlobalVarPart) {
        this.GlobalVarList=GlobalVarList;
        if(GlobalVarList!=null) GlobalVarList.setParent(this);
        this.GlobalVarPart=GlobalVarPart;
        if(GlobalVarPart!=null) GlobalVarPart.setParent(this);
    }

    public GlobalVarList getGlobalVarList() {
        return GlobalVarList;
    }

    public void setGlobalVarList(GlobalVarList GlobalVarList) {
        this.GlobalVarList=GlobalVarList;
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
        if(GlobalVarList!=null) GlobalVarList.accept(visitor);
        if(GlobalVarPart!=null) GlobalVarPart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(GlobalVarList!=null) GlobalVarList.traverseTopDown(visitor);
        if(GlobalVarPart!=null) GlobalVarPart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(GlobalVarList!=null) GlobalVarList.traverseBottomUp(visitor);
        if(GlobalVarPart!=null) GlobalVarPart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalVarMulti(\n");

        if(GlobalVarList!=null)
            buffer.append(GlobalVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(GlobalVarPart!=null)
            buffer.append(GlobalVarPart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobalVarMulti]");
        return buffer.toString();
    }
}
