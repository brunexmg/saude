package com.oficina.saude.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Farmaceutico implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull(message="CPF é obrigatório")
	private Long cpf;
	
	@NotBlank(message="Nome é obrigatório")
	private String nome;
	
	@NotNull(message="CRF é obrigatório")
	private Long crf;
	
	@NotBlank(message="Endereço é obrigatório")
	private String endereco;
	
	@NotNull(message="Número da casa é obrigatório")
	private Integer numeroCasa;
	
	@NotBlank(message="Bairro é obrigatório")
	private String bairro;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")	
	@Temporal(TemporalType.DATE)
	@NotNull(message="Data de nascimento é obrigatório")
	private Date dataNascimento;
	
	@NotNull
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "email_usuario")
	private Usuario usuario;
	
	public Long getCrf() {
		return crf;
	}
	public void setCrf(Long crf) {
		this.crf = crf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getCpf() {
		return cpf;
	}
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Integer getNumeroCasa() {
		return numeroCasa;
	}
	public void setNumeroCasa(Integer numeroCasa) {
		this.numeroCasa = numeroCasa;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	
	
}
