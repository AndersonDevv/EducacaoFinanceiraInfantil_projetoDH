package br.com.plataforma_financeira.plataforma_financeira.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.plataforma_financeira.plataforma_financeira.entity.Categoria;


public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}