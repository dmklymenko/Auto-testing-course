package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateTime {
	
	private static String dateFormat = "yyyy.MM.dd-HH:mm:ss_SSS";
	
	public static String getCurrentDateTimeStamp(){
		Calendar calendar = GregorianCalendar.getInstance();
		
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
		
		return String.valueOf(formatter.format(calendar.getTime()));
	}
	
	public static String getShiftedDateTime(int shiftedDays){
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, shiftedDays);
		
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
		
		return String.valueOf(formatter.format(calendar.getTime()));
	}

}
