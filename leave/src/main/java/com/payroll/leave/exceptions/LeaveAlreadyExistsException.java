package com.payroll.leave.exceptions;

import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
public class LeaveAlreadyExistsException extends RuntimeException{

    public LeaveAlreadyExistsException(String message) {
        super(message);
    }

}