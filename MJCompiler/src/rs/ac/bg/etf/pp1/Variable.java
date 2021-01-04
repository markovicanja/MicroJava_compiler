package rs.ac.bg.etf.pp1;

public class Variable {
	private String name;
	private Boolean array;
	private Object value;
	
	public Variable(String name, Boolean array, Object value) {
		super();
		this.name = name;
		this.array = array;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getArray() {
		return array;
	}

	public void setArray(Boolean array) {
		this.array = array;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
}
