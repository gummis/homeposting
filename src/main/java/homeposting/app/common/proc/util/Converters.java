package homeposting.app.common.proc.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Converters {
	private static final BigDecimal hundred = new BigDecimal("100");
	private static final BigDecimal small = new BigDecimal("0.00");
	
	public static String DateTimeToString(Date date){
		if(date == null){
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
	}
	public static Date StringToDateTime(String text) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(text);
		} catch (ParseException e) {
			return null;
		}
	}
	public static BigDecimal IntegerToCash(int cash){
		return new BigDecimal(cash).divide(hundred).add(small);
	}
	public static int CashStringToInteger(String text) {
		return new BigDecimal(text).multiply(hundred).stripTrailingZeros().intValue();
	}
	
	
}
