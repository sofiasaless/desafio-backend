package com.sofiasaless.desafiobackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sofiasaless.desafiobackend.model.Usuario;
import java.util.List;
import com.sofiasaless.desafiobackend.model.enums.TipoUsuario;



public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByDocumentacao(String documentacao);
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByTipoDoUsuario(TipoUsuario tipoDoUsuario);
}
