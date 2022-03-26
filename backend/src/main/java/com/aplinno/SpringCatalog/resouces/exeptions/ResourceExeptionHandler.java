package com.aplinno.SpringCatalog.resouces.exeptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aplinno.SpringCatalog.service.exceptions.DataBaseException;
import com.aplinno.SpringCatalog.service.exceptions.ResouceNotFaundException;

@ControllerAdvice
public class ResourceExeptionHandler {

	HttpStatus statusBad = HttpStatus.BAD_REQUEST;
	HttpStatus statusNot = HttpStatus.NOT_FOUND;

	@ExceptionHandler(ResouceNotFaundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResouceNotFaundException e, HttpServletRequest resquest) {

		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(statusNot.value());
		err.setError("Resouce not found");
		err.setMessage(e.getMessage());
		err.setPath(resquest.getRequestURI());
		return ResponseEntity.status(statusNot).body(err);

	}

	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> dataBase(DataBaseException e, HttpServletRequest resquest) {

		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(statusBad.value());
		err.setError("Database exception");
		err.setMessage(e.getMessage());
		err.setPath(resquest.getRequestURI());
		return ResponseEntity.status(statusBad).body(err);

	}
}
