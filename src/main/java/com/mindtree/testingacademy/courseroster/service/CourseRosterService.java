/**
 * 
 */
package com.mindtree.testingacademy.courseroster.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindtree.testingacademy.courseroster.entities.Course;
import com.mindtree.testingacademy.courseroster.entities.CourseRegistration;
import com.mindtree.testingacademy.courseroster.entities.Event;
import com.mindtree.testingacademy.courseroster.entities.EventRegistration;
import com.mindtree.testingacademy.courseroster.entities.Location;
import com.mindtree.testingacademy.courseroster.entities.Recurrence;
import com.mindtree.testingacademy.courseroster.entities.User;
import com.mindtree.testingacademy.courseroster.exceptions.InvalidLoginException;
import com.mindtree.testingacademy.courseroster.hibernate.CourseRosterDao;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Period;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.RRule;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.UidGenerator;

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

	public List<EventRegistration> getRegistrationsForEvent(Event event) {
		return courseRosterDao.getRegistrationsForEvent(event);
	}

	public int registerEvent(Event event) {
		return courseRosterDao.registerEvent(event);
	}

	public int registerLocation(Location location) {
		return courseRosterDao.registerLocation(location);
	}

	public int registerForEvent(EventRegistration registration) {
		return courseRosterDao.registerForEvent(registration);
	}

	public int registerForCourse(CourseRegistration registration) {
		return courseRosterDao.registerForCourse(registration);
	}

	public Location getLocationById(int id) {
		return courseRosterDao.getLocationById(id);
	}

	public List<Event> getEventsForSearch(Event event) {
		return courseRosterDao.getEventsForSearch(event);
	}

	public int registerCourse(Course course) {
		return courseRosterDao.registerCourse(course);
	}

	public List<Course> getCoursesforSearch(Course course) {
		return courseRosterDao.getCoursesForSearch(course);
	}

	public List<Course> getCoursesForLocation(Location location) {
		return courseRosterDao.getCoursesForLocation(location);
	}

	public List<Course> getCoursesForDate(Course course) {
		return courseRosterDao.getCoursesForDate(course);
	}

	public List<CourseRegistration> getRegistrationsForCourse(Course course) {
		return courseRosterDao.getRegistrationsForCourse(course);
	}

	public List<Event> getActiveEvents() {
		return courseRosterDao.getActiveEvents();
	}

	public List<Course> getActiveCoures() {
		return courseRosterDao.getActiveCourses();
	}

	public String generateAppointment(Event event) {
		Event loadedEvent = courseRosterDao.loadEvent(event);
		DateTime start = new DateTime(loadedEvent.getEventDate().getTime() + TimeUnit.HOURS.toMillis(9));
		DateTime end = new DateTime(loadedEvent.getEventDate().getTime() + TimeUnit.HOURS.toMillis(18));
		VEvent vEvent = new VEvent(start, end, loadedEvent.getEventName());

		UidGenerator ug;

		FileOutputStream stream;
		String fileName = "";

		try {
			ug = new UidGenerator("1");
			vEvent.getProperties().add(ug.generateUid());
			Calendar calendar = new Calendar();
			calendar.getComponents().add(vEvent);
			fileName = loadedEvent.getEventName() + ".ics";
			stream = new FileOutputStream(fileName);
			calendar.getProperties().add(new ProdId("-//Course Roster//iCal4j 1.0//EN"));
			calendar.getProperties().add(Version.VERSION_2_0);
			CalendarOutputter outputter = new CalendarOutputter();
			outputter.output(calendar, stream);

			stream.close();
		} catch (IOException | ValidationException e) {
			System.out.println(e.getMessage());
		}
		return fileName;
	}

	public String generateAppointment(Course course) {
		Course loadedCourse = courseRosterDao.loadCourse(course);

		java.util.Calendar cal = java.util.Calendar.getInstance();
		long offSet = cal.getTimeZone().getOffset(loadedCourse.getFrom().getTime());

		Date start = new Date(loadedCourse.getStartDate().getTime() + loadedCourse.getFrom().getTime() + offSet);
		Date end = new Date(loadedCourse.getStartDate().getTime() + loadedCourse.getTo().getTime() + offSet);

		DateTime startDate = new DateTime(start);
		DateTime endDate = new DateTime(end);

		VEvent vEvent = new VEvent(startDate, endDate, loadedCourse.getCourseName());
		vEvent.getProperties()
				.add(new net.fortuna.ical4j.model.property.Location(loadedCourse.getLocation().getLocationDetails()));

		Period period = new Period(startDate, endDate);
		vEvent.calculateRecurrenceSet(period);

		UidGenerator ug;

		FileOutputStream stream;
		String fileName = "";

		try {
			ug = new UidGenerator("1");
			vEvent.getProperties().add(new RRule(getRecurrenceString(loadedCourse)));
			vEvent.getProperties().add(ug.generateUid());
			Calendar calendar = new Calendar();
			calendar.getComponents().add(vEvent);
			fileName = loadedCourse.getCourseName() + ".ics";
			stream = new FileOutputStream(fileName);
			calendar.getProperties().add(new ProdId("-//Course Roster//iCal4j 1.0//EN"));
			calendar.getProperties().add(Version.VERSION_2_0);
			CalendarOutputter outputter = new CalendarOutputter();
			outputter.output(calendar, stream);

			stream.close();
		} catch (IOException | ValidationException | ParseException e) {
			System.out.println(e.getMessage());
		}
		return fileName;
	}

	public String getRecurrenceString(Course course) {
		Recurrence rec = course.getRecurrence();

		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(course.getStartDate());

		int day = cal.get(java.util.Calendar.DAY_OF_WEEK);

		long difference = course.getEndDate().getTime() - course.getStartDate().getTime();

		int days = (int) difference / (1000 * 60 * 60 * 24);
		int count = (days / 7) * 5 + (days % 7) + 1;

		String recurrenceString = "";

		switch (rec) {
		case WEEKLY:
			recurrenceString += "FREQ=WEEKLY;BYDAY=" + getDayString(day) + ";COUNT=" + (count / 5 + 1);
			break;
		case DAILY:
			recurrenceString += "FREQ=DAILY;BYDAY=MO,TU,WE,TH,FR;COUNT=" + count;
			break;
		case ONCE:
			recurrenceString += "FREQ=DAILY;BYDAY=MO,TU,WE,TH,FR;";
			break;
		default:
			recurrenceString += "FREQ=DAILY;BYDAY=MO,TU,WE,TH,FR;";
			break;
		}

		return recurrenceString;
	}

	public String getDayString(int i) {
		switch (i) {
		case 2:
			return "MO";
		case 3:
			return "TU";
		case 4:
			return "WE";
		case 5:
			return "TH";
		case 6:
			return "FR";
		case 7:
			return "SA";
		case 1:
			return "SU";
		default:
			return "SU";
		}
	}
}