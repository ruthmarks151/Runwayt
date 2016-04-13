package app;

import java.util.Date;

/**
 * An abstract data type to represent a flight event.
 *
 *
 */
public abstract class FlightEvent {
	// in local time, for now
	private Date date;
	private int delay;
	private String airport;
	private String airline;
	
	/**
	 * Creates an empty flight event.
	 */
	public FlightEvent() {

	}
	
	/**
	 * A getter for the date of the flight event.
	 * @return The date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * A getter for the delay of the flight event, in minutes
	 * @return The delay.
	 */
	public int getDelay() {
		return delay;
	}

	/**
	 * A getter for the airport.
	 * @return A string with the airport's name.
	 */
	public String getAirport() {
		return airport;
	}

	/**
	 * A getter for the airline.
	 * @return A string with the airline's name.
	 */
	public String getAirline() {
		return airline;
	}
	
	/**
	 * Construct a new flight event with the specified date, delay, airport and airline.
	 * @param date The date of the flight event.
	 * @param delay The delay, in minutes.
	 * @param airport The airport of the flight event.
	 * @param airline The airline of the flight event.
	 */
	public FlightEvent(Date date, int delay, String airport, String airline) {
		this.date = date;
		this.delay = delay;
		this.airport = airport;
		this.airline = airline;
	}
	
	/**
	 * Returns a string representation of the flight event.
	 * <br>
	 * Flight Event at (date) by (airline) with a delay of (delay) minutes
	 */
	public String toString() {
		return this.getClass().getName() + " at " + getDate().toString() + " by " + getAirline() + " with a delay of "
				+ getDelay() + " minutes";
	}
}
