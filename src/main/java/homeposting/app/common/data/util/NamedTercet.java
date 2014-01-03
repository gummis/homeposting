package homeposting.app.common.data.util;

@SuppressWarnings("serial")
public class NamedTercet<O2, O3> extends Tercet<String,O2,O3>{

	public NamedTercet(String o1, O2 o2, O3 o3) {
		super(o1, o2, o3);
	}
	
	public String toString(){
		return o1;
	}

}
