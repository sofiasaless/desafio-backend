package com.sofiasaless.desafiobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sofiasaless.desafiobackend.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
