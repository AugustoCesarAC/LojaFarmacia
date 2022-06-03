package com.generation.farmacia.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table (name = "tb_produtos")
public class Produto
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 50)
	private String nome;
	
	@NotBlank
	@Size(max = 50)
	private String marca;
	
	@NotBlank
	@Size(max = 1000)
	private String bula;
	
	@NotNull
	private BigDecimal peso;
	
	@NotNull
	private BigDecimal valor;
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getMarca()
	{
		return marca;
	}

	public void setMarca(String marca)
	{
		this.marca = marca;
	}

	public String getBula()
	{
		return bula;
	}

	public void setBula(String bula)
	{
		this.bula = bula;
	}

	public BigDecimal getPeso()
	{
		return peso;
	}

	public void setPeso(BigDecimal peso)
	{
		this.peso = peso;
	}

	public BigDecimal getValor()
	{
		return valor;
	}

	public void setValor(BigDecimal valor)
	{
		this.valor = valor;
	}

	public Categoria getCategoria()
	{
		return categoria;
	}

	public void setCategoria(Categoria categoria)
	{
		this.categoria = categoria;
	}
	
	
}
