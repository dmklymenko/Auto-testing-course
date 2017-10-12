package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateTime {
	public static String getCurrentDateTimeStamp(){
		Calendar calendar = GregorianCalendar.getInstance();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_SSS", Locale.ENGLISH);
		
		return String.valueOf(formatter.format(calendar.getTime()));
	}
	
	public static String getShiftedDate(int shiftedDays){
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, shiftedDays);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_SSS", Locale.ENGLISH);
		
		return String.valueOf(formatter.format(calendar.getTime()));
	}

}
