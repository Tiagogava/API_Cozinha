package com.sispet.cozinha.repositorios;

import com.sispet.cozinha.entidades.AgendaEvento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaEventoRepositorio extends JpaRepository<AgendaEvento, Long> {
}
