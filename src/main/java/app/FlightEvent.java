package app;

import java.util.Date;

/**
 * An abstract data type to represent a flight event.
 * 
 * @author
 *
 */
public abstract class FlightEvent {
	// in local time, for now
	protected Date date;
	// not so sure about using the int type; maybe should be some sort of
	// special time type
	protected int delay;
	protected String airport;
	protected String airline;

	public FlightEvent() {

	}

	public Date getDate() {
		return date;
	}

	public int getDelay() {
		return delay;
	}

	public String getAirport() {
		return airport;
	}

	public String getAirline() {
		return airline;
	}

	public FlightEvent(Date date, int delay, String airport, String airline) {
		this.date = date;
		this.delay = delay;
		this.airport = airport;
		this.airline = airline;
	}

	public String toString() {
		return this.getClass().getName() + " at " + getDate().toString() + " by " + getAirline() + " with a delay of "
				+ getDelay() + " delay units";
	}
}
