package com.sofiasaless.desafiobackend.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.sofiasaless.desafiobackend.model.enums.TipoUsuario;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
    private Long id;

    @Schema(example = "Sofia Sales", requiredMode = RequiredMode.REQUIRED)
    private String nome;

    @Column(unique = true)
    @Schema(example = "001.002.003-99", requiredMode = RequiredMode.REQUIRED)
    private String documentacao;

    @Column(unique = true)
    @Schema(example = "eddardstark@email.com", requiredMode = RequiredMode.REQUIRED)
    private String email;

    @Schema(example = "123457", requiredMode = RequiredMode.REQUIRED)
    private String senha;

    @Schema(example = "10.50", requiredMode = RequiredMode.REQUIRED)
    private double saldo;

    @Enumerated(EnumType.STRING)
    @Schema(examples = {"NORMAL", "LOJISTA"}, exampleClasses = TipoUsuario.class, requiredMode = RequiredMode.REQUIRED)
    private TipoUsuario tipoDoUsuario;

    @CreationTimestamp
    private LocalDateTime dataDeCriacao;


}
