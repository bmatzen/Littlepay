package name.matzen.FareCalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import name.matzen.FareCalculator.data.Trip;

@SpringBootTest
class FareCalculatorApplicationTests {
	
	private FareCalculator fareCalculator;
	
	@BeforeEach
    public void setup() {
        fareCalculator = new FareCalculator();
    }

	@Test
    public void calculateFare_ShouldReturnCorrectFare() {
        Trip trip = new Trip(Instant.now(),Instant.now(),"Stop1", "Stop3","Company1","Bus37","5500005555555559");
        Double expectedFare = 7.30;

        fareCalculator.calculateFare(trip);

        assertEquals(expectedFare, trip.getChargeAmount(), 0.01);
    }

	@Test
    public void calculateFare_ShouldReturnCorrectFareInReverse() {
        Trip trip = new Trip(Instant.now(),Instant.now(),"Stop3", "Stop1","Company1","Bus37","5500005555555559");
        Double expectedFare = 7.30;

        fareCalculator.calculateFare(trip);

        assertEquals(expectedFare, trip.getChargeAmount(), 0.01);
    }
	
	@Test
    public void calculateFare_ShouldReturnMaxFareStop1() {
        Trip trip = new Trip(Instant.now(),Instant.now(),"Stop1","","Company1","Bus37","5500005555555559");
        Double expectedFare = 7.30;

        fareCalculator.calculateFare(trip);

        assertEquals(expectedFare, trip.getChargeAmount(), 0.01);
    }

	@Test
    public void calculateFare_ShouldReturnMaxFareStop2() {
        Trip trip = new Trip(Instant.now(),Instant.now(),"Stop2","","Company1","Bus37","5500005555555559");
        Double expectedFare = 5.50;

        fareCalculator.calculateFare(trip);

        assertEquals(expectedFare, trip.getChargeAmount(), 0.01);
    }
	
}
