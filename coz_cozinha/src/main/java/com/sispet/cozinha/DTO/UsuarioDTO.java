package com.sispet.cozinha.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UsuarioDTO {

    private long id;

    @NotBlank(message = "O campo login é obrigatório")
    @Length(min = 3, max = 5, message = "O login deverá ter no minimo {min} e no máximo {max} caracteres")
    private String login;

    @NotBlank(message = "O campo senha é obrigatório")
    @Length(min = 3, max = 5, message = "A senha deverá ter no minimo {min} e no máximo {max} caracteres")
    private String senha;

    @NotBlank(message = "O campo nome é obrigatório")
    @Length(min = 3, max = 50, message = "O nome deverá ter no minimo {min} e no máximo {max} caracteres")
    private String nome;
}
