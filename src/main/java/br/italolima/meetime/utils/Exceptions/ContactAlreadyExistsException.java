package br.italolima.meetime.utils.Exceptions;

import org.springframework.http.HttpStatus;

public class ContactAlreadyExistsException extends RuntimeException {
	private final HttpStatus httpStatusCode;
	
	public ContactAlreadyExistsException(String message, HttpStatus statusCode) {
        super(message);
        this.httpStatusCode = statusCode;
    }

    public HttpStatus getHttpStatusCode() {
        return httpStatusCode;
    }

	
}
