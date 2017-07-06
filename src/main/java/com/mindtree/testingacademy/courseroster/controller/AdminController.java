/**
 * 
 */
package com.mindtree.testingacademy.courseroster.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mindtree.testingacademy.courseroster.DTO.CourseDTO;
import com.mindtree.testingacademy.courseroster.entities.Course;
import com.mindtree.testingacademy.courseroster.entities.CourseComparator;
import com.mindtree.testingacademy.courseroster.entities.Event;
import com.mindtree.testingacademy.courseroster.entities.EventComparator;
import com.mindtree.testingacademy.courseroster.entities.Location;
import com.mindtree.testingacademy.courseroster.entities.Recurrence;
import com.mindtree.testingacademy.courseroster.entities.User;
import com.mindtree.testingacademy.courseroster.exceptions.InvalidLoginException;
import com.mindtree.testingacademy.courseroster.service.CourseRosterService;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * @author azureUser
 *
 */

@Controller
public class AdminController {

	@Autowired(required = true)
	private User user;

	@Autowired(required = true)
	private Event event;

	@Autowired
	private CourseRosterService courseRosterService;

	@Autowired(required = true)
	private CourseDTO courseDTO;

	@Autowired
	private Course course;

	/**
	 * 
	 */
	public AdminController() {
	}

	@RequestMapping(value = "adminlogin", method = RequestMethod.GET)
	public ModelAndView goToAdminLogin() {
		Map<String, Object> adminLogin = new HashMap<>();
		adminLogin.put("user", user);
		return new ModelAndView("adminlogin", adminLogin);
	}

	@RequestMapping(value = "adminsignup", method = RequestMethod.GET)
	public String adminSignUp(Model model) {
		model.addAttribute("user", user);
		return "adminsignup";
	}

	@RequestMapping(value = "location.get", method = RequestMethod.GET)
	public String getLocation(Model model, HttpServletRequest request) {
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (sessionUser == null) {
			return loginMandate(model);
		} else {
			model.addAttribute("location", new Location());
			return "location";
		}
	}

	@RequestMapping(value = "location.action", method = RequestMethod.POST)
	public String registerEvent(@ModelAttribute Location location, BindingResult result, HttpServletRequest request,
			Model model) {
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (sessionUser == null) {
			return loginMandate(model);
		} else if (result.hasErrors()) {
			return "redirect:/error?message=The%20user%20object%20passed%20has%20some%20errors2E%20Please%20try%20again";
		} else {
			int regId = courseRosterService.registerLocation(location);
			if (regId > 0)
				model.addAttribute("locId", regId);
			else
				model.addAttribute("message", "Failed to register Event. Please try again");
			return "adminhome";
		}
	}

	@ResponseBody
	@RequestMapping(value = "validateUserName", method = RequestMethod.GET, produces = "application/text")
	public String validateUserName(@RequestParam String userName) {
		boolean isValid = courseRosterService.validUser(userName);
		if (isValid)
			return "valid";
		else
			return "inValid";
	}

	@RequestMapping(value = "adminSignUp.action", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute User user, BindingResult bindingResult, HttpServletRequest request) {
		String resultPage = "redirect:/success?message=";
		if (bindingResult.hasErrors()) {
			resultPage = "redirect:/error?message=The%20user%20object%20passed%20has%20some%20errors2E%20Please%20try%20again";
		} else {
			if (!courseRosterService.saveUser(user)) {
				resultPage = "redirect:/error?message=The%20user%20creation%20failed2E%20Please%20try%20again";
			} else {
				request.getSession().setAttribute("user", user);
				resultPage = "adminhome";
				// resultPage +=
				// "User%20Saved%20Successfully%2E%20Please%20login%20with%20your%20credentials%20to%20continue";
			}
		}
		return resultPage;
	}

	@RequestMapping(value = "adminLogin.action", method = RequestMethod.GET)
	public String loginRedirect(Model model, HttpServletRequest request) {
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (sessionUser == null) {
			return loginMandate(model);
		} else
			return "adminhome";
	}

