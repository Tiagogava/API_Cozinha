package com.sispet.cozinha.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class AgendaEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = false)
    private Integer qtdPessoas;

    @Column(nullable = false)
    private String nomeEvento;

}
