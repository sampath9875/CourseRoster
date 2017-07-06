/**
 * 
 */
package com.mindtree.testingacademy.courseroster.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * @author azureUser
 *
 */
@Component
@Entity
@Table(name = "Volunteer_Event_Register")
public class EventRegistration {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Registration_Id", nullable = false)
	private int registrationId;

	@Column(name = "Volunteer_Id", nullable = false)
	private String volunteerId;

	@Column(name = "Volunteer_Name", nullable = false)
	private String volunteerName;

	@Column(name = "Volunteer_Email", nullable = false)
	private String volunteerEmail;

	@Column(name = "Volunteer_phoneno", nullable = false)
	private String volunteerPhoneno;

	@ManyToOne(targetEntity = Event.class)
	@JoinColumn(name = "Event_Id", nullable = false)
	private Event event;

	/**
	 * 
	 */
	public EventRegistration() {
	}

	/**
	 * @param volunteerId
	 * @param volunteerName
	 * @param volunteerEmail
	 * @param volunteerPhoneno
	 * @param event
	 */
	public EventRegistration(String volunteerId, String volunteerName, String volunteerEmail, String volunteerPhoneno,
			Event event) {
		super();
		this.volunteerId = volunteerId;
		this.volunteerName = volunteerName;
		this.volunteerEmail = volunteerEmail;
		this.volunteerPhoneno = volunteerPhoneno;
		this.event = event;
	}

	/**
	 * @param registrationId
	 * @param volunteerId
	 * @param volunteerName
	 * @param volunteerEmail
	 * @param volunteerPhoneno
	 * @param event
	 */
	public EventRegistration(int registrationId, String volunteerId, String volunteerName, String volunteerEmail,
			String volunteerPhoneno, Event event) {
		super();
		this.registrationId = registrationId;
		this.volunteerId = volunteerId;
		this.volunteerName = volunteerName;
		this.volunteerEmail = volunteerEmail;
		this.volunteerPhoneno = volunteerPhoneno;
		this.event = event;
	}

	/**
	 * @return the registrationId
	 */
	public int getRegistrationId() {
		return registrationId;
	}

	/**
	 * @param registrationId
	 *            the registrationId to set
	 */
	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}

	/**
	 * @return the volunteerId
	 */
	public String getVolunteerId() {
		return volunteerId;
	}

	/**
	 * @param volunteerId
	 *            the volunteerId to set
	 */
	public void setVolunteerId(String volunteerId) {
		this.volunteerId = volunteerId;
	}

	/**
	 * @return the volunteerName
	 */
	public String getVolunteerName() {
		return volunteerName;
	}

	/**
	 * @param volunteerName
	 *            the volunteerName to set
	 */
	public void setVolunteerName(String volunteerName) {
		this.volunteerName = volunteerName;
	}

	/**
	 * @return the volunteerEmail
	 */
	public String getVolunteerEmail() {
		return volunteerEmail;
	}

	/**
	 * @param volunteerEmail
	 *            the volunteerEmail to set
	 */
	public void setVolunteerEmail(String volunteerEmail) {
		this.volunteerEmail = volunteerEmail;
	}

	/**
	 * @return the volunteerPhoneno
	 */
	public String getVolunteerPhoneno() {
		return volunteerPhoneno;
	}

	/**
	 * @param volunteerPhoneno
	 *            the volunteerPhoneno to set
	 */
	public void setVolunteerPhoneno(String volunteerPhoneno) {
		this.volunteerPhoneno = volunteerPhoneno;
	}

	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * @param event
	 *            the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}
}
