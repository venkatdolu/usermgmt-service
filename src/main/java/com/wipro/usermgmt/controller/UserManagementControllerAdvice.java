package com.wipro.usermgmt.controller;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.HandlerMapping;

import com.wipro.usermgmt.exceptions.DataNotFoundException;
import com.wipro.usermgmt.exceptions.NoResultsFoundException;
import com.wipro.usermgmt.exceptions.ResourceNotFoundException;
import com.wipro.usermgmt.exceptions.TimeOutException;
import com.wipro.usermgmt.exceptions.UserMgmtException;
import com.wipro.usermgmt.exceptions.ValidationException;
import com.wipro.usermgmt.vo.ErrorResponse;

@ControllerAdvice
public class UserManagementControllerAdvice {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementController.class);

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorResponse handleResourceNotFound(final ResourceNotFoundException exception,
			final HttpServletRequest request) {
		return error(exception, HttpStatus.NOT_FOUND.value(), request);
	}
	
	@ExceptionHandler(TimeOutException.class)
	@ResponseStatus(value = HttpStatus.REQUEST_TIMEOUT)
	public @ResponseBody ErrorResponse handleTimeOutException(final TimeOutException exception,
			final HttpServletRequest request) {
		return error(exception, HttpStatus.REQUEST_TIMEOUT.value(), request);
	}
	
	
	@ExceptionHandler(NoResultsFoundException.class)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public @ResponseBody ErrorResponse handleNoResultsFoundException(final NoResultsFoundException exception,
			final HttpServletRequest request) {
		return error(exception, HttpStatus.NO_CONTENT.value(), request);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponse handlerRequiredParameterException(
			final MissingServletRequestParameterException exception, final HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse();
		error.setMessage(exception.getMessage());
		error.setException(exception.getClass().toString());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setPath((String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE));
		LOGGER.error("Getting error because of:{}", exception);
		return error;
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponse methodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException exception,
			final HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse();
		error.setMessage("Invalid date format. The date format should be : dd-MM-yyyy HH:mm:ss");
		error.setException(exception.getClass().toString());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setPath((String) request.getAttribute(
		        HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE));
		LOGGER.error("Getting error because of:{}", exception);
		return error;
	}
	
	@ExceptionHandler(UserMgmtException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponse handleUserMgmtException(final UserMgmtException exception,
			final HttpServletRequest request) {
		return error(exception, HttpStatus.BAD_REQUEST.value(), request);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorResponse handleException(final Exception exception, final HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse();
		error.setMessage(exception.getMessage());
		error.setException(exception.getClass().toString());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setPath((String) request.getAttribute(
		        HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE));
		LOGGER.error("Getting error because of:", exception);
		return error;

	}

	@ExceptionHandler(DataNotFoundException.class)
	public @ResponseBody ErrorResponse handleDataNotFound(final DataNotFoundException exception,
			final HttpServletRequest request) {
		return error(exception, HttpStatus.NO_CONTENT.value(), request);
	}

	private ErrorResponse error(RuntimeException exception, Integer statusCode,final HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse();
		error.setMessage(exception.getMessage());
		error.setException(exception.getClass().toString());
		error.setStatus(statusCode);
		error.setPath((String) request.getAttribute(
		        HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE));
		LOGGER.error("Getting error because of:{}", exception);
		return error;
	}

	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponse handleValidationException(final ValidationException exception,
			final HttpServletRequest request) {
		return error(exception, HttpStatus.BAD_REQUEST.value(), request);
	}

}