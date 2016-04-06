package app;

import java.util.ArrayList;

/**
 * A representation of the profile which we will show to the user. We bin the
 * delays into different amounts (we will show as a bar graph/histogram).
 * 
 * @author
 *
 */
public class DelayProfile {
	// these two should be the same length
	ArrayList<Integer> delays = new ArrayList<Integer>(); // [0,5,10,30,120]
															// delays of 0 time,
															// less than 5 mins,
															// less than 10...
	ArrayList<Integer> takeOffAmounts = new ArrayList<Integer>(); // [10,4,2,0,1]
																	// numbers
																	// of take
																	// off
																	// delays
																	// for this
																	// flight
	ArrayList<Integer> landingAmounts = new ArrayList<Integer>(); // [15,0,2,0,0]
																	// numbers
																	// of
																	// landing
																	// delays
																	// for this
																	// flight

	public DelayProfile() {

		delays.add(0);
		delays.add(5);
		delays.add(10);
		delays.add(30);
		delays.add(60);
		delays.add(120);

		takeOffAmounts.add(0);
		takeOffAmounts.add(0);
		takeOffAmounts.add(0);
		takeOffAmounts.add(0);
		takeOffAmounts.add(0);
		takeOffAmounts.add(0);

		landingAmounts.add(0);
		landingAmounts.add(0);
		landingAmounts.add(0);
		landingAmounts.add(0);
		landingAmounts.add(0);
		landingAmounts.add(0);
	}

	/**
	 * Adds a new delay into the profile.
	 * 
	 * @param delay
	 * @param takeOff
	 *            whether the event of the delay is a take off (true) or not
	 *            (false)
	 */
	public void addDelay(int delay, boolean takeOff) {
		if (takeOff == true) {
			if (delay >= 120) {
				takeOffAmounts.set(5, takeOffAmounts.get(5) + 1);
				
			}
			if (delay >= 60 && delay <120) {
				takeOffAmounts.set(4, takeOffAmounts.get(4) + 1);
				

			}
			if (delay >= 30 && delay <60) {
				takeOffAmounts.set(3, takeOffAmounts.get(3) + 1);
				
			}
			if (delay >= 10 && delay < 30) {
				takeOffAmounts.set(2, takeOffAmounts.get(2) + 1);
				
			}
			if (delay >= 5 && delay <10) {
				takeOffAmounts.set(1, takeOffAmounts.get(1) + 1);
				
			}
			if (delay <5) {
				takeOffAmounts.set(0, takeOffAmounts.get(0) + 1);
				

			}
		}
		if (takeOff == false) {
			if (delay >= 120) {
				landingAmounts.set(5, landingAmounts.get(5) + 1);
				

			}
			if (delay >= 60 && delay < 120) {
				landingAmounts.set(4, landingAmounts.get(4) + 1);
				
			}
			
			if (delay >= 30 && delay < 60) {
				landingAmounts.set(3, landingAmounts.get(3) + 1);
				
		
			}
			
			if (delay >= 10 && delay < 30) {
				landingAmounts.set(2, landingAmounts.get(2) + 1);
				
				
			}
			
			if (delay >= 5 && delay <10) {
				landingAmounts.set(1, landingAmounts.get(1) + 1);
				
				

			}
			

			if (delay < 5) {
				landingAmounts.set(0, landingAmounts.get(0) + 1);
				
				
			}
			
			
			
			
		}
	}
	
	

}
