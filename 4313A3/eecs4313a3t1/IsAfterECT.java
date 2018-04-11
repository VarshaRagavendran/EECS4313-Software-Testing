package eecs4313a3t1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import net.sf.borg.common.DateUtil;

public class IsAfterECT {
	private Date currDay;
	private Date nextDay;
	private Date feb_30;
	// private Date apr_31;
	private Date date1;
	private Date date2;
	private Date date3;
	private Date date4;
	private Date date5;
	private Date date6;

	@SuppressWarnings("deprecation")
	@Before
	public void setup() {
		currDay = new Date(2018, 2, 1);// 1st march
		nextDay = new Date(2018, 2, 2);// 2nd march
		feb_30 = new Date(2018, 1, 30); // 30th February
		// apr_31 = new Date(2017, 3, 31); // 31st April
		date1 = new Date(2012, 2, 1);
		date2 = new Date(2012, 1, 31);
		date3 = new Date(2000, 2, 29);
		date4 = new Date(2000, 2, 28);
		date5 = new Date(2018, 4, 1);
		date6 = new Date(2018, 4, 30);
	}

	/**
	 * Here we test following method public static boolean isAfter(Date d1, Date d2)
	 * Checks if one date falls on a later calendar day than another. Parameters: d1
	 * - the first date d2 - the second date Returns: true, if is after
	 */
	@Test
	public void EquivalentTesting() {
		// test using equivalence class testing
		// variation: weak normal
		// Valid dates only
		// date with valid day, month and year

		// both first date and second date is valid and the latter is after
		// first
		// valid month change

		assertTrue("Next date is not February 1",
				DateUtil.isAfter(new Date(2018, 1, 1), new Date(2018, 0, 31)) == true);
		assertFalse(DateUtil.isAfter(new Date(2018, 0, 31), new Date(2018, 1, 1)));
		assertEquals(new Date(2018, 1, 1).after(new Date(2018, 0, 31)),
				DateUtil.isAfter(new Date(2018, 1, 1), new Date(2018, 0, 31)));

		// valid date change
		assertTrue("Next date after July 31st is August 1",
				DateUtil.isAfter(new Date(2018, 7, 1), new Date(2018, 6, 31)) == true);
		assertFalse(DateUtil.isAfter(new Date(2018, 6, 31), new Date(2018, 7, 1)));
		assertEquals(new Date(2018, 7, 1).after(new Date(2018, 6, 31)),
				DateUtil.isAfter(new Date(2018, 7, 1), new Date(2018, 6, 31)));

		// valid year change
		assertTrue("Next date after December 31st is January 1st ",
				DateUtil.isAfter(new Date(2019, 0, 1), new Date(2018, 11, 31)) == true);
		assertFalse(DateUtil.isAfter(new Date(2018, 11, 31), new Date(2019, 0, 1)));
		assertEquals(new Date(2019, 0, 1).after(new Date(2018, 11, 31)),
				DateUtil.isAfter(new Date(2019, 0, 1), new Date(2018, 11, 31)));

		// variation: weak robust
		// Add invalid inputs to valid ones
		// Invalid dates:
		// Dates with invalid day, month and/or year
		// non-date objects

		// Equivalent classes:
		// 1st invalid and 2nd valid date
		// 1st valid and 2nd invalid date
		// both dates are invalid
		// both dates are valid 2nd date is after 1st one
		// both dates are valid 1st date is after 2nd one

		// Valid consecutive days within same month
		assertTrue("Days are not Consecutive", DateUtil.isAfter(nextDay, currDay));
		assertFalse("Days are consecutive", DateUtil.isAfter(currDay, nextDay));
		assertEquals(nextDay.after(currDay), DateUtil.isAfter(nextDay, currDay));

		assertFalse("Test failed: Feb_30 is invalid", DateUtil.isAfter(feb_30, nextDay));
		assertEquals(feb_30.after(nextDay), DateUtil.isAfter(feb_30, nextDay));
		assertEquals("Test failed: Feb_30 is invalid", nextDay.after(feb_30), DateUtil.isAfter(nextDay, feb_30));

		assertEquals("Test failed: Both dates are invalid", currDay.after(new Date()),
				DateUtil.isAfter(currDay, new Date()));

	}

	@Test
	public void EquivalenceClassTesting() {

		GregorianCalendar cal1 = new GregorianCalendar();
		GregorianCalendar cal2 = new GregorianCalendar();

		cal1.setTime(date1);
		cal2.setTime(date2);
		assertEquals(cal1.after(cal2), DateUtil.isAfter(date1, date2));
		assertEquals(cal2.after(cal1), DateUtil.isAfter(date2, date1));

		cal1.setTime(date3);
		cal2.setTime(date4);
		assertEquals(cal1.after(cal2), DateUtil.isAfter(date3, date4));
		assertEquals(cal2.after(cal1), DateUtil.isAfter(date4, date3));

		cal1.setTime(date5);
		cal2.setTime(date6);
		assertEquals(cal1.after(cal2), DateUtil.isAfter(date5, date6));
		assertEquals(cal2.after(cal1), DateUtil.isAfter(date6, date5));
	}
	
	@Test
	public void mutantKills() {
		Date date1 = new Date(2018, 04, 16);
		Date date2 = new Date(2018, 04, 16);
		
		date2.setHours(1);
		GregorianCalendar cal1 = new GregorianCalendar();
		GregorianCalendar cal2 = new GregorianCalendar();

		cal1.setTime(date1);
		cal2.setTime(date2);
		assertEquals(cal1.after(cal2), DateUtil.isAfter(date2, date1));
		
		date2.setMinutes(20);
		cal1.setTime(date1);
		cal2.setTime(date2);
		assertEquals(cal1.after(cal2), DateUtil.isAfter(date2, date1));
		
		date1.setSeconds(30);
		cal1.setTime(date1);
		cal2.setTime(date2);
		assertEquals(cal1.after(cal2), DateUtil.isAfter(date1, date2));
	}
}
