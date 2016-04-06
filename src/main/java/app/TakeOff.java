package app;

import java.util.Date;

/**
 * An abstract data type to represent a take off.  Inherits from FlightEvent.
 * @author 
 *
 */
public class TakeOff extends FlightEvent {
	
	public TakeOff() {
		
	}
	
	public TakeOff(Date date, int delay, String airport,String airline) {
		super(date,delay,airport,airline);
	}
}
