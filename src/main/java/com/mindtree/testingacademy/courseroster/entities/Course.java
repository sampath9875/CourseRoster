/**
 * 
 */
package com.mindtree.testingacademy.courseroster.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

/**
 * @author M1030496
 *
 */
@Component
@Entity
@Table(name = "Course_Register")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Course_Id", nullable = false)
	private int courseId;

	@Column(name = "Course_Name", nullable = false)
	private String courseName;

	@Column(name = "Course_IsContinuous", nullable = false)
	private boolean isContinuous;

	@Column(name = "Event_Details")
	private String courseDetails;

	@ManyToOne(targetEntity = Location.class)
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "Location_Id", nullable = false)
	private Location location;

	@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "User_Id", nullable = false)
	private User user;

	//@Temporal(TemporalType.DATE)
	@Column(name = "Start_Date")
	private Date startDate;

	//@Temporal(TemporalType.DATE)
	@Column(name = "End_Date")
	private Date endDate;

	@DateTimeFormat(pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	private Date fromTime;

	@DateTimeFormat(pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	private Date toTime;

	@Enumerated(EnumType.STRING)
	private Recurrence recurrence;

	/**
	 * 
	 */
	public Course() {
	}

	/**
	 * @param courseId
	 * @param courseName
	 * @param isContinuous
	 * @param courseDetails
	 * @param location
	 * @param user
	 * @param startDate
	 * @param endDate
	 * @param from
	 * @param to
	 * @param recurrence
	 */
	public Course(int courseId, String courseName, boolean isContinuous, String courseDetails, Location location,
			User user, Date startDate, Date endDate, Date from, Date to, Recurrence recurrence) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.isContinuous = isContinuous;
		this.courseDetails = courseDetails;
		this.location = location;
		this.user = user;
		this.startDate = startDate;
		this.endDate = endDate;
		this.fromTime = from;
		this.toTime = to;
		this.recurrence = recurrence;
	}

	/**
	 * @return the courseId
	 */
	public int getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId
	 *            the courseId to set
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * @param courseName
	 *            the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * @return the isContinuous
	 */
	public boolean isContinuous() {
		return isContinuous;
	}

	/**
	 * @param isContinuous
	 *            the isContinuous to set
	 */
	public void setContinuous(boolean isContinuous) {
		this.isContinuous = isContinuous;
	}

	/**
	 * @return the courseDetails
	 */
	public String getCourseDetails() {
		return courseDetails;
	}

	/**
	 * @param courseDetails
	 *            the courseDetails to set
	 */
	public void setCourseDetails(String courseDetails) {
		this.courseDetails = courseDetails;
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

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the from
	 */
	public Date getFrom() {
		return fromTime;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(Date from) {
		this.fromTime = from;
	}

	/**
	 * @return the to
	 */
	public Date getTo() {
		return toTime;
	}

	/**
	 * @param to
	 *            the to to set
	 */
	public void setTo(Date to) {
		this.toTime = to;
	}

	/**
	 * @return the recurrence
	 */
	public Recurrence getRecurrence() {
		return recurrence;
	}

	/**
	 * @param recurrence
	 *            the recurrence to set
	 */
	public void setRecurrence(Recurrence recurrence) {
		this.recurrence = recurrence;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", isContinuous=" + isContinuous
				+ ", courseDetails=" + courseDetails + ", location=" + location + ", user=" + user + ", startDate="
				+ startDate + ", endDate=" + endDate + ", from=" + fromTime + ", to=" + toTime + ", recurrence="
				+ recurrence + "]";
	}

	public String format(Date date) {
		return new SimpleDateFormat("dd MMM yyyy").format(date);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseDetails == null) ? 0 : courseDetails.hashCode());
		result = prime * result + courseId;
		result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((fromTime == null) ? 0 : fromTime.hashCode());
		result = prime * result + (isContinuous ? 1231 : 1237);
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((recurrence == null) ? 0 : recurrence.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((toTime == null) ? 0 : toTime.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		Course cse=(Course) obj;
		if(cse.getCourseId() == this.courseId)
			return true;
		else
			return false;
	}

	
}
