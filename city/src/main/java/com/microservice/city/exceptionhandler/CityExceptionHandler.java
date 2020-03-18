package com.microservice.city.exceptionhandler;

import java.util.Arrays;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Handles exceptions that are valid for the entire application.
 *
 * @author rafael.rutsatz
 *
 */
@ControllerAdvice
public class CityExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handle exceptions when no data was found in the database.
	 */
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {
		String userMessage = ex.getMessage();
		String developerMessage = ex.toString();
		List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));

		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	/**
	 * Class to organize the error messages that will be returned to users.
	 *
	 * @author rafael.rutsatz
	 *
	 */
	public static class Error {

		/**
		 * Friendly message, to be presented to the user.
		 */
		private String userMessage;

		/**
		 * Message to be presented to the developer, to help him find the problem.
		 */
		private String developerMessage;

		public Error(String userMessage, String developerMessage) {
			this.userMessage = userMessage;
			this.developerMessage = developerMessage;
		}

		public String getUserMessage() {
			return userMessage;
		}

		public String getDeveloperMessage() {
			return developerMessage;
		}
	}
}
