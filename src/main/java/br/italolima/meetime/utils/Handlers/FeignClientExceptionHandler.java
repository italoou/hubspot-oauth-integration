package br.italolima.meetime.utils.Handlers;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import feign.FeignException;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class FeignClientExceptionHandler {

	@ExceptionHandler(FeignException.class)
	public ResponseEntity<String> ExchangingTokenException(FeignException exception, HttpServletResponse response) throws IOException {
        return ResponseEntity.status(exception.status()).body(exception.getMessage());
	}
}
