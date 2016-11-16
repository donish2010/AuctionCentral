/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */


package model;

import java.io.Serializable;

/**
 * Used to keep track of auctions happening
 * every day.
 * 
 * @author caseycao
 * @version 1
 *
 */
public class Day implements Serializable {
	
	/**
	 * Serializable Id.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * This information is shared by both auction
	 */
	private String myMonth;
	private int myDay;
	private int myYear;
	/**
	 * auctions going on today.
	 */
	private int myNumAuctions;
	
	/**
	 * This is for the first auction made.
	 */

	private Auction myAuction;
	
	
	/**
	 * This is for the second auction made.
	 */
	public Auction myAuction2;
	
	/**
	 * This is used to fill out default information shared by
	 * both auctions.
	 * 
	 * @param theMonth int
	 * @param theDay int
	 * @param theYear int
	 */
	public Day(final String theMonth, final int theDay, final int theYear) {
		myMonth = theMonth;
		myDay = theDay;
		myYear = theYear;
		myNumAuctions = 0;
	}
	
	/**
	 * Used to get the day.
	 * 
	 * @return day
	 */
	public int getDay (){
		return myDay;
	}
	
	/**
	 * Used to get month
	 * 
	 * @return month
	 */
	public String getMonth() {
		return myMonth;
	}
	
	public Auction getAuction() {
		return myAuction;
	}
	
	public Auction getAuction2() {
		return myAuction2;
	}
	
	/**
	 * Used to get year
	 * 
	 * @return year
	 */
	public int getYear() {
		return myYear;
	}
	
	/**
	 * Used to get number of
	 * auctions this day.
	 * 
	 * @return year
	 */
	public int getNumAuctions() {
		return myNumAuctions;
	}
	
	public boolean canAddAuction(AuctionRequest theRequest) {
		boolean case1 = false;
		boolean case2 = false;
		boolean case3 = false;
		boolean case4 = false;
		
		if(myNumAuctions == 0) {
			return true;
		}
		
		if(myAuction.getTime().getPeriod().toUpperCase().equals("AM") && theRequest.getTime().getPeriod().toUpperCase().equals("AM")) {
			case1 = true;
		}
		if(myAuction.getTime().getPeriod().toUpperCase().equals("PM") && theRequest.getTime().getPeriod().toUpperCase().equals("PM")) {
			case2 = true;
		}
		if(myAuction.getTime().getPeriod().toUpperCase().equals("AM") && theRequest.getTime().getPeriod().toUpperCase().equals("PM")) {
			case3 = true;
		}
		if(myAuction.getTime().getPeriod().toUpperCase().equals("PM") && theRequest.getTime().getPeriod().toUpperCase().equals("AM")) {
			case4 = true;
		}
		
		if(myNumAuctions == 1) {
			int maxTime = Math.max(myAuction.getTime().getHour(), theRequest.getTime().getHour());
			int minTime = Math.min(myAuction.getTime().getHour(), theRequest.getTime().getHour());
			
			if(case1 || case2) {
				if(maxTime - minTime >= 2) {
					return true;
				} else {
					return false;
				}
			} else if(case3 || case4) {
				if(maxTime - minTime > 10) {
					return false;
				} else {
					return true;
				}	
			}
		}
		return false;
	}
	
	public void addAuction(AuctionRequest theRequest) {
		Date date = theRequest.getDate();
		Time time = theRequest.getTime();
		String name = theRequest.getNonProfitName();
		if(myNumAuctions == 0 && canAddAuction(theRequest)) {
			myAuction = new Auction(name, date, time);
			myNumAuctions++;
		} else if (myNumAuctions == 1 && canAddAuction(theRequest)) {
			myAuction2 = new Auction(name, date, time);
			myNumAuctions++;
		}
		
	}

}
