package homeposting.app.common.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("cashConverter")
public class CashConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		int dotInd = value.indexOf(".");
		if(dotInd < 0){
			return Integer.parseInt(value);
		}else{
			String cents = value.substring(dotInd+1);
			String pounds = value.substring(0,dotInd);
			if(cents.length() != 2 || pounds.length() == 0){
				throw new RuntimeException("Niewłaściwy format kwoty");
			}
			return Integer.parseInt(pounds) * 100 + Integer.parseInt(cents);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value instanceof Long){
			return fromInteger(((Long)value).intValue());
		}else{
			return fromInteger((Integer)value);
		}
	}
	
	public static String fromInteger(int ivalue){
		int pounds = Math.abs(ivalue / 100);
		int cents = Math.abs(ivalue % 100);
		StringBuilder sb = new StringBuilder();
		if(ivalue < 0){
			sb.append("-");
		}
		sb.append(pounds).append(".");
		if(cents < 10){
			sb.append("0");
		}
		return sb.append(cents).toString();
	}
}
