package homeposting.app.common.data.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
	public static Date getNewDate(){
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("CET"));
		return cal.getTime();
	}
}
