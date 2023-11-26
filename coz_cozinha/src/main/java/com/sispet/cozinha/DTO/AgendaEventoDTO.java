package com.sispet.cozinha.DTO;

import com.sispet.cozinha.entidades.Fornecedor;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class AgendaEventoDTO {

    private long id;
    @NotBlank(message = "Campo obrigatorio")
    private LocalDateTime data;
    @NotBlank(message = "Campo obrigatorio")
    private Integer qtdPessoas;
    @NotBlank(message = "Campo obrigatorio")
    private String nomeEvento;
}
