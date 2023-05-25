/**
 * 
 */
package name.matzen.FareCalculator;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Cache all fare permutations and pre-calculate all max fares to speed up 
 * execution time and reduce energy consumption. Writing "Green" code is a thing...
 * 
 * @author BjarneMatzen
 *
 */
public class FareCache {
	
	private Map<String,Double> cache = new HashMap<String,Double>();
	
	
	
	/**
	 * Generate the fare cache based on a static list 
	 */
	public FareCache() {
		cache.put("company1:bus37:stop1:stop2", 3.25);
		cache.put("company1:bus37:stop2:stop3", 5.50);
		cache.put("company1:bus37:stop1:stop3", 7.30);
		
		// Max Fares

		// This is a little lazy. I could have created code to generate it, but
		// for the sake of this exercise it was faster to just add them manually

		cache.put("company1:bus37:stop1:", 7.30);
		cache.put("company1:bus37:stop2:", 5.50);
		cache.put("company1:bus37:stop3:", 7.30);
		
	}
	
	/**
	 * Look up the fare to charge in the cache
	 * 
	 * @param stop1
	 * @param stop2
	 * @return the cached fare
	 */
	public Double calculateFare(String company, String bus, String stop1, String stop2) throws NullPointerException {
		Double fare = cache.get((company+":"+bus+":"+stop1+":"+stop2).toLowerCase());
		
		// Cater for case where trip is in reverse
		if (fare==null) fare = cache.get((company+":"+bus+":"+stop2+":"+stop1).toLowerCase());
		
		return fare;
	}

}
