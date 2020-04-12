package com.maria.apirest.dto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.maria.apirest.model.Producto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductoDTOConverter {
	
	private final ModelMapper modelMapper;
	
	
//	@PostConstruct
//	public void init() {
//		modelMapper.addMappings(new PropertyMap<Producto, ProductoDTO>() {
//
//			@Override
//			protected void configure() {
//				map().setCategoria(source.getCategoria().getNombre());
//			}
//		});
//	}
	
	public ProductoDTO convertToDto(Producto producto) {
		return modelMapper.map(producto, ProductoDTO.class);
		
	}

}
