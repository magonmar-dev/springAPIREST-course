package com.maria.apirest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductoNotFoundException extends RuntimeException {

	public ProductoNotFoundException(Long id) {
		super("No se puede encontrar el producto con el ID: " + id);
	}
}
