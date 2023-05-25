package name.matzen.FareCalculator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import name.matzen.FareCalculator.data.TapEvent;
import name.matzen.FareCalculator.data.Trip;

@SpringBootApplication
public class FareCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FareCalculatorApplication.class, args);
		
		
		System.out.println("Calculating fares..");
		try {
			// We use an explicit ArrayList so we can enumerate by position
			List<TapEvent> events = CSVHelper.getTapEvents();
			
			List<Trip> trips = TripGenerator.generateTrips(events);
			
			CSVHelper.writeTripData(trips);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Calculation complete");
	}

}
