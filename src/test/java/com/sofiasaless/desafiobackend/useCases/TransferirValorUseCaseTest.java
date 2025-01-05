package com.sofiasaless.desafiobackend.useCases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sofiasaless.desafiobackend.dto.TransferenciaRequestDTO;
import com.sofiasaless.desafiobackend.exception.SaldoInsuficienteException;
import com.sofiasaless.desafiobackend.exception.UsuarioNaoEncontradoException;
import com.sofiasaless.desafiobackend.exception.UsuarioPagadorInvalidoException;
import com.sofiasaless.desafiobackend.model.Usuario;
import com.sofiasaless.desafiobackend.model.enums.TipoUsuario;
import com.sofiasaless.desafiobackend.repository.TransferenciaRepository;
import com.sofiasaless.desafiobackend.repository.UsuarioRepository;
import com.sofiasaless.desafiobackend.useCase.TransferirValorUseCase;

@ExtendWith(MockitoExtension.class)
public class TransferirValorUseCaseTest {
 
    @InjectMocks
    private TransferirValorUseCase transferirValorUseCase;

    @Mock
    private TransferenciaRepository transferenciaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    private Usuario usuarioPagadorDeRequisicaoValido;   
    private Usuario usuarioPagadorDeRequisicaoInvalido;
    private Usuario usuarioBeneficiarioDeRequisicaoValido;

    private TransferenciaRequestDTO transferenciaDeRequisicaoValida;

    @BeforeEach
    public void setup() {
        this.usuarioPagadorDeRequisicaoValido = criarUsuarioPagadorValido();
        this.usuarioPagadorDeRequisicaoInvalido = criarUsuarioPagadorInvalido();
        this.usuarioBeneficiarioDeRequisicaoValido = criarUsuarioBeneficiarioValido();
        this.transferenciaDeRequisicaoValida = criarTransferenciaDeRequisicaoValida();
    }

    // falta o da transferencia bem sucedida

    @Test
    @DisplayName("Deve lançar uma exceção de Usuário 'pagador' ou 'beneficiario' Não Encontrado")
    public void deveLancarExcecaoDeUsuarioNaoEncontrado() {
        when(this.usuarioRepository.findById(usuarioPagadorDeRequisicaoValido.getId())).thenReturn(Optional.empty());

        try {
            this.transferirValorUseCase.transferirValor(transferenciaDeRequisicaoValida);
        } catch (Exception e) {
            assertTrue(e instanceof UsuarioNaoEncontradoException);
        }
        
    }

    @Test
    @DisplayName("Deve lançar uma exceção quando o Usuário 'pagador' for do tipo LOJISTA")
    public void deveLancarExcecaoDeUsuarioPagadorInvaldio() {
        var transferenciaComPagadorInvalido = TransferenciaRequestDTO.builder()
            .pagadorId(usuarioPagadorDeRequisicaoInvalido.getId())
            .beneficiarioId(usuarioBeneficiarioDeRequisicaoValido.getId())
            .valor(100)
        .build();

        when(this.usuarioRepository.findById(transferenciaComPagadorInvalido.getPagadorId())).thenReturn(Optional.of(usuarioPagadorDeRequisicaoInvalido));
        when(this.usuarioRepository.findById(transferenciaComPagadorInvalido.getBeneficiarioId())).thenReturn(Optional.of(usuarioBeneficiarioDeRequisicaoValido));

        try {
            this.transferirValorUseCase.transferirValor(transferenciaComPagadorInvalido);
        } catch (Exception e) {
            assertTrue(e instanceof UsuarioPagadorInvalidoException);
        }
        
    }

    @Test
    @DisplayName("Deve lançar uma exceção quando o saldo di Usuário 'pagador' for insuficiente para a transferência")
    public void deveLancarExcecaoDeSaldoInsuficiente() throws Exception {
        var usuarioComSaldoInsuficiente = Usuario.builder()
            .tipoDoUsuario(TipoUsuario.NORMAL)
            .saldo(10)
        .build();

        when(this.usuarioRepository.findById(transferenciaDeRequisicaoValida.getPagadorId())).thenReturn(Optional.of(usuarioComSaldoInsuficiente));
        when(this.usuarioRepository.findById(transferenciaDeRequisicaoValida.getBeneficiarioId())).thenReturn(Optional.of(usuarioBeneficiarioDeRequisicaoValido));

        try {
            this.transferirValorUseCase.transferirValor(transferenciaDeRequisicaoValida);
        } catch (Exception e) {
            assertTrue(e instanceof SaldoInsuficienteException);
        }
        
    }


    private Usuario criarUsuarioPagadorValido() {
        return Usuario.builder()
            .id(2l)
            .nome("CATELYN TESTER")
            .email("CATELYN@test.com")
            .documentacao("12345678900")
            .senha("test123")
            .saldo(10.0)
            .tipoDoUsuario(TipoUsuario.NORMAL)
        .build();
    }

    private Usuario criarUsuarioPagadorInvalido() {
        return Usuario.builder()
            .id(3l)
            .nome("SANSA TESTER")
            .email("SANSA@test.com")
            .documentacao("12345678900")
            .senha("test123")
            .saldo(1000.0)
            .tipoDoUsuario(TipoUsuario.LOJISTA)
        .build();
    }

    private Usuario criarUsuarioBeneficiarioValido() {
        return Usuario.builder()
            .id(1l)
            .nome("EDDARD TESTER")
            .email("EDDARD@test.com")
            .documentacao("12345678900")
            .senha("test123")
            .saldo(800.0)
            .tipoDoUsuario(TipoUsuario.NORMAL)
        .build();
    }

    private TransferenciaRequestDTO criarTransferenciaDeRequisicaoValida() {
        return TransferenciaRequestDTO.builder()
            .pagadorId(usuarioPagadorDeRequisicaoValido.getId())
            .beneficiarioId(usuarioBeneficiarioDeRequisicaoValido.getId())
            .valor(100)
        .build();
    }

}
