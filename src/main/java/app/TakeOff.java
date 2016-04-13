package app;

import java.util.Date;

/**
 * An abstract data type to represent a take off.  Inherits from {@link app.FlightEvent}. 
 *
 */
public class TakeOff extends FlightEvent {
	
	public TakeOff() {
		
	}
	
	/**
	 * A constructor for a TakeOff object, based on the date, delay in minutes, the 
	 * airport and the airline.
	 * @param date
	 * @param delay in minutes
	 * @param airport
	 * @param airline
	 */
	public TakeOff(Date date, int delay, String airport,String airline) {
		super(date,delay,airport,airline);
	}
}
