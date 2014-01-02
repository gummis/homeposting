package homeposting.app.common.data.util;

public class NamedQuartet<O2,O3,O4> extends Quartet<String,O2,O3,O4> {
	public NamedQuartet(String name,O2 o2,O3 o3,O4 o4){
		super(name,o2,o3,o4);
	}
		
	public String toString(){
		return o1;
	}

}
