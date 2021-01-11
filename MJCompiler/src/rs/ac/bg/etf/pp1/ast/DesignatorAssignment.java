// generated with ast extension for cup
// version 0.8
// 11/0/2021 21:3:43


package rs.ac.bg.etf.pp1.ast;

public class DesignatorAssignment extends DesignatorStatement {

    private Designator Designator;
    private Assignment Assignment;

    public DesignatorAssignment (Designator Designator, Assignment Assignment) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.Assignment=Assignment;
        if(Assignment!=null) Assignment.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public Assignment getAssignment() {
        return Assignment;
    }

    public void setAssignment(Assignment Assignment) {
        this.Assignment=Assignment;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(Assignment!=null) Assignment.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(Assignment!=null) Assignment.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(Assignment!=null) Assignment.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorAssignment(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Assignment!=null)
            buffer.append(Assignment.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorAssignment]");
        return buffer.toString();
    }
}
