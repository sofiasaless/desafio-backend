package com.sofiasaless.desafiobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sofiasaless.desafiobackend.model.Lojista;
import java.util.Optional;


public interface LojistaRepository extends JpaRepository<Lojista, Long> {
    Optional<Lojista> findByCnpj(String cnpj);
    Optional<Lojista> findByEmail(String email);
}
