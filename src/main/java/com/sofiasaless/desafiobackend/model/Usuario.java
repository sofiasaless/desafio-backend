package com.sofiasaless.desafiobackend.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import com.sofiasaless.desafiobackend.model.enums.TipoUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Preencha o campo nome")
    private String nome;

    @Column(unique = true)
    @Length(min = 11, message = "Tamanho mínimo de 11 caracteres")
    @NotEmpty(message = "Preencha o campo documentação")
    private String documentacao;

    @Column(unique = true)
    @Email(message = "Insira um e-mail válido")
    private String email;

    @NotEmpty(message = "Preencha o campo senha")    
    private String senha;

    @Min(value = 0, message = "Insiria um saldo maior ou igual a zero!")
    @NotNull(message = "Campo saldo não pode ser nulo")
    private double saldo;

    @NotNull(message = "Insiria um valor para o tipo do usuário!")
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoDoUsuario;

    @CreationTimestamp
    private LocalDateTime dataDeCriacao;


}
