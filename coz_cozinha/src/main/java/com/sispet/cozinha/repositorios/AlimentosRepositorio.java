package com.sispet.cozinha.repositorios;

import com.sispet.cozinha.entidades.Alimentos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlimentosRepositorio extends JpaRepository<Alimentos, Long> {
}
