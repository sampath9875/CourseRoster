/**
 * 
 */
package com.mindtree.testingacademy.courseroster.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindtree.testingacademy.courseroster.entities.Event;
import com.mindtree.testingacademy.courseroster.entities.Location;
import com.mindtree.testingacademy.courseroster.entities.Registration;
import com.mindtree.testingacademy.courseroster.entities.User;
import com.mindtree.testingacademy.courseroster.exceptions.InvalidLoginException;
import com.mindtree.testingacademy.courseroster.hibernate.CourseRosterDao;

/**
 * @author azureUser
 *
 */
@Component
public class CourseRosterService {

	/**
	 * 
	 */
	@Autowired
	private CourseRosterDao courseRosterDao;

	public CourseRosterService() {
	}

	public User getUser(User user) throws InvalidLoginException {
		User loadedUser = courseRosterDao.getUser(user.getUserName());

		if (loadedUser == null)
			throw new InvalidLoginException("User with Username:" + user.getUserName() + " does not exist");
		else if (!loadedUser.getPassword().equals(user.getPassword()))
			throw new InvalidLoginException("Incorrect Password. Please Try again");
		else
			return loadedUser;
	}

	public boolean validUser(String userName) {
		User loadedUser = courseRosterDao.getUser(userName);

		if (loadedUser == null)
			return true;
		else
			return false;
	}

	public boolean saveUser(User user) {
		return courseRosterDao.saveUser(user);
	}

	public List<Location> getAllLocations() {
		return courseRosterDao.getAllLocations();
	}

	public List<Event> getEventsForDate(Date date) {
		return courseRosterDao.getEventsForDate(date);
	}

	public List<Event> getEventsForLocation(Location location) {
		return courseRosterDao.getEventsForLocation(location);
	}

	public List<Registration> getRegistrationsForEvent(Event event) {
		return courseRosterDao.getRegistrationsForEvent(event);
	}

	public int registerEvent(Event event) {
		return courseRosterDao.registerEvent(event);
	}
	
	public int registerLocation(Location location) {
		return courseRosterDao.registerLocation(location);
	}

	public int registerForEvent(Registration registration) {
		return courseRosterDao.registerForEvent(registration);
	}

	public Location getLocationById(int id) {
		return courseRosterDao.getLocationById(id);
	}
	
	public List<Event> getEventsForSearch(Event event)
	{
		return courseRosterDao.getEventsForSearch(event);
	}
	
}
