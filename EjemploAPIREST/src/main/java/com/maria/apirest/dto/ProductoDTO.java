package com.maria.apirest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductoDTO {
	
	private long id;
	private String nombre;
	private String image;
	private String categoriaNombre;
}
