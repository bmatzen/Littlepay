/**
 * 
 */
package name.matzen.FareCalculator;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import name.matzen.FareCalculator.data.TapEvent;
import name.matzen.FareCalculator.data.Trip;

/**
 * Generated the trips taken from the TapEvents
 * 
 * 
 * @author BjarneMatzen
 *
 */
public class TripGenerator {
	
	public static List<Trip> generateTrips(List<TapEvent> events) {
		
		List<Trip> trips = new ArrayList<Trip>();
		FareCalculator fareCalculator = new FareCalculator();
		
		// We need to process all events and generate the corresponding trips

		// As an OFF event will always follow an ON event, we hunt forward in the list using 
		// positional index. We could also implement this with Lambdas and Streams
		for (int loop=0; loop<events.size(); loop++) {
			
			TapEvent onEvent = events.get(loop);
			boolean tapOffFound = false;
			if ("ON".equalsIgnoreCase(onEvent.getTapType())) {
				Trip trip = null;
				// Hunt forward
				for (int loop2=loop+1; loop2<events.size(); loop2++) {
					TapEvent offEvent = events.get(loop2);
					
					// IF we encounter another matching tap ON, it is actually an
					// incomplete event
					if (onEvent.matchesPreviousTapOn(offEvent)) {
						break;
					}
					
					if (onEvent.matchesTapOn(offEvent)) {
						trip = new Trip(onEvent.getTimestamp(),offEvent.getTimestamp(), onEvent.getStopId(),
								offEvent.getStopId(),onEvent.getCompanyId(),onEvent.getBusId(),onEvent.getPan());
						tapOffFound = true;
					}
				}

				// If we didn't find a tap off, create a trip with a blank destination
				if (!tapOffFound) {
					trip = new Trip(onEvent.getTimestamp(),onEvent.getTimestamp(), onEvent.getStopId(),
							"",onEvent.getCompanyId(),onEvent.getBusId(),onEvent.getPan());

				}
				
				// Calculate fare and add to the trips
				fareCalculator.calculateFare(trip);
				
				trips.add(trip);
			}
		}
		
		return(trips);
		
	}

}
