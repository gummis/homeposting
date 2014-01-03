package homeposting.app.common.data.util;

@SuppressWarnings("serial")
public class NamedPair<O2> extends Pair<String,O2>{

	public NamedPair(String name,O2 o2) {
		super(name, o2);
	}

	public String toString(){
		return o1;
	}
	
}
