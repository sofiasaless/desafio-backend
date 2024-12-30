package com.sofiasaless.desafiobackend.useCase;

import org.springframework.stereotype.Service;

import com.sofiasaless.desafiobackend.exception.EmailOuCpfExistenteException;
import com.sofiasaless.desafiobackend.model.Usuario;
import com.sofiasaless.desafiobackend.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CriarUsuariosUseCase {

    private final UsuarioRepository usuarioRepository;

    public Usuario criarUsuario (Usuario usuario) {
        // verificando a existência de um cpf ou email que o usuario ta passando, se nao existir ele salva, se existir um dos dois lança a exceção
        this.usuarioRepository.findByDocumentacao(usuario.getDocumentacao()).ifPresent(user -> {
            throw new EmailOuCpfExistenteException();
        });

        this.usuarioRepository.findByEmail(usuario.getEmail()).ifPresent(user -> {
            throw new EmailOuCpfExistenteException();
        });

        return this.usuarioRepository.save(usuario);
    }

}
