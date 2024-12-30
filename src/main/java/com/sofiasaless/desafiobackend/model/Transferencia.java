package com.sofiasaless.desafiobackend.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "transferencias")
@Data
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double valor;

    @ManyToOne
    @JoinColumn(name = "pagadorId", insertable = false, updatable = false)
    private Usuario pagador;
    
    @ManyToOne
    @JoinColumn(name = "beneficiarioId", insertable = false, updatable = false)
    private Usuario beneficiario;

    private Long pagadorId;
    private Long beneficiarioId;

    @CreationTimestamp
    private LocalDateTime dataDeCriacao;
}
