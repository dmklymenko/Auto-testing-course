package utils;

import java.util.Calendar;

public class DateTime {
	public static String getCurrentTimeStamp(){
		Calendar calendar = Calendar.getInstance();
		return String.valueOf(calendar.getTimeInMillis());
	}
}
