package com.sofiasaless.desafiobackend.useCase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sofiasaless.desafiobackend.dto.UsuarioResponseDTO;
import com.sofiasaless.desafiobackend.exception.UsuarioNaoEncontradoException;
import com.sofiasaless.desafiobackend.model.Usuario;
import com.sofiasaless.desafiobackend.model.enums.TipoUsuario;
import com.sofiasaless.desafiobackend.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VisualizarUsuarioUseCase {
    
    private final UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO visualizarUsuario(Long id) {
        var usuario = this.usuarioRepository.findById(id).orElseThrow(() -> {
            throw new UsuarioNaoEncontradoException("procurado");
        });

        return UsuarioResponseDTO.builder()
            .nome(usuario.getNome())
            .documentacao(usuario.getDocumentacao())
            .email(usuario.getEmail())
            .saldo(usuario.getSaldo())
            .tipoDoUsuario(usuario.getTipoDoUsuario())
        .build();
    }

    public List<Usuario> visualizarTodosUsuarios () {
        // posteriormente pode ser mais adequado retornar um responseDTO que nao exiba informações sensíveis como saldo, senha, id, etc...
        // por enquanto vou deixar retornando a entidade de usuario mesmo 
        
        // var listaUsuarios = this.usuarioRepository.findAll();
        // List<UsuarioResponseDTO> usuariosConvertidos = new ArrayList<>();
        // listaUsuarios.forEach((usu) -> {
        //     usuariosConvertidos.add(new UsuarioResponseDTO().builder()
        //         .nome(usu.getNome())
        //         .documentacao(usu.getDocumentacao())
        //         .email(usu.getEmail())
        //         .saldo(usu.getSaldo())
        //         .tipoDoUsuario(usu.getTipoDoUsuario())
        //     .build());
        // });
        
        // return usuariosConvertidos;

        return this.usuarioRepository.findAll();
    }

    public List<Usuario> visualizarUsuariosPorTipo (TipoUsuario tipoUsuario) {
        return this.usuarioRepository.findByTipoDoUsuario(tipoUsuario);
    }

}
