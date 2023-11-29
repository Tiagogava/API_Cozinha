package com.sispet.cozinha.controllers;

import com.sispet.cozinha.DTO.UtensiliosCozinhaDTO;
import com.sispet.cozinha.entidades.UtensiliosCozinha;
import com.sispet.cozinha.repositorios.UtensiliosCozinhaRepositorio;
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
public class UtensiliosCozinhaController {

    @Autowired
    UtensiliosCozinhaRepositorio utensiliosCozinhaRepositorio;

    @GetMapping("/utensilios")
    public List<UtensiliosCozinha> listarUtensilios(){
        return utensiliosCozinhaRepositorio.findAll();
    }

    @PostMapping("/utensilios")
    public UtensiliosCozinha criarUtensilios(@RequestBody @Valid UtensiliosCozinhaDTO utensiliosCozinhaDTO){
        UtensiliosCozinha utensiliosNovo = new UtensiliosCozinha();

        utensiliosNovo.setNome(utensiliosCozinhaDTO.getNome());
        utensiliosNovo.setDescricao(utensiliosCozinhaDTO.getDescricao());

        return utensiliosCozinhaRepositorio.save(utensiliosNovo);
    }

    @PutMapping("/utensilios/{id}")
    public UtensiliosCozinha editarAgendaVet(@PathVariable long id, @RequestBody UtensiliosCozinhaDTO utensiliosCozinhaDTO){

        Optional<UtensiliosCozinha> utensiliosEncontrado = utensiliosCozinhaRepositorio.findById(id);

        utensiliosEncontrado.get().setNome(utensiliosCozinhaDTO.getNome());
        utensiliosEncontrado.get().setDescricao(utensiliosCozinhaDTO.getDescricao());

        return utensiliosCozinhaRepositorio.save(utensiliosEncontrado.get());
    }

    @DeleteMapping("/utensilios/{id}")
    public void deletarAgendaVet(@PathVariable long id){
        utensiliosCozinhaRepositorio.deleteById(id);
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
