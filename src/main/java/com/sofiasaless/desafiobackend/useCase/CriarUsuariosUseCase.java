package com.sofiasaless.desafiobackend.useCase;

import org.springframework.stereotype.Service;

import com.sofiasaless.desafiobackend.exception.EmailOuCpfExistenteException;
import com.sofiasaless.desafiobackend.model.Lojista;
import com.sofiasaless.desafiobackend.model.Usuario;
import com.sofiasaless.desafiobackend.repository.LojistaRepository;
import com.sofiasaless.desafiobackend.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CriarUsuariosUseCase {

    private final UsuarioRepository usuarioRepository;

    private final LojistaRepository lojistaRepository;

    public Usuario criarUsuario (Usuario usuario) {
        // verificando a existência de um cpf ou email que o usuario ta passando, se nao existir ele salva, se existir um dos dois lança a exceção
        this.usuarioRepository.findByCpf(usuario.getCpf()).ifPresent(user -> {
            throw new EmailOuCpfExistenteException();
        });

        this.usuarioRepository.findByEmail(usuario.getEmail()).ifPresent(user -> {
            throw new EmailOuCpfExistenteException();
        });

        return this.usuarioRepository.save(usuario);
    }

    public Lojista criarLojista (Lojista lojista) {
        // verificando a existência de um cpf ou email que o usuario ta passando, se nao existir ele salva, se existir um dos dois lança a exceção
        this.lojistaRepository.findByCnpj(lojista.getCnpj()).ifPresent(user -> {
            throw new EmailOuCpfExistenteException();
        });

        this.lojistaRepository.findByEmail(lojista.getEmail()).ifPresent(user -> {
            throw new EmailOuCpfExistenteException();
        });

        return this.lojistaRepository.save(lojista);
    }

}
