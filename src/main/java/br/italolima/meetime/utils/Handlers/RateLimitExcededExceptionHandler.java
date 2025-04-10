package br.italolima.meetime.utils.Handlers;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.italolima.meetime.utils.Exceptions.RateLimitExcededException;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class RateLimitExcededExceptionHandler {

	@ExceptionHandler(RateLimitExcededException.class)
	public ResponseEntity<String> ExchangingTokenException(RateLimitExcededException exception, HttpServletResponse response) throws IOException {
        return ResponseEntity.status(exception.getHttpStatusCode()).body(exception.getMessage());
	}
}
