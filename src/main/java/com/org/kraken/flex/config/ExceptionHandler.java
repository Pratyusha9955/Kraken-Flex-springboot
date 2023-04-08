package com.org.kraken.flex.config;

import java.util.logging.ErrorManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.org.kraken.flex.controller.KrakenFlexController;
import com.org.kraken.flex.model.ErrorMessage;

@ControllerAdvice
public class ExceptionHandler {

	private static Logger logger = LoggerFactory.getLogger(KrakenFlexController.class);

	@org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorMessage> exception(Exception exception) {
		logger.info("there is an some issue in the System {}", exception.getStackTrace());
		ErrorMessage error = new ErrorMessage();
		error.setErrorMessage(exception.getMessage());
		error.setDetailedMessage(exception.getCause().toString());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
