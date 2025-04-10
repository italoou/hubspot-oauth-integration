package br.italolima.meetime.utils.Handlers;

import java.io.IOException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.italolima.meetime.utils.Exceptions.ExchangingTokenException;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExchangingTokenExceptionHandler {

	@ExceptionHandler(ExchangingTokenException.class)
	public void ExchangingTokenException(ExchangingTokenException exception, HttpServletResponse response) throws IOException {
		response.sendRedirect("/hubspot/auth/error?msg="+exception.getMessage());
	}
}
