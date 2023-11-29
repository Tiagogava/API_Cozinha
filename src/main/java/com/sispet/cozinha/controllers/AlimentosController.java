package com.sispet.cozinha.controllers;

import com.sispet.cozinha.DTO.AlimentosDTO;
import com.sispet.cozinha.entidades.Alimentos;
import com.sispet.cozinha.repositorios.AlimentosRepositorio;
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
public class AlimentosController {

    @Autowired
    AlimentosRepositorio alimentosRepositorio;

    @GetMapping("/alimentos")
    public List<Alimentos> listarAlimentos(){
        return alimentosRepositorio.findAll();
    }

    @PostMapping("/alimentos")
    public Alimentos criarAlimentos(@RequestBody @Valid AlimentosDTO alimentosDTO){
        Alimentos alimentosNovo = new Alimentos();

        alimentosNovo.setNome(alimentosDTO.getNome());
        alimentosNovo.setValor(alimentosDTO.getValor());
        alimentosNovo.setEstoque(alimentosDTO.getEstoque());

        return alimentosRepositorio.save(alimentosNovo);
    }

    @PutMapping("/produtos/{id}")
    public Alimentos editarAlimentos(@PathVariable long id, @RequestBody AlimentosDTO alimentosDTO){

        Optional<Alimentos> produtoEncontrado = alimentosRepositorio.findById(id);

        produtoEncontrado.get().setNome(alimentosDTO.getNome());
        produtoEncontrado.get().setValor(alimentosDTO.getValor());
        produtoEncontrado.get().setEstoque(alimentosDTO.getEstoque());

        return alimentosRepositorio.save(produtoEncontrado.get());
    }

    @DeleteMapping("/alimentos/{id}")
    public void deletarProduto(@PathVariable long id){
        alimentosRepositorio.deleteById(id);
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
