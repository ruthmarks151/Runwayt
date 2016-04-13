package app;


/** Class Key is used as a method to use both airline name,
 * and airport as a key in the hash table
 * Used in {@link app.DataAccess}, {@link app.DataRead}, 
 */
public class Key {

	private final String airline;
	private final String airport;

	/**
	 * Constructs a key based on the given airline and airport.
	 * @param x the airline
	 * @param y the airport
	 */
	public Key(String x, String y) {
		this.airline = x;
		this.airport = y;
	}
	
	@Override
	/**
	 * Checks that the object is equal to the key
	 * @param o an object to compare to
	 * @return True if the objects are the same, false if not.
	 */
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Key))
			return false;
		Key key = (Key) o;
		return airline == key.airline && airport == key.airport;
	}

}
