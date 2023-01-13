package com.br.fsc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequeriedObjectIsNullException  extends RuntimeException{


	private static final long serialVersionUID = 1L;

	public RequeriedObjectIsNullException(String ex) {
		super(ex);
	}
	public RequeriedObjectIsNullException() {
		super("It is not allowed to persist a null object!");
		//não é permitido um objeto nulo
	}
}
