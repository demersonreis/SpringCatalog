package com.aplinno.dscatalog.resouces.exeptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aplinno.dscatalog.service.exceptions.EntityNotFaundException;

@ControllerAdvice
public class ResourceExeptionHandler {

	@ExceptionHandler(EntityNotFaundException.class)
	public ResponseEntity<StandardError> 
	      entityNotFound(EntityNotFaundException e, HttpServletRequest resquest) {

		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Resouce not found");
		err.setMessage(e.getMessage());
		err.setPath(resquest.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);

	}
}
