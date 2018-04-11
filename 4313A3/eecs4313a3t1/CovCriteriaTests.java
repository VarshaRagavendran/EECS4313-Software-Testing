package eecs4313a3t1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import net.sf.borg.common.DateUtil;

public class CovCriteriaTests {

	@Test
	public void allDefs() {
		assertEquals("1 Minute", DateUtil.minuteString(1));  
		  
		// All-Defs: BCDEF  
		assertEquals("2 Hours 30 Minutes", DateUtil.minuteString(150));  
		  
		// All-Defs: CDEFHK  
		assertEquals("5 Minutes", DateUtil.minuteString(5));  
		 
		// All-Defs: DEFHJKL  
		assertEquals("2 Minutes", DateUtil.minuteString(2));  
		 
		// All-Defs: EFG  
		assertEquals("5 Hours", DateUtil.minuteString(300));
	}
	
	@Test
	public void allUses() {
		// All-Uses: BCDEF, BCDEFG, BCDEFH, BCDEFHI, BCDEFHJKMO 
		assertEquals("1 Hour 30 Minutes", DateUtil.minuteString(90));  
		assertEquals("2 Hours 30 Minutes", DateUtil.minuteString(150));  
		assertEquals("1 Minute", DateUtil.minuteString(1));  
		  
		// All-Uses: CDEFHJK, CDEFHJKL, CDEFHJKM, CDEFHJKMN, CDEFHJKMNOQ 
		assertEquals("0 Minutes", DateUtil.minuteString(0));  
		assertEquals("1 Minute", DateUtil.minuteString(1));  
		assertEquals("2 Minutes", DateUtil.minuteString(2));  
		  
		// All-Uses: DEFHJKL, DEFHJKMN , DEFHJKMOP, DEFHJKMOQ, DEFHJKMOR, DEFHKMORS, ST
		assertEquals("1 Hour 1 Minute", DateUtil.minuteString(61));  
		assertEquals("1 Hour 2 Minutes", DateUtil.minuteString(62));  
		assertEquals("0 Minutes", DateUtil.minuteString(0));  
		assertEquals("1 Hour", DateUtil.minuteString(60));  
		          
		// All-Uses: EFG, EFHI, EFHJ, EFHJKMOR, EFHJKMORT 
		assertEquals("2 Hours", DateUtil.minuteString(120));  
		assertEquals("1 Hour", DateUtil.minuteString(60));  
		assertEquals("0 Minutes", DateUtil.minuteString(0));  
		assertEquals("1 Hour 1 Minute", DateUtil.minuteString(61));  
		assertEquals("3 Hours 2 Minutes", DateUtil.minuteString(182)); 
	}
	
	@Test
	public void allPSomeC() {
		// All-P-Uses/Some-C-Uses: BCDEFHJKMO  
		assertEquals("1 Hour", DateUtil.minuteString(60));  
		  
		// All-P-Uses/Some-C-uses: CDEFHJK, CDEFHKM  
		assertEquals("15 Minutes", DateUtil.minuteString(15));  
		assertEquals("5 Hours 1 Minute", DateUtil.minuteString(301));  
		  
		// All-P-Uses/Some-C-uses: DEFHJKMOR  
		assertEquals("7 Hours 1 Minute", DateUtil.minuteString(421));  
		assertEquals("1 Hour", DateUtil.minuteString(60));  
		  
		// All-P-Uses/SomeC-Uses: EFHKMOR  
		assertEquals("7 Hours 1 Minute", DateUtil.minuteString(421));  
		assertEquals("1 Hour", DateUtil.minuteString(60));  
	}
	
	@Test
	public void AllCSomeP() {
		// All-C-uses/Some-P-Uses: BCDEFG, BCDEFHI  
		assertEquals("1 Hour 2 Minutes", DateUtil.minuteString(62));  
		assertEquals("2 Hours 2 Minutes", DateUtil.minuteString(122));  
		  
		// All-C-uses/Some-P-uses: CDEFHJKL, CDEFHJKMN, CDEFHJKMNOQ  
		assertEquals("1 Hour 10 Minutes", DateUtil.minuteString(70));  
		assertEquals("3 Hours 40 Minutes", DateUtil.minuteString(220));  
		  
		          
		// All-C-uses/Some-P-uses: DEFHJKL, DEFHJKMN , DEFHJKMOP, DEFHJKMOQ, DEFHJKMORS, ST  
		assertEquals("1 Hour 6 Minutes", DateUtil.minuteString(66));  
		assertEquals("2 Hours 1 Minute", DateUtil.minuteString(121));  
		assertEquals("1 Hour", DateUtil.minuteString(60));  
		assertEquals("0 Minutes", DateUtil.minuteString(0));  
		assertEquals("5 Minutes", DateUtil.minuteString(5));  
		  
		// All-C-uses/Some-P-uses: EFG, EFHI, EFHJ, EFHJKMORT  
		assertEquals("1 Hour 6 Minutes", DateUtil.minuteString(66));  
		assertEquals("2 Hours 1 Minute", DateUtil.minuteString(121));  
		assertEquals("6 Minutes", DateUtil.minuteString(6));  
	}
	
}
