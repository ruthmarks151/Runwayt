import static org.junit.Assert.*;

import org.junit.Test;

import app.Key;


public class KeyTest {

	@Test
	public void testEqualsObject() {
		Key k = new Key("airline","airport");
		Key l = new Key("airline","airport");
		assert(k.equals(l));
		assert(l.equals(k));
		assert(k.equals(l) == l.equals(k));
	}

}
