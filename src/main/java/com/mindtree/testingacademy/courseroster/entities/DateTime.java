/**
 * 
 */
package com.mindtree.testingacademy.courseroster.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

/**
 * @author M1030496
 *
 */

@Component
@Entity
@Table(name = "Date_Time")
public class DateTime {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DateTime_Id", nullable = false)
	private int dateTimeId;

	@Column(name = "Date", nullable = false)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date date;

	@Column(name = "From_Time", nullable = false)
	@DateTimeFormat(pattern = "HH:mm")
	private Date fromTime;

	@Column(name = "To_Time", nullable = false)
	@DateTimeFormat(pattern = "HH:mm")
	private Date toTime;

	/**
	 * 
	 */
	public DateTime() {
	}

	/**
	 * @param dateTimeId
	 * @param date
	 * @param fromTime
	 * @param toTime
	 */
	public DateTime(int dateTimeId, Date date, Date fromTime, Date toTime) {
		super();
		this.dateTimeId = dateTimeId;
		this.date = date;
		this.fromTime = fromTime;
		this.toTime = toTime;
	}

	/**
	 * @return the dateTimeId
	 */
	public int getDateTimeId() {
		return dateTimeId;
	}

	/**
	 * @param dateTimeId
	 *            the dayTimeId to set
	 */
	public void setDateTimeId(int dateTimeId) {
		this.dateTimeId = dateTimeId;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the fromTime
	 */
	public Date getFromTime() {
		return fromTime;
	}

	/**
	 * @param fromTime
	 *            the fromTime to set
	 */
	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}

	/**
	 * @return the toTime
	 */
	public Date getToTime() {
		return toTime;
	}

	/**
	 * @param toTime
	 *            the toTime to set
	 */
	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DateTime [dayTimeId=" + dateTimeId + ", date=" + date + ", fromTime=" + fromTime + ", toTime=" + toTime
				+ "]";
	}

}
