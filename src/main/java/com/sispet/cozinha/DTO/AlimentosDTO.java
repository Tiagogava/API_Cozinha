package com.sispet.cozinha.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Valid
public class AlimentosDTO {
    private long id;
    @NotBlank(message = "Campo Obrigatorio")
    private String nome;
    @NotBlank(message = "Campo Obrigatorio")
    private Double valor;
    @NotBlank(message = "Campo Obrigatorio")
    private Integer estoque;
}
