/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */


package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Bidder;
import model.Item;
import model.NonProfit;
import model.Staff;

/**
 * The test class for item class.
 * @author Dmitriy Onishchenko, Georgia Wade.
 *
 */
public class ItemTest {	
	
	private Item myItem;
	private Item myitem2;	
	private Bidder myBidder1;
	private Bidder myBidder2;
	private Bidder myUnregisterBidder;
	private Staff myStaff1;
	private NonProfit myNonProfit1;

	@Before
	public void setup() {
		
		myItem = new Item("Autographed Sounders Jersey", "Good", "medium",
				50, "Seattle Sounders", "Game worn jersy signed by Brad Evan.",
				"CenturyLink Field front office has the jersey");
		
		myitem2 = new Item("Football", "good", "small", 100, "Bill and Melinda Gates", 
						"Really cool football", "hello");		
		
		myNonProfit1 = new NonProfit("Bill Gates", "billy", "1234",
				"Bill and Melinda Gates", "1234 Mercer Island", "111-111-1111");		
		
		myStaff1 = new Staff("Bill Clinton", "billyC", "2345");
		
		myBidder1 = new Bidder("Dave Mathews", "dave", "3456", 
				"d@gmail.com", "1111 11th st San Diego", "111-111-1111");
		
		myBidder2 = new Bidder("Steve harvey", "s", "3456", 
				"d@gmail.com", "1111 11th st San Diego", "111-111-1111");
		
		myUnregisterBidder = new Bidder("George Bush", "", "", 
				"d@gmail.com", "1111 11th st San Diego", "111-111-1111");		
		
		myitem2.makeBid(myBidder1, 300);
		
	}
	
	
	@Test
	public void testMakeBidBidderNotPreviouslyBid() {		
		assertTrue(myitem2.makeBid(myBidder2, 200));	
		
	}
	
	@Test
	public void testMakeBidBidderPreviouslyBid() {
		
		assertFalse(myitem2.makeBid(myBidder1, 200));	
		
	}
	
	@Test
	public void testMakeBidUnregisteredBidder() {		
		assertFalse(myitem2.makeBid(myUnregisterBidder, 200));	
		
	}
	
	@Test
	public void testMakeBidRegisteredBidder() {		
		assertTrue(myitem2.makeBid(myBidder2, 200));			
	}
	
	@Test
	public void testMakeBidAsStaffMember() {		
		assertFalse(myitem2.makeBid(myStaff1, 200));			
	}
	
	@Test
	public void testMakeBidAsNonProfit() {		
		assertFalse(myitem2.makeBid(myNonProfit1, 200));			
	}
	
	@Test
	public void testIsValidBidPriceOnNegativeBid(){
		assertEquals(false, myItem.isValidBidPrice(-1));
	}
	
	@Test
	public void testIsValidBidPriceOnZeroBid(){
		assertEquals(false, myItem.isValidBidPrice(0));
	}
	
	@Test
	public void testIsValidBidPriceOnLessThanMinBid(){
		assertEquals(false, myItem.isValidBidPrice(25));
	}
	
	@Test
	public void testIsValidBidPriceOnEqualToMinBid(){
		assertEquals(true, myItem.isValidBidPrice(50));
	}
	
	@Test
	public void testIsValidBidPriceOnGreaterThanMinBid(){
		assertEquals(true, myItem.isValidBidPrice(75));
	}
	
}
