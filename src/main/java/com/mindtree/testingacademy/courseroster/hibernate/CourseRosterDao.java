/**
 * 
 */
package com.mindtree.testingacademy.courseroster.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.testingacademy.courseroster.entities.Course;
import com.mindtree.testingacademy.courseroster.entities.CourseRegistration;
import com.mindtree.testingacademy.courseroster.entities.Event;
import com.mindtree.testingacademy.courseroster.entities.Location;
import com.mindtree.testingacademy.courseroster.entities.EventRegistration;
import com.mindtree.testingacademy.courseroster.entities.User;

/**
 * @author azureUser
 *
 */
@Component
@Repository
@Transactional
public class CourseRosterDao {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 
	 */
	public CourseRosterDao() {
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public boolean saveUser(User user) {
		Session session = getSession();
		session.save(user);
		return true;
	}

	public User getUser(String username) {
		User loadedUser;

		Session session = getSession();
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("userName", username));

		loadedUser = (User) criteria.getExecutableCriteria(session).uniqueResult();

		return loadedUser;
	}

	public int registerEvent(Event event) {
		Session session = getSession();
		session.save(event);
		return event.getEventId();
	}

	@SuppressWarnings("unchecked")
	public List<Location> getAllLocations() {
		Session session = getSession();
		return (List<Location>) session.createCriteria(Location.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<Event> getEventsForLocation(Location location) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Event.class);

		Date sysDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		try {
			sysDate = dateFormat.parse(dateFormat.format(sysDate));
		} catch (ParseException e) {
		}
		long sysTime = sysDate.getTime();

		criteria.add(Restrictions.ge("eventDate", new Date(sysTime)));
		criteria.add(Restrictions.eq("location.locationId", location.getLocationId()));

		List<Event> events = criteria.getExecutableCriteria(getSession()).list();

		return events;
	}

	@SuppressWarnings("unchecked")
	public List<Event> getEventsForSearch(Event event) {
		Date date = event.getEventDate();
		DetachedCriteria criteria = DetachedCriteria.forClass(Event.class);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			date = dateFormat.parse(dateFormat.format(date));
		} catch (ParseException e) {
		}
		long eventDate = date.getTime();

		Date fromDate = new Date(eventDate - TimeUnit.DAYS.toMillis(1));
		Date toDate = new Date(eventDate + TimeUnit.DAYS.toMillis(1));
		criteria.add(Restrictions.ge("eventDate", fromDate));
		criteria.add(Restrictions.lt("eventDate", toDate));
		criteria.createCriteria("location", "loc");
		criteria.add(Restrictions.eq("location.locationId", event.getLocation().getLocationId()));

