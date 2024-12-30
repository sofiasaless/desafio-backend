package com.sofiasaless.desafiobackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sofiasaless.desafiobackend.dto.TransferenciaDTO;
import com.sofiasaless.desafiobackend.useCase.TransferirValorUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transferencia")
@RequiredArgsConstructor
public class TransferenciasController {
    
    private final TransferirValorUseCase transferirValorUseCase;

    @PostMapping("/pagar")
    public ResponseEntity<Object> realizarPagamento(@RequestBody TransferenciaDTO transferenciaDTO) throws Exception {
        try {
            var result = this.transferirValorUseCase.efetuarTransferencia(transferenciaDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
