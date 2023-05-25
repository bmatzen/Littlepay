package name.matzen.FareCalculator;

import name.matzen.FareCalculator.data.Trip;

public class FareCalculator {
	
	private static FareCache fareCache = new FareCache();
	
	public void calculateFare(Trip trip) {
		// For readability
		String from = trip.getFromStopId();
		String to = trip.getToStopId();
		
		
		// Start with the easy case
		if (from.equals(to)) {
			trip.setChargeAmount(0.00);
			trip.setStatus("CANCELLED");
			return;		
		}

		// Regardless of the status, our cache will hold the right fare
		trip.setChargeAmount(fareCache.calculateFare(trip.getCompanyId(), trip.getBusId(), from, to));
		
		// Check if the trip was incomplete
		if ("".equals(to)) {
			trip.setStatus("INCOMPLETE");
			return;
		}
		
		// It has to be a completed trip
		trip.setStatus("COMPLETED");
		return;
	}

}
