package app;

import java.util.Date;

/**
 * An abstract data type to represent a landing event. Inherits from {@link app.FlightEvent}. 
 * 
 */
public class Landing extends FlightEvent {
	/**
	 * Creates empty Landing event.
	 */
	public Landing() {
		
	}
	/**
	 * A constructor for a Landing object, based on the date, delay in minutes, the airport and the airline.
	 * @param date
	 * @param delay in minutes
	 * @param airport
	 * @param airline
	 */
	public Landing(Date date, int delay, String airport, String airline) {
		super(date,delay,airport,airline);
	}
}
