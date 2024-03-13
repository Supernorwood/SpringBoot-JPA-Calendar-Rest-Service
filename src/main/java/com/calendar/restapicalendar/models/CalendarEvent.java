package com.calendar.restapicalendar.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class CalendarEvent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String title;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime eventDateTime;
	private String location;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<User> attendees;
	private LocalDateTime reminderDateTime;
	private boolean reminderSent;
	
	public CalendarEvent() {}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getEventDateTime() {
		return eventDateTime;
	}

	public void setEventDateTime(LocalDateTime eventDateTime) {
		this.eventDateTime = eventDateTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<User> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<User> attendees) {
		this.attendees = attendees;
	}

	public LocalDateTime getReminderDateTime() {
		return reminderDateTime;
	}

	public void setReminderDateTime(LocalDateTime reminderDateTime) {
		this.reminderDateTime = reminderDateTime;
	}

	public boolean isReminderSent() {
		return reminderSent;
	}

	public void setReminderSent(boolean reminderSent) {
		this.reminderSent = reminderSent;
	};

}
