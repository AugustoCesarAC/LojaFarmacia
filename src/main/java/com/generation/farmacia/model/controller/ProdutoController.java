package com.generation.farmacia.model.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.farmacia.model.Produto;
import com.generation.farmacia.repository.CategoriaRepository;
import com.generation.farmacia.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController
{

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll()
	{
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id)
	{
		return produtoRepository.findById(id)
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				.orElse(ResponseEntity.badRequest().build());
	}
	
	@GetMapping("/between/{valor1}/{valor2}")
	public ResponseEntity <List<Produto>> getByBetween(@PathVariable BigDecimal valor1, @PathVariable BigDecimal valor2)
	{
		return ResponseEntity.ok(produtoRepository.findByValorBetween(valor1,valor2));
	}
	
	@GetMapping("/and/{nome}/{marca}")
	public ResponseEntity <List<Produto>> getByAnd(@PathVariable String nome, @PathVariable String marca)
	{
		return ResponseEntity.ok(produtoRepository.findByNomeAndMarcaContainingIgnoreCase(nome,marca));
	}
	
	@GetMapping("/or/{nome}/{marca}")
	public ResponseEntity <List<Produto>> getByOr(@PathVariable String nome, @PathVariable String marca)
	{
		return ResponseEntity.ok(produtoRepository.findByNomeOrMarcaContainingIgnoreCase(nome,marca));
	}
	
	@PostMapping
	public ResponseEntity<Produto> postCate(@Valid @RequestBody Produto produto)
	{
		if(categoriaRepository.existsById(produto.getCategoria().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
		
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping
	public ResponseEntity<Produto> putCate(@Valid @RequestBody Produto produto)
	{
		if(!categoriaRepository.existsById(produto.getCategoria().getId()) || produto.getId() == null || produto.getCategoria().getId() == null)
			return ResponseEntity.badRequest().build();
		
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Produto> delCate(@PathVariable Long id)
	{
		if(produtoRepository.existsById(id))
		{
			produtoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	
}
