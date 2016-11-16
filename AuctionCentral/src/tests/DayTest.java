package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.AuctionRequest;
import model.Date;
import model.Day;
import model.Time;

public class DayTest {
	
	Date d1 = new Date(8, "November", 2016);
	Time t1 = new Time(1, 00, "pm");
	Date d2 = new Date(8, "November", 2016);
	Time t2 = new Time(2, 00, "pm");
	Date d3 = new Date(8, "November", 2016);
	Time t3 = new Time(3, 00, "pm");
	Date d4 = new Date(8, "November", 2016);
	Time t4 = new Time(12, 00, "am");
	Date d5 = new Date(8, "November", 2016);
	Time t5 = new Time(11, 00, "am");
	
	
	AuctionRequest myRequest = new AuctionRequest(d1, t1, "Non-profit-1");
	AuctionRequest myRequest2 = new AuctionRequest(d2, t2, "Non-profit-2");
	AuctionRequest myRequest3 = new AuctionRequest(d3, t3, "Non-profit-3");
	AuctionRequest myRequest4 = new AuctionRequest(d4, t4, "Non-profit-4");
	AuctionRequest myRequest5 = new AuctionRequest(d5, t5, "Non-profit-5");
	Day d;
	
	@Before
	public void setup() {
		d = new Day("November", 8, 2016);
	}

	@Test
	public void testCanAddAuction() {
		d.addAuction(myRequest);
		//both are pm times.
		assertFalse("test case can add auction within 2 hours", d.canAddAuction(myRequest2));
		assertTrue("test can add auction after 2 hours", d.canAddAuction(myRequest3));
		
		//checking one am and one pm time.
		assertFalse("test case can add auction within 2 hours am pm difference", d.canAddAuction(myRequest4));
		assertTrue("test case can add auction after 2 hours am pm difference", d.canAddAuction(myRequest5));
		d.addAuction(myRequest3);
		
		//already at 2 auctions trying to add another
		assertFalse("test can add a third auction", d.canAddAuction(myRequest4));
	}

	@Test
	public void testAddAuction() {
		assertTrue("test can add auction num auctions is 0", d.getNumAuctions() == 0);
		d.addAuction(myRequest);
		assertTrue("test can add auctions num auctions is 1", d.getNumAuctions() == 1);
		d.addAuction(myRequest5);
		assertTrue("test can add auctions num auctions is 2", d.getNumAuctions() == 2);
		d.addAuction(myRequest3);
		assertFalse("test can add auctions num auctions is 3", d.getNumAuctions() == 3);
		
	}
}
