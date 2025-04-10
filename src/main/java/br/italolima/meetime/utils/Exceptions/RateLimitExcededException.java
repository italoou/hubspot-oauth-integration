package br.italolima.meetime.utils.Exceptions;

import org.springframework.http.HttpStatus;

public class RateLimitExcededException extends RuntimeException {
	
	private final HttpStatus httpStatusCode;
	
	public RateLimitExcededException(String message, HttpStatus statusCode) {
        super(message);
        this.httpStatusCode = statusCode;
    }

    public HttpStatus getHttpStatusCode() {
        return httpStatusCode;
    }

	
}