package rs.ac.bg.etf.pp1;

import java.util.ArrayList;

import rs.etf.pp1.symboltable.concepts.Struct;

public class Method {
	private String name;
	private ArrayList<Struct> parameters; 
			
	public Method(String name) {		
		this.name = name;
		this.parameters = new ArrayList<Struct>();
	}				
	
	public Method(String name, ArrayList<Struct> parameters) {		
		this.name = name;
		this.parameters = parameters;
	}				

	public String getMethodName() {
		return name;
	}
	
	public ArrayList<Struct> getParameters() {
		return parameters;
	}		
}
