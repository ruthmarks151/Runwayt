package app;

import java.util.Date;

/**
 * An abstract data type to represent a landing event. Inherits from FlightEvent.
 * @author 
 * 
 */
public class Landing extends FlightEvent {
	public Landing() {
		
	}
	/**
	 * A constructor for a Landing object.
	 * @param date
	 * @param delay
	 * @param airport
	 * @param airline
	 * @param delayReason
	 */
	public Landing(Date date, int delay, String airport, String airline) {
		super(date,delay,airport,airline);
	}
}
