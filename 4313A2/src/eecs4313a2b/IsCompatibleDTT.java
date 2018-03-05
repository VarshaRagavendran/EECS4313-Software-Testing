package eecs4313a2b;

import net.sf.borg.model.Repeat;
import net.sf.borg.model.Day;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class IsCompatibleDTT {
	/*
	 * 
	 * This class takes a date and a frequency, and determines if the date is a
	 * valid date for the frequency. For example, a Monday is valid for a
	 * "Monday,Wednesday,Friday" frequency as well as "Weekday" frequency. Monday
	 * would not be valid for the "Weekend" frequency. Monday may be valid for the
	 * "last day of month" frequency, depending on what month and year it is.
	 * 
	 * Input Conditions: c1 -> Day is Monday c2 -> Day is Tuesday c3 -> Day is
	 * Wednesday c4 -> Day is Thursday c5 -> Day is Friday c6 -> Day is Saturday c7
	 * -> Day is Sunday c8 -> Day is the last of the month
	 * 
	 * c9 -> Frequency is Once, Daily, Weekly, Biweekly, Monthly, or Yearly. c10 ->
	 * Frequency is Weekday. c11 -> Frequency is Weekend. c12 -> Frequency is
	 * Monday, Wednesday, Friday. c13 -> Frequency is Tuesday, Thursday. C14 ->
	 * Frequency is Last Day of Month.
	 * 
	 * c1 through c7 are exclusive with each other. c9 through c14 are exclusive
	 * with each other.
	 * 
	 * 
	 * 
	 * * Actions: a1 - Output True a2 - Output False
	 * 
	 * 
	 * 
	 * * Decision Table: c9 -> a1
	 * 
	 * c10 && any of c1 through c5 -> a1 c10 && none of c1 through c5 -> a2
	 * 
	 * c11 && any of c6 through c7 -> a1 c11 && none of c6 through c7 -> a2
	 * 
	 * c12 && any of c1,c3,c5 -> a1 c12 && none of c1,c3,c5 -> a2
	 * 
	 * c13 && any of c2,c4 -> a1 c13 && non of c2,c4 -> a2
	 * 
	 * c14 && c8 -> a1 c14 && !c8 -> a2
	 * 
	 */

	private Calendar monday_calendar;
	private Calendar tuesday_calendar;
	private Calendar wednesday_calendar;
	private Calendar thursday_calendar;
	private Calendar friday_calendar;
	private Calendar saturday_calendar;
	private Calendar sunday_calendar;
	private Calendar last_day_of_month_calendar;

	private String weekday_freq;
	private String weekend_freq;
	private String mwf_freq;
	private String tuth_freq;
	private String last_of_month_freq;

	Collection<Integer> daylist;

	@Before
	public void setup() {
		// These calendars correspond to c1 through c8
		monday_calendar = new GregorianCalendar(2018, 2, 5);
		tuesday_calendar = new GregorianCalendar(2018, 2, 6);
		wednesday_calendar = new GregorianCalendar(2018, 2, 7);
		thursday_calendar = new GregorianCalendar(2018, 2, 8);
		friday_calendar = new GregorianCalendar(2018, 2, 9);
		saturday_calendar = new GregorianCalendar(2018, 2, 10);
		sunday_calendar = new GregorianCalendar(2018, 2, 11);
		last_day_of_month_calendar = new GregorianCalendar(2018, 2, 31);

		// These ints are used in a frequency lookup chart in Repeat. They corrispond to
		// c10 through c14.
		weekday_freq = Repeat.getFreqString(8);
		weekend_freq = Repeat.getFreqString(9);
		mwf_freq = Repeat.getFreqString(10);
		tuth_freq = Repeat.getFreqString(11);
		last_of_month_freq = Repeat.getFreqString(6);

		// Daylist is wider in scope than this single method and will not be tested
		// here. It will be kept empty for testing.
		daylist = new ArrayList<Integer>();
	}

	@Test
	public void isCurrent_Weekday_test() {
		assertTrue(Repeat.isCompatible(monday_calendar, weekday_freq, daylist));
		assertTrue(Repeat.isCompatible(tuesday_calendar, weekday_freq, daylist));
		assertTrue(Repeat.isCompatible(wednesday_calendar, weekday_freq, daylist));
		assertTrue(Repeat.isCompatible(thursday_calendar, weekday_freq, daylist));
		assertTrue(Repeat.isCompatible(friday_calendar, weekday_freq, daylist));

		assertFalse(Repeat.isCompatible(saturday_calendar, weekday_freq, daylist));
		assertFalse(Repeat.isCompatible(sunday_calendar, weekday_freq, daylist));
	}

	@Test
	public void isCurrent_Weekend_test() {
		assertTrue(Repeat.isCompatible(saturday_calendar, weekend_freq, daylist));
		assertTrue(Repeat.isCompatible(sunday_calendar, weekend_freq, daylist));

		assertFalse(Repeat.isCompatible(monday_calendar, weekend_freq, daylist));
		assertFalse(Repeat.isCompatible(tuesday_calendar, weekend_freq, daylist));
		assertFalse(Repeat.isCompatible(wednesday_calendar, weekend_freq, daylist));
		assertFalse(Repeat.isCompatible(thursday_calendar, weekend_freq, daylist));
		assertFalse(Repeat.isCompatible(friday_calendar, weekend_freq, daylist));
	}

	@Test
	public void isCurrent_MonWedFri_test() {
		assertTrue(Repeat.isCompatible(monday_calendar, mwf_freq, daylist));
		assertTrue(Repeat.isCompatible(wednesday_calendar, mwf_freq, daylist));
		assertTrue(Repeat.isCompatible(friday_calendar, mwf_freq, daylist));

		assertFalse(Repeat.isCompatible(tuesday_calendar, mwf_freq, daylist));
		assertFalse(Repeat.isCompatible(thursday_calendar, mwf_freq, daylist));
		assertFalse(Repeat.isCompatible(saturday_calendar, mwf_freq, daylist));
		assertFalse(Repeat.isCompatible(sunday_calendar, mwf_freq, daylist));
	}

	@Test
	public void isCurrent_TuThr_test() {
		assertTrue(Repeat.isCompatible(tuesday_calendar, tuth_freq, daylist));
		assertTrue(Repeat.isCompatible(thursday_calendar, tuth_freq, daylist));

		assertFalse(Repeat.isCompatible(monday_calendar, tuth_freq, daylist));
		assertFalse(Repeat.isCompatible(wednesday_calendar, tuth_freq, daylist));
		assertFalse(Repeat.isCompatible(friday_calendar, tuth_freq, daylist));
		assertFalse(Repeat.isCompatible(saturday_calendar, tuth_freq, daylist));
		assertFalse(Repeat.isCompatible(sunday_calendar, tuth_freq, daylist));
	}

	@Test
	public void isCurrent_LastOfMonth_test() {
		assertTrue(Repeat.isCompatible(last_day_of_month_calendar, last_of_month_freq, daylist));

		assertFalse(Repeat.isCompatible(monday_calendar, last_of_month_freq, daylist));
		assertFalse(Repeat.isCompatible(tuesday_calendar, last_of_month_freq, daylist));
		assertFalse(Repeat.isCompatible(wednesday_calendar, last_of_month_freq, daylist));
		assertFalse(Repeat.isCompatible(thursday_calendar, last_of_month_freq, daylist));
		assertFalse(Repeat.isCompatible(friday_calendar, last_of_month_freq, daylist));
		assertFalse(Repeat.isCompatible(saturday_calendar, last_of_month_freq, daylist));
		assertFalse(Repeat.isCompatible(sunday_calendar, last_of_month_freq, daylist));
	}

	@Test
	public void isCurrent_OtherFreqs_test() {
		for (int i = 0; i < 6; i++) {
			assertTrue(Repeat.isCompatible(monday_calendar, Repeat.getFreqString(i), daylist));
			assertTrue(Repeat.isCompatible(tuesday_calendar, Repeat.getFreqString(i), daylist));
			assertTrue(Repeat.isCompatible(wednesday_calendar, Repeat.getFreqString(i), daylist));
			assertTrue(Repeat.isCompatible(thursday_calendar, Repeat.getFreqString(i), daylist));
			assertTrue(Repeat.isCompatible(friday_calendar, Repeat.getFreqString(i), daylist));
			assertTrue(Repeat.isCompatible(saturday_calendar, Repeat.getFreqString(i), daylist));
			assertTrue(Repeat.isCompatible(sunday_calendar, Repeat.getFreqString(i), daylist));
		}
	}
}
