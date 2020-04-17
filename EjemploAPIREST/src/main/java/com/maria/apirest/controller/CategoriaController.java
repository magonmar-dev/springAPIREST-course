package com.maria.apirest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.maria.apirest.dto.CategoriaDTO;
import com.maria.apirest.dto.CategoriaDTOConverter;
import com.maria.apirest.error.CategoriaNotFoundException;
import com.maria.apirest.model.Categoria;
import com.maria.apirest.model.CategoriaRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CategoriaController {

	private final CategoriaRepository categoriaRepositorio;
	private final CategoriaDTOConverter categoriaDTOConverter;
	
	@GetMapping("/categoria")
	public ResponseEntity<?> obtenerTodas() {
		
		List<Categoria> result = categoriaRepositorio.findAll();

		if (result.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay categorías registradas");
		} else {

			List<CategoriaDTO> dtoList = result.stream().map(categoriaDTOConverter::convertToDto)
					.collect(Collectors.toList());

			return ResponseEntity.ok(dtoList);
		}
	}
	
	@GetMapping("/categoria/{id}")
	public Categoria obtenerUna(@PathVariable Long id) {

		try {
			return categoriaRepositorio.findById(id).orElseThrow(() -> new CategoriaNotFoundException(id));
		} catch(CategoriaNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
	
	@PostMapping("/categoria")
	public ResponseEntity<?> nuevaCategoria(@RequestBody CategoriaDTO nuevo) {
		
		Categoria nuevaCategoria = new Categoria();
		nuevaCategoria.setNombre(nuevo.getNombre());
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepositorio.save(nuevaCategoria));
	}
	
	@PutMapping("/categoria/{id}")
	public Categoria editarCategoria(@RequestBody Categoria editar, @PathVariable Long id) {

		return categoriaRepositorio.findById(id).map(c -> {
			c.setNombre(editar.getNombre());
			return categoriaRepositorio.save(c);
		}).orElseThrow(() -> new CategoriaNotFoundException(id));
	}
	
	@DeleteMapping("/categoria/{id}")
	public ResponseEntity<?> borrarCategoria(@PathVariable Long id) {
		
		Categoria categoria = categoriaRepositorio.findById(id)
				.orElseThrow(() -> new CategoriaNotFoundException(id));
		
		categoriaRepositorio.delete(categoria);
		return ResponseEntity.noContent().build();
	}
}
