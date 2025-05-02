package practice.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateAfterOrBeforeFromCurrentDate {
	public static void main(String[] args) {
		Date dateObj = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String date = sim.format(dateObj);
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String reqDate = sim.format(cal.getTime());
		System.out.println(reqDate);
	}
}
