package homeposting.app.common.data.util;

public class Pair<O1,O2>{
	protected O1 o1;
	protected O2 o2;

	public Pair(O1 o1, O2 o2){
		this.o1 = o1;
		this.o2 = o2;
	}

	public O1 getO1() {
		return o1;
	}

	public O2 getO2() {
		return o2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((o1 == null) ? 0 : o1.hashCode());
		result = prime * result + ((o2 == null) ? 0 : o2.hashCode());
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
		Pair<O1,O2> other = (Pair<O1, O2>) obj;
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
		return true;
	}

	@Override
	public String toString() {
		return "Pair [o1=" + o1 + ", o2=" + o2 + "]";
	}
	
	
}
