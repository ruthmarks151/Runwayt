import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import app.Landing;


public class LandingTest {

	@Test
	public void test() {
		String[] airlines = {"line1","line2","line3"};
		String[] airports = {"port1","port2","port3"};
		int[] delays = {0,1,2};
		
		for (int i=0; i<airlines.length; i++) {
			Landing e = new Landing(new Date(2000, 12, 12),delays[i],airports[i],airlines[i]);
			assert e.getAirline().equals(airlines[i]);
		}
		
	}

}
