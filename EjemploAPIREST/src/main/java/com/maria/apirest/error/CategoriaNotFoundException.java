package com.maria.apirest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoriaNotFoundException extends RuntimeException {

	public CategoriaNotFoundException(Long id) {
		super("No se puede encontrar la categoría con el ID: " + id);
	}
}
