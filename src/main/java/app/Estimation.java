package app;

import java.util.Date;
import java.util.Random;

/**
 * The Estimation class calculates the expected delays of the flight. The important
 * function here is findDelay which calculates the DelayProfile.
 * @author
 *
 */
public class Estimation {
	private static final double stdevDays = 5;
	private static final double stdevHours = 3;
	private static final int HOUR_IN_MILLISECS = 1000 * 60 * 60;
	private static final int DAY_IN_MILLISECS = 24 * HOUR_IN_MILLISECS;
	private static final int nSamples = 100;
	private static final Random r = new Random();
	
	public static DelayProfile findDelay(String origin, String destination, String airline) {
		Date today = new Date(2016, 1, 15);
		DelayProfile dProfile = new DelayProfile();
		for (int i = 0; i < nSamples; i++) {
			Date sampleDate = sampleDate(today);
			TakeOff t = DataAccess.getTakeOff(origin, airline, sampleDate);
			Landing l = DataAccess.getLanding(destination, airline, sampleDate);
			dProfile.addDelay(t.getDelay() + l.getDelay());
		}
		return dProfile;
	}
	
	public static double rand(){
		return r.nextGaussian();
	}
	
	public static Date sampleDate(Date mean){
		Date sample = new Date();
		//sample.setYear(sample.getYear());
		sample.setTime(mean.getTime() + (int)(rand() * stdevHours) * HOUR_IN_MILLISECS + (int)(rand() * stdevDays) * DAY_IN_MILLISECS);
		return sample;
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
