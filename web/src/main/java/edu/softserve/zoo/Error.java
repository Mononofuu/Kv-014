package edu.softserve.zoo;

import edu.softserve.zoo.exceptions.ApplicationException;
import org.apache.commons.lang3.StringUtils;

public class Error {

    private String message;

    public Error(ApplicationException exception) {
        this.message = StringUtils.isEmpty(exception.getMessage())? exception.getReason().getMessage() : exception.getMessage();
    }

    public String getMessage() {
        return message;
    }
}
