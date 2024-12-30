package com.sofiasaless.desafiobackend.useCase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sofiasaless.desafiobackend.dto.TransferenciaDTO;
import com.sofiasaless.desafiobackend.exception.UsuarioNaoEncontradoException;
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

    @Transactional(rollbackOn = Exception.class) // o transactional serve para transações, aqui estão acontecendo várias transação, mas caso aconteça algum erro, vai acontecer um rowback das informações
    public Transferencia transferirValor(TransferenciaDTO transferenciaDTO) throws Exception {
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

        return this.transferenciaRepository.save(transferencia);
    }

    private boolean validarUsuarioPagador (Usuario usuarioPagador, double valorTransferencia) throws Exception {
        if (usuarioPagador.getTipoDoUsuario().toString().equalsIgnoreCase("NORMAL")) {
            if (usuarioPagador.getSaldo() < valorTransferencia) {
                throw new Exception("Saldo insuficiente para realizar transação!");
            }
            return true;
        } else {
            throw new Exception("Usuários Lojistas não são autorizados de realizar transações!");
        }
    }
    
    private boolean autenticarTransacao() {
        RestTemplate rt = new RestTemplate();
        rt.getForObject(urlAuth, Object.class);
        return true;
    }
    
}
