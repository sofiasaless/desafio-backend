package com.sofiasaless.desafiobackend.useCases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.sofiasaless.desafiobackend.dto.UsuarioRequestDTO;
import com.sofiasaless.desafiobackend.model.Usuario;
import com.sofiasaless.desafiobackend.model.enums.TipoUsuario;
import com.sofiasaless.desafiobackend.repository.UsuarioRepository;
import com.sofiasaless.desafiobackend.useCase.CriarUsuariosUseCase;

@ExtendWith(MockitoExtension.class)
public class CriarUsuariosUseCaseTest {

    @InjectMocks
    private CriarUsuariosUseCase criarUsuariosUseCase;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve ser possível criar um usuário")
    public void deve_ser_possivel_criar_um_usuario() {
        // entidades de requisição e resposta
        UsuarioRequestDTO usuarioRequestDTO = UsuarioRequestDTO.builder()
            .nome("CATELYN TESTER")
            .email("CATELYN@test.com")
            .documentacao("12345678900")
            .senha("test123")
            .saldo(1000.0)
            .tipoDoUsuario(TipoUsuario.NORMAL)
        .build();

        Usuario usuarioEsperado = Usuario.builder()
            .nome(usuarioRequestDTO.getNome())
            .email(usuarioRequestDTO.getEmail())
            .documentacao(usuarioRequestDTO.getDocumentacao())
            .senha(usuarioRequestDTO.getSenha())
            .saldo(usuarioRequestDTO.getSaldo())
            .tipoDoUsuario(usuarioRequestDTO.getTipoDoUsuario())
        .build();

        when(usuarioRepository.findByDocumentacao(usuarioRequestDTO.getDocumentacao())).thenReturn(Optional.empty());
        when(usuarioRepository.findByEmail(usuarioRequestDTO.getEmail())).thenReturn(Optional.empty());
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioEsperado);

        assertNotNull(criarUsuariosUseCase.criarUsuario(usuarioRequestDTO));
        assertEquals(usuarioRequestDTO, usuarioEsperado);
    }

}
