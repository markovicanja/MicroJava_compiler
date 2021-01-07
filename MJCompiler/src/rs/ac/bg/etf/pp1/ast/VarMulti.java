// generated with ast extension for cup
// version 0.8
// 7/0/2021 22:39:39


package rs.ac.bg.etf.pp1.ast;

public class VarMulti extends VarList {

    private VarList VarList;
    private VarPart VarPart;

    public VarMulti (VarList VarList, VarPart VarPart) {
        this.VarList=VarList;
        if(VarList!=null) VarList.setParent(this);
        this.VarPart=VarPart;
        if(VarPart!=null) VarPart.setParent(this);
    }

    public VarList getVarList() {
        return VarList;
    }

    public void setVarList(VarList VarList) {
        this.VarList=VarList;
    }

    public VarPart getVarPart() {
        return VarPart;
    }

    public void setVarPart(VarPart VarPart) {
        this.VarPart=VarPart;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarList!=null) VarList.accept(visitor);
        if(VarPart!=null) VarPart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarList!=null) VarList.traverseTopDown(visitor);
        if(VarPart!=null) VarPart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarList!=null) VarList.traverseBottomUp(visitor);
        if(VarPart!=null) VarPart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarMulti(\n");

        if(VarList!=null)
            buffer.append(VarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarPart!=null)
            buffer.append(VarPart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarMulti]");
        return buffer.toString();
    }
}
