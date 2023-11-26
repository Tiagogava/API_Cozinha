package com.sispet.cozinha.DTO;

import com.sispet.cozinha.entidades.Fornecedor;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UtensiliosCozinhaDTO {

    private long id;
    @NotBlank(message = "Campo obrigatorio")
    private String nome;
    @NotBlank(message = "Campo obrigatorio")
    private String descricao;

}
