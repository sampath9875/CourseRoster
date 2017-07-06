/**
 * 
 */
package com.mindtree.testingacademy.courseroster.entities;

import java.util.Comparator;

/**
 * @author M1030496
 *
 */
public class CourseComparator implements Comparator<Course> {

	/**
	 * 
	 */
	public CourseComparator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Course o1, Course o2) {
		return o1.getStartDate().compareTo(o2.getStartDate());
	}

}
