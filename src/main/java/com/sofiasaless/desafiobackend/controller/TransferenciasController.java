package com.sofiasaless.desafiobackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sofiasaless.desafiobackend.dto.BadRequestResponseDTO;
import com.sofiasaless.desafiobackend.dto.TransferenciaRequestDTO;
import com.sofiasaless.desafiobackend.dto.TransferenciaResponseDTO;
import com.sofiasaless.desafiobackend.dto.UsuarioResponseDTO;
import com.sofiasaless.desafiobackend.model.Transferencia;
import com.sofiasaless.desafiobackend.useCase.TransferirValorUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Transferências", description = "Pontos relacionados a transferências/pagamentos entre usuários")
@RestController
@RequestMapping("/transferencia")
@RequiredArgsConstructor
public class TransferenciasController {
    
    private final TransferirValorUseCase transferirValorUseCase;

    @Operation(
        summary = "Realizar transferências entre usuários", 
        description = "Essa função é reponsável por realizar uma transferência de pagamento entre dois usuários"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = TransferenciaResponseDTO.class)))
        }),
        @ApiResponse(responseCode = "400", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = BadRequestResponseDTO.class)))
        })
    })
    @PostMapping("/pagar")
    public ResponseEntity<Object> realizarPagamento(@Valid @RequestBody TransferenciaRequestDTO transferenciaDTO) throws Exception {
        try {
            var result = this.transferirValorUseCase.transferirValor(transferenciaDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BadRequestResponseDTO(e.getMessage()));
        }
    }

}
