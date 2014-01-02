package homeposting.app.common.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;


public class Shortcut implements Serializable{

	private static final long serialVersionUID = 1L;

	private final Integer id;
	private final String name;
	
	public Shortcut(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Shortcut(SelectItem item) {
		this.id = (Integer) item.getValue();
		this.name = item.getLabel();
	}

	public SelectItem toSelectedItem(){
		return new SelectItem(id,name);
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String toString(){
		return name;
	}
	
	public int hashCode(){
		return id;
	}

	public boolean equals(Object obj){
		return id.equals(((Shortcut)obj).id);
	}
	
	public static List<SelectItem> toSelectItemList(List<Shortcut> shortcuts) {
		List<SelectItem> list = new ArrayList<SelectItem>();
		for(Shortcut shortcut : shortcuts)
			list.add(shortcut.toSelectedItem());
		return list;
	}
	
	
	
}
