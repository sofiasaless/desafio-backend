package com.sofiasaless.desafiobackend.useCases;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sofiasaless.desafiobackend.dto.UsuarioResponseDTO;
import com.sofiasaless.desafiobackend.exception.UsuarioNaoEncontradoException;
import com.sofiasaless.desafiobackend.model.Usuario;
import com.sofiasaless.desafiobackend.model.enums.TipoUsuario;
import com.sofiasaless.desafiobackend.repository.UsuarioRepository;
import com.sofiasaless.desafiobackend.useCase.VisualizarUsuarioUseCase;

@ExtendWith(MockitoExtension.class)
public class VisualizarUsuarioUseCaseTest {

    @InjectMocks
    private VisualizarUsuarioUseCase visualizarUsuarioUseCase;

    @Mock
    private UsuarioRepository usuarioRepository;


    private UsuarioResponseDTO usuarioResponseDTO;
    private Usuario usuario;

    @BeforeEach
    public void setup() {
        this.usuarioResponseDTO = criarUsuarioResponseDTO();
        this.usuario = criarUsuario();
    }

    @Test
    @DisplayName("Deve retornar um UsuarioResponseDTO ao fazer busca por id bem sucedida")
    public void deveRetornarUmUsuarioResponseDTOAoFazerBuscaPorId() {
        when(this.usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));
        // when(this.visualizarUsuarioUseCase.visualizarUsuario(anyLong())).thenReturn(Optional.of(usuarioResponseDTO));

        assertEquals(usuarioResponseDTO, this.visualizarUsuarioUseCase.visualizarUsuario(anyLong()));
    }

    @Test
    @DisplayName("Deve retornar um UsuarioNaoEncontradoException ao fazer busca por id mal sucedida")
    public void deveRetornarUmUsuarioNaoEncontradoExceptionAoFazerBuscaPorIdMalSucedida() {
        when(this.usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());
        
        try {
            this.visualizarUsuarioUseCase.visualizarUsuario(anyLong());
        } catch (Exception e) {
            assertTrue(e instanceof UsuarioNaoEncontradoException);
        }
    }

    @Test
    @DisplayName("Deve retornar uma lista de Usuario ao fazer busca por todos")
    public void deveRetornarListaDeUsuarioAoBuscarPorTodos() {
        when(this.usuarioRepository.findAll()).thenReturn(List.of(new Usuario(), new Usuario()));
        
        assertEquals(List.of(new Usuario(), new Usuario()), this.visualizarUsuarioUseCase.visualizarTodosUsuarios());
    }

    
    // METODOS UTEIS
    private UsuarioResponseDTO criarUsuarioResponseDTO() {
        return UsuarioResponseDTO.builder()
            .nome("CATELYN TESTER")
            .email("CATELYN@test.com")
            .documentacao("12345678900")
            .saldo(1000.0)
            .tipoDoUsuario(TipoUsuario.NORMAL)        
        .build();
    }

    private Usuario criarUsuario() {
        return Usuario.builder()
            .id(1l)
            .nome("CATELYN TESTER")
            .email("CATELYN@test.com")
            .documentacao("12345678900")
            .saldo(1000.0)
            .tipoDoUsuario(TipoUsuario.NORMAL)
            .dataDeCriacao(LocalDateTime.now())        
        .build();
    }
}
