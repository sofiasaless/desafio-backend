package com.sofiasaless.desafiobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sofiasaless.desafiobackend.model.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    
}