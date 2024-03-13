package com.calendar.restapicalendar.errors;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Controller
@ControllerAdvice
public class CustomErrorExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(InvalidRequestException.class)
	  public final ResponseEntity<ApiErrorMessage> handleInvalidRequestException(InvalidRequestException ex, WebRequest request) {
		ApiErrorMessage errors = new ApiErrorMessage(HttpStatus.BAD_REQUEST, LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	  }

}
