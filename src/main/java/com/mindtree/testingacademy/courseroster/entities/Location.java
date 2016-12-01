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
}
