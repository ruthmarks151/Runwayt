package app;

import java.util.ArrayList;

/**
 * A representation of the profile which we will show to the user. We bin the
 * delays into different amounts (we will show as a bar graph/histogram).
 * 
 * @author
 *
 */
public class DelayProfile {

	private final int minDelay = -90;
	private final int maxDelay = 300;
	private final int delta = 5;
	private final int numEl = (maxDelay - minDelay) / delta;
	int maxIndex = 0;
	// these two should be the same length
	int[] delays = new int[numEl]; // [0,5,10,30,120]
									// delays of 0 time,
									// less than 5 mins,
									// less than 10...
	int[] frequencies = new int[numEl]; // [10,4,2,0,1] numbers of take off delays  for this flight

	public DelayProfile() {
		for (int i = 0; i < numEl; i++) {
			delays[i] = minDelay + i * delta;
			frequencies[i] = 0;
		}
	}

	/**
	 * Adds a new delay into the profile.
	 * 
	 * @param delay
	 */
	public void addDelay(int delay) {
		if (delay < minDelay)
			frequencies[0]++;
		else if (delay > maxDelay - delta)
			frequencies[numEl-1]++;
		else{
			int index = (delay-minDelay)/delta;
			frequencies[index]++;	
			if (index > maxIndex)
				maxIndex = index;
		}
	}
	
	public String delays(){
		boolean primed = false;
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for (int i = 0; i <= maxIndex; i++) {
			if(primed){
				sb.append(", ");
				sb.append(delays[i]);
			}else{
				if (frequencies[i] > 0){
					primed = true;
					sb.append(delays[i]);
				}
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	public String freqs(){
		boolean primed = false;
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for (int i = 0; i <= maxIndex; i++) {
			if(primed){
				sb.append(", ");
				sb.append(frequencies[i]);
			}else{
				if (frequencies[i] > 0){
					primed = true;
					sb.append(frequencies[i]);
				}
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	public String toString(){
		boolean primed = false;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= maxIndex; i++) {
			if(primed){
				sb.append(delays[i]);
				sb.append(" :");
				for (int j = 0; j < frequencies[i]; j++ ){
					sb.append('O');
				}
				sb.append("<br>");
			}else{
				if (frequencies[i] > 0){
					primed = true;
					sb.append(delays[i]);
					sb.append(" :");
					for (int j = 0; j < frequencies[i]; j++ ){
						sb.append('O');
					}
					sb.append("<br>");
				}
			}
		}
		return sb.toString();
	}

}
