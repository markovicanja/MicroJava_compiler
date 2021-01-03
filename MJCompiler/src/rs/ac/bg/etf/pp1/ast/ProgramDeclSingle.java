// generated with ast extension for cup
// version 0.8
// 3/0/2021 15:48:49


package rs.ac.bg.etf.pp1.ast;

public class ProgramDeclSingle extends ProgramDeclarations {

    private ProgramDeclaration ProgramDeclaration;

    public ProgramDeclSingle (ProgramDeclaration ProgramDeclaration) {
        this.ProgramDeclaration=ProgramDeclaration;
        if(ProgramDeclaration!=null) ProgramDeclaration.setParent(this);
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
        if(ProgramDeclaration!=null) ProgramDeclaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ProgramDeclaration!=null) ProgramDeclaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ProgramDeclaration!=null) ProgramDeclaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgramDeclSingle(\n");

        if(ProgramDeclaration!=null)
            buffer.append(ProgramDeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgramDeclSingle]");
        return buffer.toString();
    }
}
