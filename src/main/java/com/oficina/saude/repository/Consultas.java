package com.oficina.saude.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oficina.saude.model.Consulta;

public interface Consultas extends JpaRepository<Consulta, Long> {

}
