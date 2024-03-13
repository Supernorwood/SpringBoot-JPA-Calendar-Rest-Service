package com.calendar.restapicalendar.errors;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiErrorMessage {
	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private String message;
	private String detailedMessages;

	public ApiErrorMessage(HttpStatus status, LocalDateTime timestamp, String message, String detailedMessages) {
		super();
		this.status = status;
		this.timestamp = timestamp;
		this.message = message;
		this.detailedMessages = detailedMessages;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetailedMessages() {
		return detailedMessages;
	}

	public void setDetailedMessages(String detailedMessages) {
		this.detailedMessages = detailedMessages;
	}
	
}
