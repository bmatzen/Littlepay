/**
 * 
 */
package name.matzen.FareCalculator.data;

import java.time.Instant;

/**
 * 
 * Object representation of a tap event
 * 
 * @author BjarneMatzen
 *
 */
public class TapEvent {
	
	String id;
	Instant timestamp;
	String tapType;
	String stopId;
	String companyId;
	String busId;
	String pan;
	
	/**
	 * Tap Events
	 * 
	 * @param id
	 * @param timestamp
	 * @param tapType
	 * @param stopId
	 * @param companyId
	 * @param busId
	 * @param pan
	 */
	public TapEvent(String id, Instant timestamp, String tapType, String stopId, String companyId, String busId,
			String pan) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.tapType = tapType;
		this.stopId = stopId;
		this.companyId = companyId;
		this.busId = busId;
		this.pan = pan;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the timestamp
	 */
	public Instant getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the tapType
	 */
	public String getTapType() {
		return tapType;
	}

	/**
	 * @param tapType the tapType to set
	 */
	public void setTapType(String tapType) {
		this.tapType = tapType;
	}

	/**
	 * @return the stopId
	 */
	public String getStopId() {
		return stopId;
	}

	/**
	 * @param stopId the stopId to set
	 */
	public void setStopId(String stopId) {
		this.stopId = stopId;
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

	/**
	 * @return the pan
	 */
	public String getPan() {
		return pan;
	}

	/**
	 * @param pan the pan to set
	 */
	public void setPan(String pan) {
		this.pan = pan;
	}
	
	
	/**
	 * Check if this event is a tap-off for a previous tap-on event
	 * @param event to check
	 * @return match
	 */
	public boolean matchesTapOn(TapEvent event) {
		if (event.getCompanyId().equals(companyId)
			&& event.getBusId().equals(busId)	
			&& event.getPan().equals(pan)
			&& event.getTapType().equalsIgnoreCase("OFF")) {
			return true;
		} else {
			return false;
		}
			
	}

	/**
	 * Check if this event is a tap-on at the same location as a previous tap-on event
	 * If so, the previous event is an incomplete trip
	 * @param event to check
	 * @return match
	 */
	public boolean matchesPreviousTapOn(TapEvent event) {
		if (event.getCompanyId().equals(companyId)
			&& event.getBusId().equals(busId)	
			&& event.getPan().equals(pan)
			&& event.getTapType().equalsIgnoreCase("ON")) {
			return true;
		} else {
			return false;
		}
			
	}
	
	
}
