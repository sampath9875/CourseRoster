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

import com.mindtree.testingacademy.courseroster.entities.Event;
import com.mindtree.testingacademy.courseroster.entities.Location;
import com.mindtree.testingacademy.courseroster.entities.Registration;
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
		Session session = getSession();

		Query query = session.createQuery("from Event e where e.location.locationId=?").setParameter(0,
				location.getLocationId());
		List<Event> events = query.list();

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
		criteria.add(Restrictions.eq("loc.locationId", event.getLocation().getLocationId()));

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

	public int registerForEvent(Registration registration) {
		Session session = getSession();
		session.save(registration);
		return registration.getRegistrationId();
	}

	public Location getLocationById(int id) {
		Session session = getSession();
		return session.get(Location.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Registration> getRegistrationsForEvent(Event event) {
		Session session = getSession();
		Query query = session.createQuery("from Registration r where r.event.eventId=?").setParameter(0,
				event.getEventId());
		List<Registration> registrations = query.list();

		return registrations;
	}

	public int registerLocation(Location location) {
		Session session = getSession();
		session.save(location);
		return location.getLocationId();
	}
}