	@RequestMapping(value = "adminLogin.action", method = RequestMethod.POST)
	public String loginUser(@ModelAttribute User user, BindingResult bindingResult, Model model,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return "redirect:/error?message=The%20user%20object%20passed%20has%20some%20errors2E%20Please%20try%20again";
		} else {
			try {
				User sessionUser = courseRosterService.getUser(user);
				request.getSession().setAttribute("user", sessionUser);
			} catch (InvalidLoginException e) {
				model.addAttribute("message", e.getMessage());
				return "adminlogin";
			}
		}
		return "adminhome";
	}

	@RequestMapping(value = "registerevents.get", method = RequestMethod.GET)
	public String registerEvents(Model model, HttpServletRequest request) {
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (sessionUser == null) {
			return loginMandate(model);
		} else {
			model.addAttribute("locations", courseRosterService.getAllLocations());
			model.addAttribute("event", event);
			return "registerevents";
		}
	}

	@RequestMapping(value = "adminviewevents.get", method = RequestMethod.GET)
	public String viewEvents(Model model, HttpServletRequest request) {
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (sessionUser == null) {
			return loginMandate(model);
		} else {
			model.addAttribute("locations", courseRosterService.getAllLocations());
			model.addAttribute("event", event);
			model.addAttribute("course", course);
			return "adminviewevents";
		}
	}

	@RequestMapping(value = "registerEvent.action", method = RequestMethod.GET)
	public String registerEventGet(Model model, HttpServletRequest request) {
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (sessionUser == null) {
			return loginMandate(model);
		} else {
			model.addAttribute("locations", courseRosterService.getAllLocations());
			model.addAttribute("event", event);
			return "registerevents";
		}
	}

	@RequestMapping(value = "registerEvent.action", method = RequestMethod.POST)
	public String registerEvent(@ModelAttribute Event event, BindingResult result, HttpServletRequest request,
			Model model) {
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (sessionUser == null) {
			return loginMandate(model);
		} else if (result.hasErrors()) {
			return "redirect:/error?message=The%20user%20object%20passed%20has%20some%20errors2E%20Please%20try%20again";
		} else {
			event.setLocation(courseRosterService.getLocationById(event.getLocation().getLocationId()));
			event.setUser(sessionUser);
			int eventId = courseRosterService.registerEvent(event);
			if (eventId > 0)
				model.addAttribute("eventId", eventId);
			else
				model.addAttribute("message", "Failed to register Event. Please try again");
			return "adminhome";
		}
	}

	@RequestMapping(value = "getEvents.action", method = RequestMethod.POST)
	public String viewEventsPage(@ModelAttribute Event event, Model model, HttpServletRequest request) {
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (sessionUser == null) {
			return loginMandate(model);
		} else {
			List<Event> eventsSearch = null;
			List<Event> eventsLocation = null;
			List<Event> eventsDate = null;
			if (event == null)
				return "adminhome";
			else {
				Location location = event.getLocation();
				if (location.getLocationId() != 0 && event.getEventDate() != null) {
					eventsSearch = courseRosterService.getEventsForSearch(event);
					eventsDate = courseRosterService.getEventsForDate(event.getEventDate());
					eventsLocation = courseRosterService.getEventsForLocation(location);
				}
				for (Event event2 : eventsSearch) {
					if (eventsLocation.contains(event2)) {
						eventsLocation.remove(event2);
					}
					if (eventsDate.contains(event2)) {
						eventsDate.remove(event2);
					}
				}
				
				Collections.sort(eventsDate, new EventComparator());
				Collections.sort(eventsLocation, new EventComparator());
				Collections.sort(eventsSearch, new EventComparator());
				
				model.addAttribute("eventsSearch", eventsSearch);
				model.addAttribute("eventsLocation", eventsLocation);
				model.addAttribute("eventsDate", eventsDate);
			}
			return "viewevents";
		}
	}

	@RequestMapping(value = "adminHome.get", method = RequestMethod.GET)
	public String adminHome(Model model, HttpServletRequest request) {
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (sessionUser == null) {
			return loginMandate(model);
		} else
			return "adminhome";
	}

	@RequestMapping(value = "viewVolunteers", method = RequestMethod.GET)
	public String viewRegistrations(Model model, HttpServletRequest request) {
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (sessionUser == null) {
			return loginMandate(model);
		} else {
			String eventId = request.getParameter("eventId");
			String courseId = request.getParameter("courseId");
			if (eventId != null) {
				Event selectedEvent = new Event();
				selectedEvent.setEventId(Integer.parseInt(eventId));
				model.addAttribute("registrations", courseRosterService.getRegistrationsForEvent(selectedEvent));
				return "viewvolunteersforevent";
			} else if (courseId != null) {
				Course selectedCourse = new Course();
				selectedCourse.setCourseId(Integer.parseInt(courseId));
				model.addAttribute("registrations", courseRosterService.getRegistrationsForCourse(selectedCourse));
				return "viewvolunteersforcourse";
			} else
				return "redirect:/error?message=There%20was%20an%20error2E%20Please%20try%20again";
		}
	}

	@RequestMapping(value = "logout.action", method = RequestMethod.GET)
	public String logoutInvalidateSession(Model model, HttpServletRequest request) {
		request.getSession().invalidate();
		return "home";
	}

	public String loginMandate(Model model) {
		model.addAttribute("message", "Please login to access admin module");
		model.addAttribute("user", user);
		return "adminlogin";
	}

	@RequestMapping(value = "registercourse.get", method = RequestMethod.GET)
	public String registerCourseGet(Model model, HttpServletRequest request) {
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (sessionUser == null) {
			return loginMandate(model);
		} else {
			model.addAttribute("locations", courseRosterService.getAllLocations());
			model.addAttribute("recurrenceTypes", Recurrence.values());
			model.addAttribute("courseDTO", courseDTO);
			model.addAttribute("hours", getHours());
			model.addAttribute("minutes", getMinutes());
			return "registercourses";
		}
	}

	@RequestMapping(value = "registerCourse.action", method = RequestMethod.POST)
	public String registerCourse(@ModelAttribute CourseDTO courseDTO, BindingResult result, HttpServletRequest request,
			Model model) {
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (sessionUser == null) {
			return loginMandate(model);
		} else if (result.hasErrors())
			return "redirect:/error?message=The%20course%20object%20passed%20has%20some%20errors%2E%20Please%20try%20again";
		else {
			Course course = courseDTO.getCourse();
			boolean check = (course.getStartDate().getTime() > course.getEndDate().getTime());
			if (check) {
				course.setLocation(courseRosterService.getLocationById(course.getLocation().getLocationId()));
				course.setUser(sessionUser);
				course.setFrom(startTime(courseDTO));
				course.setTo(endTime(courseDTO));
				int courseId = courseRosterService.registerCourse(course);
				if (courseId > 0) {
					model.addAttribute("entity", "Course");
					model.addAttribute("entityId", courseId);
				} else
					model.addAttribute("message", "Failed to register Course. Please try again");
			} else
				model.addAttribute("message", "Server Validation failed for dates. Please try again");
			return "adminhome";
		}
	}

	public List<Integer> getHours() {
		List<Integer> hours = new ArrayList<>();
		int i = 0;
		for (i = 0; i < 24; i++)
			hours.add(i);
		return hours;
	}

	public List<Integer> getMinutes() {
		List<Integer> hours = new ArrayList<>();
		int i = 0;
		for (i = 0; i < 60; i = i + 15)
			hours.add(i);
		return hours;
	}

	public Date startTime(CourseDTO timeDTO) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeDTO.getStartHour()));
		cal.set(Calendar.MINUTE, Integer.parseInt(timeDTO.getStartMin()));
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	public Date endTime(CourseDTO timeDTO) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeDTO.getEndHour()));
		cal.set(Calendar.MINUTE, Integer.parseInt(timeDTO.getEndMin()));
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	@RequestMapping(value = "getCourses.action", method = RequestMethod.POST)
	public String viewCoursesPage(@ModelAttribute Course course, Model model, HttpServletRequest request) {
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (sessionUser == null) {
			return loginMandate(model);
		} else {
			List<Course> coursesSearch = null;
			List<Course> coursesLocation = null;
			List<Course> coursesDate = null;
			if (course == null)
				return "adminhome";
			else {
				Location location = course.getLocation();
				if (location.getLocationId() != 0 && course.getStartDate() != null) {
					coursesSearch = courseRosterService.getCoursesforSearch(course);
					coursesDate = courseRosterService.getCoursesForDate(course);
					coursesLocation = courseRosterService.getCoursesForLocation(course.getLocation());
				}
				for (Course course2 : coursesSearch) {
					if (coursesLocation.contains(course2)) {
						coursesLocation.remove(course2);
					}
					if (coursesDate.contains(course2)) {
						coursesDate.remove(course2);
					}
				}
				
				Collections.sort(coursesDate, new CourseComparator());
				Collections.sort(coursesLocation, new CourseComparator());
				Collections.sort(coursesSearch, new CourseComparator());
				
				model.addAttribute("coursesSearch", coursesSearch);
				model.addAttribute("coursesLocation", coursesLocation);
				model.addAttribute("coursesDate", coursesDate);
			}
			return "viewcourses";
		}
	}

	@RequestMapping(value = "getCalendar", method = RequestMethod.GET)
	public void getCalendarEvent(@RequestParam String entity, @RequestParam String id, HttpServletResponse response) {
		if (entity != null) {
			if (id != null) {
				if (entity.equalsIgnoreCase("Event")) {
					Event eventToBeGenerated = new Event();
					eventToBeGenerated.setEventId(Integer.parseInt(id));
					String file;
					try {
						file = courseRosterService.generateAppointment(eventToBeGenerated);
						response.setContentType("application/octet-stream");
						InputStream stream = new BufferedInputStream(new FileInputStream(new File(file)));
						IOUtils.copy(stream, response.getOutputStream());
						response.setHeader("Content-Disposition", "attachment; filename=" + file);
						response.flushBuffer();
					} catch (IOException e) {
					}
				} else if (entity.equalsIgnoreCase("Course")) {
					Course courseToBeGenerated = new Course();
					courseToBeGenerated.setCourseId(Integer.parseInt(id));
					String file;
					try {
						file = courseRosterService.generateAppointment(courseToBeGenerated);
						response.setContentType("application/octet-stream");
						InputStream stream = new BufferedInputStream(new FileInputStream(new File(file)));
						IOUtils.copy(stream, response.getOutputStream());
						response.setHeader("Content-Disposition", "attachment; filename=" + file);
						response.flushBuffer();
					} catch (IOException e) {
					}

				}
			}
		}
	}

}
