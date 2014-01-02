package homeposting.app.common.data.util;

public class Quartet<O1,O2,O3,O4>{
	protected O1 o1;
	protected O2 o2;
	protected O3 o3;	
	protected O4 o4;	

	public Quartet(O1 o1, O2 o2, O3 o3, O4 o4){
		this.o1 = o1;
		this.o2 = o2;
		this.o3 = o3;
		this.o4 = o4;
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

	public O4 getO4() {
		return o4;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((o1 == null) ? 0 : o1.hashCode());
		result = prime * result + ((o2 == null) ? 0 : o2.hashCode());
		result = prime * result + ((o3 == null) ? 0 : o3.hashCode());
		result = prime * result + ((o4 == null) ? 0 : o4.hashCode());
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
		Quartet<O1,O2,O3,O4> other = (Quartet<O1,O2,O3,O4>) obj;
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
		if (o4 == null) {
			if (other.o4 != null)
				return false;
		} else if (!o4.equals(other.o4))
			return false;
		return true;
	}

	public String toString() {
		return "Quartet [o1=" + o1 + ", o2=" + o2 + ", o3=" + o3 + ", o4=" + o4 + "]";
	}

}
