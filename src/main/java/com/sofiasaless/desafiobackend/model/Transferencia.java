package com.sofiasaless.desafiobackend.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transferencias")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
    private Long id;

    @Schema(example = "87.23", requiredMode = RequiredMode.REQUIRED)
    private double valor;

    @ManyToOne
    @JoinColumn(name = "pagador_id", insertable = false, updatable = false)
    private Usuario pagador;
    
    @ManyToOne
    @JoinColumn(name = "beneficiario_id", insertable = false, updatable = false)
    private Usuario beneficiario;

    @Column(name = "pagador_id")
    private Long pagadorId;

    @Column(name = "beneficiario_id")
    private Long beneficiarioId;

    @CreationTimestamp
    private LocalDateTime dataDeCriacao;
}
