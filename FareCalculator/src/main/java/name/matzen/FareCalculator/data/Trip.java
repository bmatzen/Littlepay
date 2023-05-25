/**
 * 
 */
package name.matzen.FareCalculator.data;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.opencsv.CSVWriter;

/**
 * 
 * Object representation of a trip charge
 * 
 * @author BjarneMatzen
 *
 */
public class Trip {
	
	static private DateTimeFormatter formatter = DateTimeFormatter
													.ofPattern("dd-MM-yyyy HH:mm:ss")
													.withZone( ZoneId.of("UTC"));

	
	Instant started;
	Instant finished;
	int duration;
	String fromStopId;
	String toStopId;
	Double chargeAmount;
	String companyId;
	String busId;
	String pan;
	String status;
	
	/**
	 * Trip details
	 * 
	 * @param started
	 * @param finished
	 * @param fromStopId
	 * @param toStopId
	 * @param companyId
	 * @param busId
	 * @param pan
	 */
	public Trip(Instant started, Instant finished, String fromStopId, String toStopId,
			String companyId, String busId, String pan) {
		super();
		this.started = started;
		this.finished = finished;
		this.fromStopId = fromStopId;
		this.toStopId = toStopId;
		this.companyId = companyId;
		this.busId = busId;
		this.pan = pan;

		// No need to pass the duration, we calculate it here 
		if (finished!=null) { // Finished can be null if incomplete trip 
			this.duration = (int)(finished.toEpochMilli()-started.toEpochMilli())/1000;
		}

	}
	
	
	/**
	 * Generate CSV data for this trip
	 * @param csvWriter 
	 */
	public void writeToCSV(CSVWriter csvWriter) {
		String[] csvData = {
				formatter.format(started),
				formatter.format(finished),
				""+duration,
				fromStopId,
				toStopId,
				chargeAmount.toString(),
				companyId,
				busId,
				pan,
				status
		};
		csvWriter.writeNext(csvData);
	}


	/**
	 * @return the chargeAmount
	 */
	public Double getChargeAmount() {
		return chargeAmount;
	}


	/**
	 * @param chargeAmount the chargeAmount to set
	 */
	public void setChargeAmount(Double chargeAmount) {
		this.chargeAmount = chargeAmount;
	}


	/**
	 * @return the fromStopId
	 */
	public String getFromStopId() {
		return fromStopId;
	}


	/**
	 * @param fromStopId the fromStopId to set
	 */
	public void setFromStopId(String fromStopId) {
		this.fromStopId = fromStopId;
	}


	/**
	 * @return the toStopId
	 */
	public String getToStopId() {
		return toStopId;
	}


	/**
	 * @param toStopId the toStopId to set
	 */
	public void setToStopId(String toStopId) {
		this.toStopId = toStopId;
	}


	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @return the companyId
	 */
	public String getCompanyId() {
		return companyId;
	}


	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}


	/**
	 * @return the busId
	 */
	public String getBusId() {
		return busId;
	}


	/**
	 * @param busId the busId to set
	 */
	public void setBusId(String busId) {
		this.busId = busId;
	}

	
	
}
