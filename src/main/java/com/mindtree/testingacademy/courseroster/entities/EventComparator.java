/**
 * 
 */
package com.mindtree.testingacademy.courseroster.entities;

import java.util.Comparator;

/**
 * @author azureUser2
 *
 */
public class EventComparator implements Comparator<Event>{

	@Override
	public int compare(Event o1, Event o2) {
		 return o1.getEventDate().compareTo(o2.getEventDate());
	}
}
