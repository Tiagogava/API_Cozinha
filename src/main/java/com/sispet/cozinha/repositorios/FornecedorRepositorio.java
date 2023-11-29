package com.sispet.cozinha.repositorios;

import com.sispet.cozinha.entidades.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FornecedorRepositorio extends JpaRepository<Fornecedor, Long> {

    List<Fornecedor> searchByNome(String nome);
}
