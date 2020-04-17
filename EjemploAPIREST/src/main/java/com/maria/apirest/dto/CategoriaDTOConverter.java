package com.maria.apirest.dto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.maria.apirest.model.Categoria;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CategoriaDTOConverter {

	private final ModelMapper modelMapper;
	
	public CategoriaDTO convertToDto(Categoria categoria) {
		return modelMapper.map(categoria, CategoriaDTO.class);
	}
}
