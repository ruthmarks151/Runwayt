package app;

import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.TreeMap;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;

/**
 * Uses a hash table (generated using DataRead) to store the flights.
 * A bunch of specialized functions retrieve flights with various properties
 * i.e. close to the current flight 
 * @author 
 *
 */
public class DataAccess {
	
	private static Hashtable<Key,FlightEvent> allFlights = DataRead.getEvents();
	private static Hashtable<String, TreeMap<Date,TakeOff>> takeOffs = new Hashtable<>();
	private static  Hashtable<String, TreeMap<Date, Landing>> landings = new Hashtable<>();
	private static boolean prepared = false;
	
	public static void prepare(){
		assert !prepared;
		Collection<FlightEvent> events = allFlights.values();
		System.out.println("Restructuring " + events.size() + " elements of data for accessing");
		for (FlightEvent fe:events){
			String airport = fe.getAirport();
			Date date = fe.getDate();
			
			if (fe instanceof TakeOff){
				if (!takeOffs.containsKey(airport))
					takeOffs.put(airport, new TreeMap<Date,TakeOff>());
				takeOffs.get(airport).put(date, (TakeOff)fe);
			}else if(fe instanceof Landing){
				if (!landings.containsKey(airport))
					landings.put(airport, new TreeMap<Date,Landing>());
				landings.get(airport).put(date, (Landing)fe);
			}else{
				System.out.println(fe.getClass().getName());
				throw new RuntimeException();
			}
			//System.out.println(fe.toString());
		}
		System.out.println("Data restructured");
		prepared = true;
	}
	
	/**
	 * 
	 * @param airport
	 * @param airline
	 * @param date
	 * @return A takeoff event with the given airport and airline; closest to the given date.
	 */
	public static TakeOff getTakeOff(String airport, String airline, Date date) {
		assert prepared;
		TreeMap<Date,TakeOff> airportTakeOffs= takeOffs.get(airport);
		if (airportTakeOffs == null)
			System.out.println(airport);
		assert airportTakeOffs != null;
		Date closest = airportTakeOffs.ceilingKey(date);
		if (closest == null)
			closest = airportTakeOffs.floorKey(date);
		assert closest != null;
		return airportTakeOffs.get(closest);
	}
	
	/**
	 * 
	 * @param airport
	 * @param airline
	 * @param date
	 * @return A landing event with the given airport and airline; closest to the given date.
	 */
	public static Landing getLanding(String airport, String airline, Date date) {
		assert prepared;
		TreeMap<Date,Landing> airportLandings= landings.get(airport);
		assert airportLandings != null;
		Date closest = airportLandings.ceilingKey(date);
		if (closest == null)
			closest = airportLandings.floorKey(date);
		assert closest != null;
		return airportLandings.get(closest);
	}
	
	
	//hash tables to store flight events; built in java one should work (need to ask)
	//first keyed by the airport 
	//second keyed by the airline string
	//then an ArrayList of FlightEvents, sorted by date/time
	//public static (hashtable) tableEvents;
}
