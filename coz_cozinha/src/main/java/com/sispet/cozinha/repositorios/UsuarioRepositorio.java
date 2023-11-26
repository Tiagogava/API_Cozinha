package com.sispet.cozinha.repositorios;

import com.sispet.cozinha.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

}
