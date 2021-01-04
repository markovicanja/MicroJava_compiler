// generated with ast extension for cup
// version 0.8
// 4/0/2021 22:44:14


package rs.ac.bg.etf.pp1.ast;

public class ProgramDeclMulti extends ProgramDeclarations {

    private ProgramDeclarations ProgramDeclarations;
    private ProgramDeclaration ProgramDeclaration;

    public ProgramDeclMulti (ProgramDeclarations ProgramDeclarations, ProgramDeclaration ProgramDeclaration) {
        this.ProgramDeclarations=ProgramDeclarations;
        if(ProgramDeclarations!=null) ProgramDeclarations.setParent(this);
        this.ProgramDeclaration=ProgramDeclaration;
        if(ProgramDeclaration!=null) ProgramDeclaration.setParent(this);
    }

    public ProgramDeclarations getProgramDeclarations() {
        return ProgramDeclarations;
    }

    public void setProgramDeclarations(ProgramDeclarations ProgramDeclarations) {
        this.ProgramDeclarations=ProgramDeclarations;
    }

    public ProgramDeclaration getProgramDeclaration() {
        return ProgramDeclaration;
    }

    public void setProgramDeclaration(ProgramDeclaration ProgramDeclaration) {
        this.ProgramDeclaration=ProgramDeclaration;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ProgramDeclarations!=null) ProgramDeclarations.accept(visitor);
        if(ProgramDeclaration!=null) ProgramDeclaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ProgramDeclarations!=null) ProgramDeclarations.traverseTopDown(visitor);
        if(ProgramDeclaration!=null) ProgramDeclaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ProgramDeclarations!=null) ProgramDeclarations.traverseBottomUp(visitor);
        if(ProgramDeclaration!=null) ProgramDeclaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgramDeclMulti(\n");

        if(ProgramDeclarations!=null)
            buffer.append(ProgramDeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ProgramDeclaration!=null)
            buffer.append(ProgramDeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgramDeclMulti]");
        return buffer.toString();
    }
}
