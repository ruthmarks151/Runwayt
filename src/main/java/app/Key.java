package app;


/* Class Key is used as a method to use both airline name,
 * and airport as a key in the hash table
 */

public class Key {

	private final String airline;
	private final String airport;

	public Key(String x, String y) {
		this.airline = x;
		this.airport = y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Key))
			return false;
		Key key = (Key) o;
		return airline == key.airline && airport == key.airport;
	}

}