		List<Event> eventsForDate = criteria.getExecutableCriteria(getSession()).list();
		return eventsForDate;
	}

	public int saveLocation(Location location) {
		Session session = getSession();
		session.save(location);

		return location.getLocationId();
	}

	public List<Event> getEventsForDate(Date date) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Event.class);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			date = dateFormat.parse(dateFormat.format(date));
		} catch (ParseException e) {
		}
		long eventDate = date.getTime();

		Date fromDate = new Date(eventDate - 1);
		Date toDate = new Date(eventDate + TimeUnit.DAYS.toMillis(1));
		criteria.add(Restrictions.ge("eventDate", fromDate));
		criteria.add(Restrictions.lt("eventDate", toDate));

		@SuppressWarnings("unchecked")
		List<Event> eventsForDate = criteria.getExecutableCriteria(getSession()).list();
		return eventsForDate;
	}

	public int registerForEvent(EventRegistration registration) {
		Session session = getSession();
		session.save(registration);
		return registration.getRegistrationId();
	}

	public int registerForCourse(CourseRegistration registration) {
		Session session = getSession();
		session.save(registration);
		return registration.getRegistrationId();
	}

	public Location getLocationById(int id) {
		Session session = getSession();
		return session.get(Location.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<EventRegistration> getRegistrationsForEvent(Event event) {
		Session session = getSession();
		Query query = session.createQuery("from EventRegistration r where r.event.eventId=?").setParameter(0,
				event.getEventId());
		List<EventRegistration> registrations = query.list();

		return registrations;
	}

	public int registerLocation(Location location) {
		Session session = getSession();
		session.save(location);
		return location.getLocationId();
	}

	public int registerCourse(Course course) {
		Session session = getSession();
		session.save(course);
		return course.getCourseId();
	}

	@SuppressWarnings("unchecked")
	public List<Course> getCoursesForSearch(Course course) {
		Date date = course.getStartDate();
		DetachedCriteria criteria = DetachedCriteria.forClass(Course.class);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			date = dateFormat.parse(dateFormat.format(date));
		} catch (ParseException e) {
		}
		long requiredDate = date.getTime();

		criteria.add(Restrictions.le("startDate", new Date(requiredDate)));
		criteria.add(Restrictions.ge("endDate", new Date(requiredDate)));
		criteria.createCriteria("location", "loc");
		criteria.add(Restrictions.eq("location.locationId", course.getLocation().getLocationId()));

		List<Course> coursesForDate = criteria.getExecutableCriteria(getSession()).list();
		return coursesForDate;
	}

	@SuppressWarnings("unchecked")
	public List<Event> getActiveEvents() {
		Session session = getSession();
		DetachedCriteria criteria = DetachedCriteria.forClass(Event.class);

		Date sysDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		try {
			sysDate = dateFormat.parse(dateFormat.format(sysDate));
		} catch (ParseException e) {
		}
		long sysTime = sysDate.getTime();

		criteria.add(Restrictions.ge("eventDate", new Date(sysTime)));

		List<Event> activeEvents = criteria.getExecutableCriteria(session).list();
		return activeEvents;
	}

	@SuppressWarnings("unchecked")
	public List<Course> getActiveCourses() {
		Session session = getSession();
		DetachedCriteria criteria = DetachedCriteria.forClass(Course.class);

		Date sysDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		try {
			sysDate = dateFormat.parse(dateFormat.format(sysDate));
		} catch (ParseException e) {
		}
		long sysTime = sysDate.getTime();

		criteria.add(Restrictions.ge("startDate", new Date(sysTime)));

		List<Course> activeCourses = criteria.getExecutableCriteria(session).list();
		return activeCourses;
	}

	@SuppressWarnings("unchecked")
	public List<Course> getCoursesForLocation(Location location) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Course.class);

		Date sysDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		try {
			sysDate = dateFormat.parse(dateFormat.format(sysDate));
		} catch (ParseException e) {
		}
		long sysTime = sysDate.getTime();

		criteria.add(Restrictions.ge("startDate", new Date(sysTime)));
		criteria.add(Restrictions.eq("location.locationId", location.getLocationId()));

		List<Course> courses = criteria.getExecutableCriteria(getSession()).list();

		return courses;
	}

	@SuppressWarnings("unchecked")
	public List<Course> getCoursesForDate(Course course) {
		Date date = course.getStartDate();
		DetachedCriteria criteria = DetachedCriteria.forClass(Course.class);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			date = dateFormat.parse(dateFormat.format(date));
		} catch (ParseException e) {
		}
		long requiredDate = date.getTime();

		/*
		 * Date fromDate = new Date(requiredDate - TimeUnit.DAYS.toMillis(1));
		 * Date toDate = new Date(requiredDate + TimeUnit.DAYS.toMillis(1));
		 */
		criteria.add(Restrictions.ge("startDate", new Date(requiredDate)));
		criteria.add(Restrictions.le("endDate", new Date(requiredDate)));

		List<Course> coursesForDate = criteria.getExecutableCriteria(getSession()).list();
		return coursesForDate;
	}

	@SuppressWarnings("unchecked")
	public List<CourseRegistration> getRegistrationsForCourse(Course course) {
		Session session = getSession();
		Query query = session.createQuery("from CourseRegistration r where r.course.courseId=?").setParameter(0,
				course.getCourseId());
		List<CourseRegistration> registrations = query.list();

		return registrations;
	}

	public Event loadEvent(Event event) {
		Event loadedEvent = new Event();
		Session session = getSession();
		session.load(loadedEvent, event.getEventId());
		return loadedEvent;
	}

	public Course loadCourse(Course course) {
		Course loadedCourse = new Course();
		Session session = getSession();
		session.load(loadedCourse, course.getCourseId());
		return loadedCourse;
	}
}