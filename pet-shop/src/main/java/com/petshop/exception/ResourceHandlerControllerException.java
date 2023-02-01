package com.petshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;;

@ControllerAdvice
public class ResourceHandlerControllerException {

	@ExceptionHandler(value = { ResourceNotFoundException.class })
	public ResponseEntity<Object> handleCloudResourceNotFoundExcepin(
			ResourceNotFoundException resourceNotFoundException) {
		ResourceException resourceException = new ResourceException(resourceNotFoundException.getMessage(),
				resourceNotFoundException.getCause(), HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(resourceException, HttpStatus.NOT_FOUND);
	}
	
	
}
