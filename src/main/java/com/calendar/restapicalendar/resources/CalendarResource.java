package com.calendar.restapicalendar.resources;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calendar.restapicalendar.errors.CalendarNotFoundException;
import com.calendar.restapicalendar.errors.InvalidRequestException;
import com.calendar.restapicalendar.models.Calendar;
import com.calendar.restapicalendar.models.CalendarEvent;
import com.calendar.restapicalendar.request.SearchRequest;
import com.calendar.restapicalendar.service.CalendarService;

@RestController
@RequestMapping("/v1")
public class CalendarResource {

	@Autowired
	private CalendarService calendarService;

	@GetMapping("/")
	public String sayHello() {
		return "Welcome to the Calendar REST API!";
	}

	@GetMapping("/calendars/{id}")
	public Calendar retrieveCalendar(@PathVariable long id) throws CalendarNotFoundException
	{
		Calendar calendar = calendarService.retrieveCalendar(id);
		if(calendar == null)
		{
			throw new CalendarNotFoundException("id->" + id);
		}
		else
		{
			return calendar;
		}
	}

	@GetMapping("/calendars")
	public List<Calendar> retrieveAllCalendars() {
		return calendarService.retrieveAllCalendars();
	}

	@PostMapping(value = "/calendars/add")
	public Calendar addCalendar(@RequestBody Calendar calendar) throws InvalidRequestException
	{
		if(calendar.getUser() != null)
		{
			return calendarService.addCalendar(calendar);			
		}
		else
		{
			throw new InvalidRequestException("Provide user details to create a calendar.");
		}
	}
	
	@PutMapping(value = "/calendars/update")
	public Calendar updateCalendar(@RequestBody Calendar calendar) throws InvalidRequestException
	{
		Calendar updated = calendarService.updateCalendar(calendar);
		if(updated != null)
		{
			return updated;
		}
		else
		{
			throw new CalendarNotFoundException("id->" + calendar.getId());
		}
	}
	
	@DeleteMapping(value = "/calendars/delete")
	public void deleteCalendar(@RequestBody Calendar calendar) {
		calendarService.deleteCalendar(calendar);
	}
	
	@PostMapping("/calendars/search")
	public List<CalendarEvent> searchEvents(@RequestBody SearchRequest searchRequest) throws InvalidRequestException
	{
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		if(searchRequest != null)
		{
			String startDateTime = searchRequest.getStartDateTime();
			String endDateTime = searchRequest.getEndDateTime();
			
			if(startDateTime == null && endDateTime == null)
			{
				throw new InvalidRequestException("Must provide a start and/or end date to retrieve calendar events.");
			}
			else if(startDateTime != null && endDateTime != null)
			{
				LocalDateTime start = LocalDateTime.parse(startDateTime, formatter);
				LocalDateTime end = LocalDateTime.parse(endDateTime, formatter);
				List<CalendarEvent> calendarEvents = calendarService.findByEventDateTimeBetween(start, end);
				return calendarEvents;
			}
			else
			{
				throw new InvalidRequestException("Must provide a start and/or end date to retrieve calendar events.");
			}
		}
		else
		{
			throw new InvalidRequestException("Search parameters startDateTime and endDateTime must be provided.");
		}
	}
}