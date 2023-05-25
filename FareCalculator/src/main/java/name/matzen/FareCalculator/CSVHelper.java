/**
 * 
 */
package name.matzen.FareCalculator;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import com.opencsv.exceptions.CsvException;

import name.matzen.FareCalculator.data.TapEvent;
import name.matzen.FareCalculator.data.Trip;

/**
 * @author BjarneMatzen
 *
 */
public class CSVHelper {
	
	
	static private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	
	
	/**
	 * Read the taps.csv file into a list of TapEvent objects
	 * 
	 * @return list of TapEvent objects
	 */
	public static List<TapEvent> getTapEvents() throws IOException, CsvException {
		Reader file = Files.newBufferedReader(Paths.get("src/main/resources/taps.csv"));
		
		// Build reader, skipping first line
		CSVReader csvReader = new CSVReaderBuilder(file).withSkipLines(1)
									.withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
									.build();
		
		List<String[]> csvData = csvReader.readAll();
		
		ArrayList<TapEvent> tapEvents = new ArrayList<>();
		
		for (String[] line : csvData) {
			TapEvent event = new TapEvent(
						line[0].trim(), // Id
						StringToInstantConverter(line[1].trim()), // timestamp
						line[2].trim(), // tapType
						line[3].trim(), // stopId
						line[4].trim(), // companyId
						line[5].trim(), // busId
						line[6].trim() // pan
						
					);
					
			tapEvents.add(event);
		}
		
		

		return(tapEvents);
	}
	

	/**
	 * Convert yyyy-MM-dd HH:mm:ss to Instant object
	 * @param dateString
	 * @return converted object
	 */
	private static Instant StringToInstantConverter(String dateString) {
		LocalDateTime localTime = LocalDateTime.parse(dateString, formatter);
		return localTime.toInstant(ZoneOffset.UTC);
	}
	
	/**
	 * Write out trip data to the CSV file
	 */
	public static void writeTripData(List<Trip> trips) throws IOException {
		String[] CSV_COLUMNS = { "Started", " Finished", " DurationSecs",
				" FromStopId", " ToStopId", " ChargeAmount", " CompanyId",
				" BusID", " PAN", " Status" };

		Writer writer = Files.newBufferedWriter(Paths.get("src/main/resources/trips.csv"));	
		
		CSVWriter csvWriter = new CSVWriter(writer,
	                CSVWriter.DEFAULT_SEPARATOR,
	                CSVWriter.NO_QUOTE_CHARACTER,
	                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
	                CSVWriter.DEFAULT_LINE_END);

		csvWriter.writeNext(CSV_COLUMNS);
		
		for (Trip trip : trips) {
			trip.writeToCSV(csvWriter);
		}
		
		csvWriter.close();
	}
	
}
