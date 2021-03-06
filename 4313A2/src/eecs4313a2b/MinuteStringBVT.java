package eecs4313a2b;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import net.sf.borg.common.DateUtil;

public class MinuteStringBVT {

	private String ZERO;
	private String ONE;
	private String ONE_ONE;
	private String NOM;
	private String BELOW_MAX;
	private String MAX;

	@Before
	public void setup() {
		ZERO = "0 Minutes";
		ONE = "1 Minute";
		ONE_ONE = "1 Hour 1 Minute";
		NOM = "5 Hours 20 Minutes";
		BELOW_MAX = "35791394 Hours 6 Minutes";
		MAX = "35791394 Hours 7 Minutes";
	}

	/**
	 * Test Date Utils minuteString method. Assuming use case is for all
	 * non-negative minutes
	 */
	@Test
	public void boundaryValue() {
		int minutes;

		// Minimum
		minutes = 0;
		Assert.assertEquals(ZERO, DateUtil.minuteString(minutes));

		// Just above Minimum
		minutes = 1;
		Assert.assertEquals(ONE, DateUtil.minuteString(minutes));

		// Singular hour,minute (nominal)
		minutes = 61;
		Assert.assertEquals(ONE_ONE, DateUtil.minuteString(minutes));

		// Nominal
		minutes = 320;
		Assert.assertEquals(NOM, DateUtil.minuteString(minutes));

		// Just below max
		minutes = Integer.MAX_VALUE - 1;
		Assert.assertEquals(BELOW_MAX, DateUtil.minuteString(minutes));

		// Maximum
		minutes = Integer.MAX_VALUE;
		Assert.assertEquals(MAX, DateUtil.minuteString(minutes));
	}
}
