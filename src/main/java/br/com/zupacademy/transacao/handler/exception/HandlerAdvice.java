package br.com.zupacademy.transacao.handler.exception;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import feign.FeignException;

@RestControllerAdvice
public class HandlerAdvice {

	private MessageSource messageSource;

	public HandlerAdvice(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handlerBadRequest(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(
				(err) -> errors.put(err.getField(), messageSource.getMessage(err, LocaleContextHolder.getLocale())));

		return errors;
	}

	@ExceptionHandler(ApiErroException.class)
	public ResponseEntity<ErroPadronizado> handleApiErroException(ApiErroException ex) {
		Collection<String> mensagens = new ArrayList<>();
		mensagens.add(ex.getMotivoExcecao());

		return ResponseEntity.status(ex.getHttpStatus()).body(new ErroPadronizado(mensagens));
	}

	@ExceptionHandler(FeignException.class)
	public ResponseEntity<?> handleFeignException(FeignException ex) {
		return ResponseEntity.status(ex.status()).body(ex.getMessage());
	}

}
