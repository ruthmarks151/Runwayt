package app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;

//import Data_Structures.SeparateChainingHashST;

/**
 * Reads the flight information from the file and puts it into a hash table. See
 * also DataAccess comments for more details.
 * Uses {@link app.Key}, {@link app.FlightEvent}, {@link app.TakeOff}, 
 * {@link app.Landing}
 *
 */
public class DataRead {

	private final static int NUMBER_OF_CARRIERS = 1610;
	private static String[] airline_Names = new String[NUMBER_OF_CARRIERS];
	private static String[] airline_ID = new String[NUMBER_OF_CARRIERS];

	private final static int NUMBER_OF_AIRPORTS = 6368;
	private static String[] airport_Names = new String[NUMBER_OF_AIRPORTS];
	private static String[] airport_ID = new String[NUMBER_OF_AIRPORTS];
	
	/**
	 * 
	 * @return An array of Strings, containing the airport names, sorted.
	 */
	public static String[] getAirportNames(){
		Arrays.sort(airport_Names);
		return airport_Names;
	}
	
	/**
	 * 
	 * @return An array of Strings, containing the airline names, sorted.
	 */
	public static String[] getAirline(){
		Arrays.sort(airline_Names);
		return airline_Names;
	}
	
	/**
	 * Used in {@link app.DataAccess}
	 * @return A Hashtable of flight events, using the airline and airport as the key.
	 */
	public static Hashtable<Key, FlightEvent> getEvents() {
		// reads data from other files
		readUniqueAirline();
		readUniqueAirport();

		// Makes the Hash Table
		Hashtable<Key, FlightEvent> flightEvents = new Hashtable<Key, FlightEvent>();

		String csvFile = "data_input/input_Data.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			// ignores the first line
			br.readLine();

			while ((line = br.readLine()) != null) {

				// gets data from the row
				String[] data = line.split(cvsSplitBy);
				if (data.length !=6)
					System.out.println(line);
				assert data.length == 6;
				// gets the date information
				Date day = parseDate(data[0]);

				// gets the airline information
				int indexOFAirline = Arrays.binarySearch(airline_ID, data[1]);
				if (!(indexOFAirline < 0)){
					String airline = airline_Names[indexOFAirline];
	
					
					int indexOrigin = Arrays.binarySearch(airport_ID, data[2]);
					assert indexOrigin >= 0;
					String originAirport = airport_Names[indexOrigin];
	
					int indexDest = Arrays.binarySearch(airport_ID, data[3]);
					assert indexDest >= 0;
					String destAirport = airport_Names[indexDest];
					
					// gets the delay time (in minutes) information
					// not so sure about using the int type; maybe should be some
					// sort of special time type
					int depDelay = (int)Double.parseDouble(data[4]);
					int arrDelay = (int)Double.parseDouble(data[5]);

					// creates the flight and key object that will be put into the
					// hash table
					FlightEvent takeOff = new TakeOff(day, depDelay, originAirport, airline);
					Key takeOffKey = new Key(airline, originAirport);
	
					FlightEvent landing = new Landing(day, arrDelay, destAirport, airline);
					Key landingKey = new Key(airline, destAirport);
					
					// puts the objects into the hash table
					flightEvents.put(takeOffKey, takeOff);
					flightEvents.put(landingKey, landing);

				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return flightEvents;

	}
	
	/**
	 * Writes the data from airline to an array
	 */
	private static void readUniqueAirline() {

		String csvFile = "data_input/carrier_ID.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			br.readLine(); // ignores the first line
			int i = 0; // initializes a counter

			while ((line = br.readLine()) != null) {

				String[] data = new String[2];
				int firstCommaIndex = line.indexOf(',');
				data[0] = line.substring(0, firstCommaIndex);
				data[1] = line.substring(firstCommaIndex+1, line.length());

				// stores the data from the file into the arrays
				airline_ID[i] = data[0];
				airline_Names[i] = stripQuotes(data[1]);

				i++; // increments the counter

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Writes the data from Airport to an array
	 * 
	 * @return
	 */
	private static void readUniqueAirport() {

		String csvFile = "data_input/airport_ID.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			br.readLine(); // ignores the first line
			int i = 0; // initializes a counter

			while ((line = br.readLine()) != null) {

				// gets data from the row
				String[] data = new String[2];
				int firstCommaIndex = line.indexOf(',');
				data[0] = line.substring(0, firstCommaIndex);
				data[1] = line.substring(firstCommaIndex+1, line.length());

				// stores the data from the file into the arrays
				airport_ID[i] = stripQuotes(data[0]);
				airport_Names[i] = stripQuotes(data[1]);

				i++; // increments the counter

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static String stripQuotes(String s){
		int lastIndex = s.length() - 1;
		if (!(s.charAt(0) == '"' && s.charAt(lastIndex) == '"'))
			System.out.println(s+ " doesn't have quotes");
		assert s.charAt(0) == '"' && s.charAt(lastIndex) == '"';
		return s.substring(1,lastIndex);
	}
	
	private static Date parseDate(String s){
		String[] date = s.split("-");
		int year = Integer.parseInt(date[0]);
		int month = Integer.parseInt(date[1]);
		int dateNumber = Integer.parseInt(date[2]);
		Date parsed = new Date(year, month, dateNumber);
		return parsed;
	}
}
