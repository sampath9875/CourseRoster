/**
 * 
 */
package com.mindtree.testingacademy.courseroster.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author azureUser
 *
 */
@Entity
@Table(name = "LocationDetails")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Location_Id", nullable = false)
	private int locationId;

	@Column(name = "Location_Details")
	private String locationDetails;

	/**
	 * 
	 */
	public Location() {
	}

	/**
	 * @param locationId
	 * @param locationDetails
	 */
	public Location(String locationDetails) {
		super();
		this.locationDetails = locationDetails;
	}

	/**
	 * @param locationId
	 * @param locationDetails
	 */
	public Location(int locationId, String locationDetails) {
		super();
		this.locationId = locationId;
		this.locationDetails = locationDetails;
	}

	/**
	 * @return the locationId
	 */
	public int getLocationId() {
		return locationId;
	}

	/**
	 * @param locationId
	 *            the locationId to set
	 */
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	/**
	 * @return the locationDetails
	 */
	public String getLocationDetails() {
		return locationDetails;
	}

	/**
	 * @param locationDetails
	 *            the locationDetails to set
	 */
	public void setLocationDetails(String locationDetails) {
		this.locationDetails = locationDetails;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((locationDetails == null) ? 0 : locationDetails.hashCode());
		result = prime * result + locationId;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (locationDetails == null) {
			if (other.locationDetails != null)
				return false;
		} else if (!locationDetails.equals(other.locationDetails))
			return false;
		if (locationId != other.locationId)
			return false;
		return true;
	}
	
	
}
