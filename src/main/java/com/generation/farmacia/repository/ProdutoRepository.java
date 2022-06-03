package com.generation.farmacia.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.farmacia.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>
{

	public List <Produto> findByValorBetween (BigDecimal valor1,BigDecimal  valor2);
	
	public List <Produto> findByNomeAndMarcaContainingIgnoreCase (@Param("nome") String nome, @Param("marca")  String marca);
	
	public List <Produto> findByNomeOrMarcaContainingIgnoreCase (@Param("nome") String nome, @Param("marca") String marca);
}
