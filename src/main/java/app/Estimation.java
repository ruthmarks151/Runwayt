package app;

/**
 * The Estimation class calculates the expected delays of the flight. The important
 * function here is findDelay which calculates the DelayProfile.
 * @author
 *
 */
public class Estimation {
	/**
	 * Should return a profile of the amount by which the flights are delayed.
	 * @param takeoff
	 * @param landing
	 * @return
	 */
	public DelayProfile findDelay(TakeOff takeoff, Landing landing) {
		DelayProfile profile = new DelayProfile();
		for (int i=0; i<20; i++) {
			profile.addDelay(getEventDelay(takeoff),true);
			profile.addDelay(getEventDelay(landing),false);
		}
		return new DelayProfile();
	}
	
	/*
	 * use some private functions to pick a random time, day, year from the profile
	 * 
	 */
	/**
	 * This is probably a function to find the delay for one event of the flight.
	 * @param event
	 * @return
	 */
	private static int getEventDelay(FlightEvent event) {
		return 0;
	}

}
