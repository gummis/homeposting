package homeposting.app.common.data.util;


public class Tercet<O1,O2,O3>{
	protected O1 o1;
	protected O2 o2;
	protected O3 o3;	

	public Tercet(O1 o1, O2 o2, O3 o3){
		this.o1 = o1;
		this.o2 = o2;
		this.o3 = o3;
	}

	public O1 getO1() {
		return o1;
	}

	public O2 getO2() {
		return o2;
	}

	public O3 getO3() {
		return o3;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((o1 == null) ? 0 : o1.hashCode());
		result = prime * result + ((o2 == null) ? 0 : o2.hashCode());
		result = prime * result + ((o3 == null) ? 0 : o3.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		Tercet<O1,O2,O3> other =  (Tercet<O1, O2, O3>) obj;
		if (o1 == null) {
			if (other.o1 != null)
				return false;
		} else if (!o1.equals(other.o1))
			return false;
		if (o2 == null) {
			if (other.o2 != null)
				return false;
		} else if (!o2.equals(other.o2))
			return false;
		if (o3 == null) {
			if (other.o3 != null)
				return false;
		} else if (!o3.equals(other.o3))
			return false;
		return true;
	}

	public String toString() {
		return "Tercet [o1=" + o1 + ", o2=" + o2 + ", o3=" + o3 + "]";
	}
	
}
