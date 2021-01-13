package rs.ac.bg.etf.pp1;

public class CondJcc {
	private int pc;
	private int ordNum;
	private int relop;
	private boolean modified;
	
	public CondJcc(int pc, int ordNum, int relop) {
		super();
		this.pc = pc;
		this.ordNum = ordNum;
		this.relop = relop;
		this.modified = false;
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public int getOrdNum() {
		return ordNum;
	}

	public void setOrdNum(int ordNum) {
		this.ordNum = ordNum;
	}

	public int getRelop() {
		return relop;
	}

	public void setRelop(int relop) {
		this.relop = relop;
	}

	public boolean isModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

}