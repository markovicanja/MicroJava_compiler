// generated with ast extension for cup
// version 0.8
// 14/0/2021 23:12:10


package rs.ac.bg.etf.pp1.ast;

public class ExprTernary extends Expr {

    private TernaryCondition TernaryCondition;
    private TernaryExpr1 TernaryExpr1;
    private TernaryExpr2 TernaryExpr2;

    public ExprTernary (TernaryCondition TernaryCondition, TernaryExpr1 TernaryExpr1, TernaryExpr2 TernaryExpr2) {
        this.TernaryCondition=TernaryCondition;
        if(TernaryCondition!=null) TernaryCondition.setParent(this);
        this.TernaryExpr1=TernaryExpr1;
        if(TernaryExpr1!=null) TernaryExpr1.setParent(this);
        this.TernaryExpr2=TernaryExpr2;
        if(TernaryExpr2!=null) TernaryExpr2.setParent(this);
    }

    public TernaryCondition getTernaryCondition() {
        return TernaryCondition;
    }

    public void setTernaryCondition(TernaryCondition TernaryCondition) {
        this.TernaryCondition=TernaryCondition;
    }

    public TernaryExpr1 getTernaryExpr1() {
        return TernaryExpr1;
    }

    public void setTernaryExpr1(TernaryExpr1 TernaryExpr1) {
        this.TernaryExpr1=TernaryExpr1;
    }

    public TernaryExpr2 getTernaryExpr2() {
        return TernaryExpr2;
    }

    public void setTernaryExpr2(TernaryExpr2 TernaryExpr2) {
        this.TernaryExpr2=TernaryExpr2;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(TernaryCondition!=null) TernaryCondition.accept(visitor);
        if(TernaryExpr1!=null) TernaryExpr1.accept(visitor);
        if(TernaryExpr2!=null) TernaryExpr2.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TernaryCondition!=null) TernaryCondition.traverseTopDown(visitor);
        if(TernaryExpr1!=null) TernaryExpr1.traverseTopDown(visitor);
        if(TernaryExpr2!=null) TernaryExpr2.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TernaryCondition!=null) TernaryCondition.traverseBottomUp(visitor);
        if(TernaryExpr1!=null) TernaryExpr1.traverseBottomUp(visitor);
        if(TernaryExpr2!=null) TernaryExpr2.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprTernary(\n");

        if(TernaryCondition!=null)
            buffer.append(TernaryCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(TernaryExpr1!=null)
            buffer.append(TernaryExpr1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(TernaryExpr2!=null)
            buffer.append(TernaryExpr2.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExprTernary]");
        return buffer.toString();
    }
}
