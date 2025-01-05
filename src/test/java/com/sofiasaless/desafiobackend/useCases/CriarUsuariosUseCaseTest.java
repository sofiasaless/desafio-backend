package com.sofiasaless.desafiobackend.useCases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.sofiasaless.desafiobackend.dto.UsuarioRequestDTO;
import com.sofiasaless.desafiobackend.exception.EmailOuCpfExistenteException;
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

    private UsuarioRequestDTO usuarioDeRequisicaoValido;
    private Usuario usuarioDeRespostaEsperada;

    @BeforeEach
    public void setup() {
        this.usuarioDeRequisicaoValido = criarUsuarioDeRequisicaoValido();
        this.usuarioDeRespostaEsperada = criarUsuarioDeRespostaEsperada();
    }

    @Test
    @DisplayName("Deve retornar um Usuário ao efetuar um cadastro bem sucessido")
    public void deveRetornarUmUsuarioAoEfetuarCadastroBemSucedido() {
        when(this.usuarioRepository.findByEmail(usuarioDeRequisicaoValido.getEmail())).thenReturn(Optional.empty());
        when(this.usuarioRepository.findByDocumentacao(usuarioDeRequisicaoValido.getDocumentacao())).thenReturn(Optional.empty());
        when(this.criarUsuariosUseCase.criarUsuario(usuarioDeRequisicaoValido)).thenReturn(usuarioDeRespostaEsperada);
        
        assertEquals(usuarioDeRespostaEsperada, this.criarUsuariosUseCase.criarUsuario(usuarioDeRequisicaoValido));        
    }

    @Test
    @DisplayName("Deve lançar uma exceção de e-mail já cadastrado por outro usuário")
    public void deveLancarExcecaoDeEmailJaCadastradoPorOutroUusario() {
        when(this.usuarioRepository.findByEmail(usuarioDeRequisicaoValido.getEmail())).thenReturn(Optional.of(new Usuario()));

        assertThrows(EmailOuCpfExistenteException.class, () -> this.criarUsuariosUseCase.criarUsuario(usuarioDeRequisicaoValido));
    }

    @Test
    @DisplayName("Deve lançar uma exceção de documento já cadastrado por outro usuário")
    public void deveLancarExcecaoDeDocumentoJaCadastradoPorOutroUusario() {
        when(this.usuarioRepository.findByDocumentacao(usuarioDeRequisicaoValido.getDocumentacao())).thenReturn(Optional.of(new Usuario()));

        assertThrows(EmailOuCpfExistenteException.class, () -> this.criarUsuariosUseCase.criarUsuario(usuarioDeRequisicaoValido));
    }

    private UsuarioRequestDTO criarUsuarioDeRequisicaoValido() {
        return UsuarioRequestDTO.builder()
            .nome("CATELYN TESTER")
            .email("CATELYN@test.com")
            .documentacao("12345678900")
            .senha("test123")
            .saldo(1000.0)
            .tipoDoUsuario(TipoUsuario.NORMAL)
        .build();
    }

    private Usuario criarUsuarioDeRespostaEsperada() {
        return Usuario.builder()
            .id(1L)
            .nome("CATELYN TESTER")
            .email("CATELYN@test.com")
            .documentacao("12345678900")
            .senha("test123")
            .saldo(1000.0)
            .tipoDoUsuario(TipoUsuario.NORMAL)
            .dataDeCriacao(LocalDateTime.now())
        .build();
    }
}
