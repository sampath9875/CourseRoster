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
 * @author M1030496
 *
 */
@Component
@Entity
@Table(name = "Volunteer_Course_Register")
public class CourseRegistration {

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

	@ManyToOne(targetEntity = Course.class)
	@JoinColumn(name = "Course_Id", nullable = false)
	private Course course;

	/**
	 * 
	 */
	public CourseRegistration() {
	}

	/**
	 * @param registrationId
	 * @param volunteerId
	 * @param volunteerName
	 * @param volunteerEmail
	 * @param volunteerPhoneno
	 * @param course
	 */
	public CourseRegistration(int registrationId, String volunteerId, String volunteerName, String volunteerEmail,
			String volunteerPhoneno, Course course) {
		super();
		this.registrationId = registrationId;
		this.volunteerId = volunteerId;
		this.volunteerName = volunteerName;
		this.volunteerEmail = volunteerEmail;
		this.volunteerPhoneno = volunteerPhoneno;
		this.course = course;
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
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * @param course
	 *            the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CourseRegistration [registrationId=" + registrationId + ", volunteerId=" + volunteerId
				+ ", volunteerName=" + volunteerName + ", volunteerEmail=" + volunteerEmail + ", volunteerPhoneno="
				+ volunteerPhoneno + ", course=" + course + "]";
	}

}
