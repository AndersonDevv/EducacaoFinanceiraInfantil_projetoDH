package br.com.plataforma_financeira.plataforma_financeira.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.plataforma_financeira.plataforma_financeira.dto.CategoriaDTO;
import br.com.plataforma_financeira.plataforma_financeira.entity.Categoria;
import br.com.plataforma_financeira.plataforma_financeira.service.CategoriaService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1")
public class CategoriaController {	
	
	
	@Autowired
	private CategoriaService categoriaService;	
	
	
	//------------------------Busca Categorias sem DTO---------------------------------------------------------------------
	
	@GetMapping("/categoria")
	public ResponseEntity<List<Categoria>> listarCategorias() {		
		List<Categoria> categorias = categoriaService.findAll();
		return ResponseEntity.ok().body(categorias);		
	}
	
	
	
	/*
	//------------------------Busca Categorias com DTO---------------------------------------------------------------------
	@GetMapping("/categoria")
	public ResponseEntity<List<CategoriaDTO>> listarCategorias() {		
		List<Categoria> categorias = categoriaService.findAll();
		List<CategoriaDTO> categoriasDTO = categorias.stream().map(cat -> new CategoriaDTO(cat)).collect(Collectors.toList());
		return ResponseEntity.ok().body(categoriasDTO);		
	}
	
	*/
	
	
	@GetMapping("/categoria/{cat_codigo}")	
	public ResponseEntity<?> buscarCategoriaId(@PathVariable Integer cat_codigo) {	
		Categoria categoria = categoriaService.find(cat_codigo);
		return ResponseEntity.ok().body(categoria);
		
	}
	
	
	//-------------------------------------Insere uma Categoria sem DTO-------------------------------------------------------------
	/*
	@PostMapping("/categoria")
	public ResponseEntity<Categoria> inserirCategoria(@RequestBody Categoria categoria) {
		categoria = categoriaService.save(categoria);		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getCat_codigo()).toUri();		
		return ResponseEntity.created(uri).build();		
	}
	*/
	
	
	//-------------------------------------Insere uma Categoria com DTO-------------------------------------------------------------
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/categoria")
	public ResponseEntity<Categoria> inserirCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {
		Categoria cat = categoriaService.fromDTO(categoriaDTO);
		cat = categoriaService.save(cat);		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cat.getCat_codigo()).toUri();		
		return ResponseEntity.created(uri).build();		
	}
	
	/*
	@PutMapping("/categoria/{cat_codigo}")	
    public ResponseEntity<Void> atualizarCategoria(@RequestBody Categoria categoria, @PathVariable Integer cat_codigo) {				
		categoria.setCat_codigo(cat_codigo);
		categoria = categoriaService.update(categoria);				
		return ResponseEntity.noContent().build();
		
	}
	*/
	
	//-------------------------------------Atualiza uma Categoria com DTO-------------------------------------------------------------
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/categoria/{cat_codigo}")	
    public ResponseEntity<Void> atualizarCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable Integer cat_codigo) {				
		Categoria cat = categoriaService.fromDTO(categoriaDTO);
		cat.setCat_codigo(cat_codigo);
		cat = categoriaService.update(cat);				
		return ResponseEntity.noContent().build();
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/categoria/{cat_codigo}")	
	public ResponseEntity<Void> deletarCategoria(@PathVariable Integer cat_codigo) {	
		categoriaService.deleteById(cat_codigo);
		return ResponseEntity.noContent().build();//Retorna uma resposta sem conteúdo
		
	}
	
	

}