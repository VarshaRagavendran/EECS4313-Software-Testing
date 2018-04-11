package eecs4313a3t1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import net.sf.borg.common.DateUtil;

public class SlicingTests {

	@Test
	public void forwardSlicing() {
		// covers all forward slices
		assertEquals("5 Hours 1 Minute", DateUtil.minuteString(301)); 
	}
	
	@Test
	public void backwardSlicing() {
		// Covers S(hours, 3), S(minsPast, 4), S(minutesString, 18)  
		assertEquals("5 Minutes", DateUtil.minuteString(5));   
		  
		// Covers S(minutesString, 20)  
		assertEquals("1 Minute", DateUtil.minuteString(1));  
		  
		// Covers S(minutesString, 22)  
		assertEquals("1 Hour", DateUtil.minuteString(60));   
		  
		// Covers S(minutesString, 29)  
		assertEquals("0 Minutes", DateUtil.minuteString(0));   
		  
		// Covers S(hoursString, 10)  
		assertEquals("0 Minutes", DateUtil.minuteString(0));   
		  
		// Covers S(hoursString, 12)  
		assertEquals("5 Hours", DateUtil.minuteString(300));   
		  
		// Covers S(hoursString, 14)  
		assertEquals("1 Hour", DateUtil.minuteString(60));   
		  
		// Covers S(hoursString, 29)  
		assertEquals("5 Minutes", DateUtil.minuteString(5));   
	}
	
}
