/**
 * 
 */
package com.mindtree.testingacademy.courseroster.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

/**
 * @author azureUser
 *
 */
@Component
@Entity
@Table(name = "Event_Register")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Event_Id", nullable = false)
	private int eventId;

	@Column(name = "Event_Name", nullable = false)
	private String eventName;

	@Column(name = "Event_Details")
	private String eventDetails;

	@ManyToOne(targetEntity = Location.class)
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "Location_Id", nullable = false)
	private Location location;

	@Column(name = "Event_Date", nullable = false)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date eventDate;

	@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "User_Id", nullable = false)
	private User user;

	/**
	 * 
	 */
	public Event() {
	}

	/**
	 * @param eventId
	 * @param eventName
	 * @param location
	 * @param eventDate
	 * @param user
	 */
	public Event(int eventId, String eventName, Location location, Date eventDate, User user) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.location = location;
		this.eventDate = eventDate;
		this.user = user;
	}

	/**
	 * @param eventName
	 * @param location
	 * @param eventDate
	 * @param user
	 */
	public Event(String eventName, Location location, Date eventDate, User user) {
		super();
		this.eventName = eventName;
		this.location = location;
		this.eventDate = eventDate;
		this.user = user;
	}

	/**
	 * @return the eventId
	 */
	public int getEventId() {
		return eventId;
	}

	/**
	 * @param eventId
	 *            the eventId to set
	 */
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * @param eventName
	 *            the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * @return the eventDate
	 */
	public Date getEventDate() {
		return eventDate;
	}

	/**
	 * @param eventDate
	 *            the eventDate to set
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	public String format(Date date) {
		return new SimpleDateFormat("dd MMM yyyy").format(date);
	}

	/**
	 * @return
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/**
	 * @param eventDetails
	 */
	public void setEventDetails(String eventDetails) {
		this.eventDetails = eventDetails;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eventId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Event evt = (Event) obj;
		if (evt.getEventId() == this.eventId)
			return true;
		else
			return false;
	}

}
