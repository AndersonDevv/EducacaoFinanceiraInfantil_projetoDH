package br.com.plataforma_financeira.plataforma_financeira.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.plataforma_financeira.plataforma_financeira.dto.CategoriaDTO;
import br.com.plataforma_financeira.plataforma_financeira.entity.Categoria;
import br.com.plataforma_financeira.plataforma_financeira.repository.CategoriaRepository;
import br.com.plataforma_financeira.plataforma_financeira.services.exception.DataIntegratyException;
import br.com.plataforma_financeira.plataforma_financeira.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	public List<Categoria> findAll(){		
		List<Categoria> categorias = categoriaRepository.findAll();
		return categorias;//.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " + cat_codigo + ", Tipo " + Categoria.class.getName()));		
	}
	
	public Categoria find(Integer cat_codigo) {		
		Optional<Categoria> categoria = categoriaRepository.findById(cat_codigo);
		return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " + cat_codigo + ", Tipo " + Categoria.class.getName()));
		
	}
	
	public Categoria save(Categoria categoria) {
		categoria.setCat_codigo(null);//Garante que eu esteja inserindo uma nova categoria
		return categoriaRepository.save(categoria);
	}


	public Categoria update(Categoria categoria) {
		find(categoria.getCat_codigo());//Verifica se a categoria a ser atualizad existe
		return categoriaRepository.save(categoria);
	}


	public void deleteById(Integer cat_codigo) {
		find(cat_codigo);
		try {
			categoriaRepository.deleteById(cat_codigo);
			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegratyException("Não é possível excluir uma categoria que possui tarefas");
		}
		
	}
	
	public Categoria fromDTO(CategoriaDTO categoriaDTO) {		
		return new Categoria(categoriaDTO.getCat_nome());
	}
}
