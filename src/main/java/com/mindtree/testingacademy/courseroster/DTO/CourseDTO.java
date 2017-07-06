/**
 * 
 */
package com.mindtree.testingacademy.courseroster.DTO;

import org.springframework.stereotype.Component;

import com.mindtree.testingacademy.courseroster.entities.Course;

/**
 * @author M1030496
 *
 */

@Component
public class CourseDTO {

	private Course course;

	private String startHour;

	private String startMin;

	private String endHour;

	private String endMin;

	/**
	 * 
	 */
	public CourseDTO() {
	}

	/**
	 * @param course
	 * @param startHour
	 * @param startMin
	 * @param endHour
	 * @param endMin
	 */
	public CourseDTO(Course course, String startHour, String startMin, String endHour, String endMin) {
		super();
		this.course = course;
		this.startHour = startHour;
		this.startMin = startMin;
		this.endHour = endHour;
		this.endMin = endMin;
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

	/**
	 * @return the startHour
	 */
	public String getStartHour() {
		return startHour;
	}

	/**
	 * @param startHour
	 *            the startHour to set
	 */
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}

	/**
	 * @return the startMin
	 */
	public String getStartMin() {
		return startMin;
	}

	/**
	 * @param startMin
	 *            the startMin to set
	 */
	public void setStartMin(String startMin) {
		this.startMin = startMin;
	}

	/**
	 * @return the endHour
	 */
	public String getEndHour() {
		return endHour;
	}

	/**
	 * @param endHour
	 *            the endHour to set
	 */
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}

	/**
	 * @return the endMin
	 */
	public String getEndMin() {
		return endMin;
	}

	/**
	 * @param endMin
	 *            the endMin to set
	 */
	public void setEndMin(String endMin) {
		this.endMin = endMin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CourseDTO [course=" + course + ", startHour=" + startHour + ", startMin=" + startMin + ", endHour="
				+ endHour + ", endMin=" + endMin + "]";
	}

}
