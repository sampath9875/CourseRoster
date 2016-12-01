/**
 * 
 */
package com.mindtree.testingacademy.courseroster.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mindtree.testingacademy.courseroster.entities.Event;
import com.mindtree.testingacademy.courseroster.entities.EventComparator;
import com.mindtree.testingacademy.courseroster.entities.Location;
import com.mindtree.testingacademy.courseroster.entities.Registration;
import com.mindtree.testingacademy.courseroster.service.CourseRosterService;

/**
 * @author M1030496
 *
 */

@Controller
public class EventController {
	@Autowired(required = true)
	private Registration volunteer;

	@Autowired
	private CourseRosterService courseRosterService;

	/**
	 * 
	 */
	public EventController() {
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homepage() {
		return "home";
	}

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home() {
		return "home";
	}

	@RequestMapping(value = "registration.get", method = RequestMethod.GET)
	public ModelAndView register() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Location> locations = courseRosterService.getAllLocations();
		map.put("locations", locations);
		map.put("volunteer", volunteer);
		return new ModelAndView("registration", map);
	}

	@ResponseBody
	@RequestMapping(value = "getEvents", method = RequestMethod.GET, produces = "application/json")
	public String getEvents(@RequestBody String place, HttpServletRequest request, HttpServletResponse response)
			throws ParseException {
		int locationId = Integer.parseInt(request.getParameter("place"));
		Location loc = new Location();
		loc.setLocationId(locationId);
		List<Event> li = courseRosterService.getEventsForLocation(loc);
		String str = "{ ";
		for (Event event : li) {
			str += "'" + event.getEventId() + "': " + "'" + event.format(event.getEventDate()) + "-"
					+ event.getEventName() + "',";
		}
		str += " }";
		System.out.println(str);
		return str;
	}

	@RequestMapping(value = "register.action", method = RequestMethod.POST)
	public String registerVolunteer(@ModelAttribute Registration volunteer) {
		int registrationId = courseRosterService.registerForEvent(volunteer);
		if (registrationId > 0) {
			return "redirect:/success?message=You%20are%20successfully%20registered%2E%20With%20Id%20" + registrationId;
		} else {
			return "redirect:/error?message=Some%20Error%20Occurred%2E%20Please%20Try%20After%20Sometime";
		}
	}

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success(@RequestParam String message, Model model) {
		model.addAttribute("message", message);
		return "success";
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error(@RequestParam String message, Model model) {
		model.addAttribute("message", message);
		return "error";
	}

	@RequestMapping(value = "viewevents.get", method = RequestMethod.GET)
	public ModelAndView viewEvents() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Location> locations = courseRosterService.getAllLocations();
		List<Event> li = new ArrayList<>();
		for (Location location : locations) {
			li.addAll(courseRosterService.getEventsForLocation(location));
		}
		Collections.sort(li,new EventComparator());
		map.put("events", li);
		return new ModelAndView("information", map);
	}
	
	@RequestMapping(value = "aboutus.get", method = RequestMethod.GET)
	public String getAboutUs() {
		return "aboutus";
	}
}
