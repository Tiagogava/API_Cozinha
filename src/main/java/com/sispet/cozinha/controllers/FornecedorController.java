package com.sispet.cozinha.controllers;

import com.sispet.cozinha.DTO.FornecedorDTO;
import com.sispet.cozinha.entidades.Fornecedor;
import com.sispet.cozinha.repositorios.FornecedorRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class FornecedorController {

        @Autowired
        FornecedorRepositorio fornecedorRepositorio;

        @GetMapping("/fornecedor")
        public List<Fornecedor> listarAnimais(){
            return fornecedorRepositorio.findAll();
        }

        @PostMapping("/fornecedor")
        public Fornecedor criarAnimal(@RequestBody @Valid FornecedorDTO fornecedorDTO){
            Fornecedor fornecedorNovo = new Fornecedor();

            fornecedorNovo.setNome(fornecedorDTO.getNome());
            fornecedorNovo.setTelefone(fornecedorDTO.getTelefone());
            fornecedorNovo.setCnpj(fornecedorDTO.getCnpj());

            return fornecedorRepositorio.save(fornecedorNovo);
        }

        @PutMapping("/fornecedor/{id}")
        public Fornecedor editarAnimal(@PathVariable long id, @RequestBody FornecedorDTO fornecedorDTO){

            Optional<Fornecedor> fornecedorEncontrado = fornecedorRepositorio.findById(id);

            fornecedorEncontrado.get().setNome(fornecedorDTO.getNome());
            fornecedorEncontrado.get().setTelefone(fornecedorDTO.getTelefone());
            fornecedorEncontrado.get().setCnpj(fornecedorDTO.getCnpj());

            return fornecedorRepositorio.save(fornecedorEncontrado.get());
        }

        @DeleteMapping("/fornecedor/{id}")
        public void deletarFornecedor(@PathVariable long id){
            fornecedorRepositorio.deleteById(id);
        }

    @ResponseStatus
    @ExceptionHandler
    public Map<String, String> interceptarErros(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

