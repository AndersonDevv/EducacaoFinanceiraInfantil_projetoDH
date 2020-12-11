package br.com.plataforma_financeira.plataforma_financeira.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.plataforma_financeira.plataforma_financeira.entity.Cartao;
import br.com.plataforma_financeira.plataforma_financeira.repository.CartaoRepository;
import br.com.plataforma_financeira.plataforma_financeira.services.exception.ObjectNotFoundException;


@Service
public class CartaoService {
	
	@Autowired  //Instancia automaticamente pelo spring - via injeção de dependência ou inversão de controle
	private CartaoRepository cartaoRepository;	
	

	public List<Cartao> findAll() {		
		return cartaoRepository.findAll();		
	}
		
		
	public Cartao find(Integer id) {		
		Optional<Cartao> cartao = cartaoRepository.findById(id);
		return cartao.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " + id + ", Tipo " + Cartao.class.getName()));
		
	}


	public Cartao insert(Cartao cartao) {
		 return cartaoRepository.save(cartao);
	}
	
	public Cartao update(Cartao cartao) {
		find(cartao.getCar_numero());
		 return cartaoRepository.save(cartao);
	}

	public List<Cartao> findAllAtivos(char tipo) {
		return cartaoRepository.listaCartoes(tipo);		
	}
	
	


}
