package com.sispet.cozinha.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalDate;

@Getter
@Setter
public class FornecedorDTO {

    @NotBlank(message = "Campo obrigatório")
    private String nome;
    @NotBlank(message = "Campo obrigatório")
    private String telefone;
    //@CNPJ(message = "CNPJ inválido")
    @NotBlank(message = "Campo obrigatório")
    private String cnpj;

}
