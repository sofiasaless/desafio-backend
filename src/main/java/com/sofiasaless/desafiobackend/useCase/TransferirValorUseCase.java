package com.sofiasaless.desafiobackend.useCase;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sofiasaless.desafiobackend.dto.TransferenciaRequestDTO;
import com.sofiasaless.desafiobackend.dto.TransferenciaResponseDTO;
import com.sofiasaless.desafiobackend.dto.UsuarioResponseDTO;
import com.sofiasaless.desafiobackend.exception.SaldoInsuficienteException;
import com.sofiasaless.desafiobackend.exception.TransferenciaNaoAutorizadaException;
import com.sofiasaless.desafiobackend.exception.UsuarioNaoEncontradoException;
import com.sofiasaless.desafiobackend.exception.UsuarioPagadorInvalidoException;
import com.sofiasaless.desafiobackend.model.Transferencia;
import com.sofiasaless.desafiobackend.model.Usuario;
import com.sofiasaless.desafiobackend.repository.TransferenciaRepository;
import com.sofiasaless.desafiobackend.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferirValorUseCase {
    
    private final UsuarioRepository usuarioRepository;

    private final TransferenciaRepository transferenciaRepository;

    private final AtualizarSaldoUseCase atualizarSaldoUseCase;

    @Value("${authentication.transf.url}")
    private String urlAuth;

    private final RestTemplate restTemplate;

    @Transactional(rollbackOn = Exception.class) // caso aconteça de lançar alguma execeção de autorização do pagamento, o transactional é reponsável por administrar isso e fazer o rowback das informações que já tinham sido salvas
    // se a transação passou por alguma interferência, o valor do dinheiro da transação volta para a conta do pagador
    public TransferenciaResponseDTO transferirValor(TransferenciaRequestDTO transferenciaDTO) throws Exception {
        // validar se os usuarios passados existem e são válidos (ex: usuario tentando transferir para ele mesmo, usuario lojista tentando fazer transferência)
        var pagador = this.usuarioRepository.findById(transferenciaDTO.getPagadorId()).orElseThrow(() -> {
            throw new UsuarioNaoEncontradoException("pagador");
        });

        var beneficiario = this.usuarioRepository.findById(transferenciaDTO.getBeneficiarioId()).orElseThrow(() -> {
            throw new UsuarioNaoEncontradoException("beneficiário");
        });
        
        // validando o usuário pagador e seu saldo
        validarUsuarioPagador(pagador, transferenciaDTO.getValor());
        
        // atualizando os saldos dos usuários da transferência
        this.atualizarSaldoUseCase.atualizarSaldo(pagador.getId(), pagador.getSaldo() - transferenciaDTO.getValor());
        this.atualizarSaldoUseCase.atualizarSaldo(beneficiario.getId(), beneficiario.getSaldo() + transferenciaDTO.getValor());
        
        var transferencia = Transferencia.builder()
            .beneficiario(beneficiario)
            .pagador(pagador)
            .beneficiarioId(transferenciaDTO.getBeneficiarioId())
            .pagadorId(transferenciaDTO.getPagadorId())
            .valor(transferenciaDTO.getValor())
        .build();

        autenticarTransacao();

        this.transferenciaRepository.save(transferencia);

        return TransferenciaResponseDTO.builder()
            .id(transferencia.getId())
            .valor(transferencia.getValor())
            .pagador(new UsuarioResponseDTO(transferencia.getPagador().getId(), transferencia.getPagador().getNome()))
            .beneficiario(new UsuarioResponseDTO(transferencia.getBeneficiario().getId(), transferencia.getBeneficiario().getNome()))
        .build();

    }

    private boolean validarUsuarioPagador (Usuario usuarioPagador, double valorTransferencia) throws Exception {
        if (usuarioPagador.getTipoDoUsuario().toString().equalsIgnoreCase("NORMAL")) {
            if (usuarioPagador.getSaldo() < valorTransferencia) {
                // throw new Exception("Saldo insuficiente para realizar transação!");
                throw new SaldoInsuficienteException();
            }
            return true;
        } else {
            // throw new Exception("Usuários Lojistas não são autorizados de realizar transações!");
            throw new UsuarioPagadorInvalidoException();
        }
    }
    
    public void autenticarTransacao() throws Exception {
        
        try {
            restTemplate.getForEntity(urlAuth, Map.class);            
        } catch (Exception e) {
            // throw new Exception("Transferência não autorizada!");
            throw new TransferenciaNaoAutorizadaException();
        }
        
    }
    
}
