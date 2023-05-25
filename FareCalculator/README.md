# Getting Started

Pull the repository and import it as a Maven project into your favorite IDE.

Run the Spring Boot FareCalculatorApplication. The trips.csv file will regenerate


# Assumptions

The project was created using JavaSE-17

Tap events are in chronological order

Tap data is clean and free from errors

Tapping off without tapping on (missed tap or insufficient morning coffee) is not considered

Fare data is not persisted in any database, so a simple object format is used and for performance reasons all fare permutations are generated up front into a memory cache

If there is a tap ON event, followed by another tap ON event with the same Company, Bus and PAN, the first tap ON is considered an incomplete trip

Test harness is for illustrative purposes only and not exhaustive
