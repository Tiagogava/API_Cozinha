package com.sispet.cozinha.controllers;

import com.sispet.cozinha.DTO.AgendaEventoDTO;
import com.sispet.cozinha.entidades.AgendaEvento;
import com.sispet.cozinha.repositorios.AgendaEventoRepositorio;
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
public class AgendaEventoController {

    @Autowired
    AgendaEventoRepositorio agendaEventoRepositorio;

    @GetMapping("/agenda-evento")
    public List<AgendaEvento> listarEventos(){
        return agendaEventoRepositorio.findAll();
    }

    @PostMapping("/agenda-evento")
    public AgendaEvento criarEventos(@RequestBody @Valid AgendaEventoDTO agendaEventoDTO){
        AgendaEvento agendaEventoNovo = new AgendaEvento();

        agendaEventoNovo.setData(agendaEventoDTO.getData());
        agendaEventoNovo.setNomeEvento(agendaEventoDTO.getNomeEvento());
        agendaEventoNovo.setQtdPessoas(agendaEventoDTO.getQtdPessoas());

        return agendaEventoRepositorio.save(agendaEventoNovo);
    }

    @PutMapping("/agenda-evento/{id}")
    public AgendaEvento editarEventos(@PathVariable long id, @RequestBody AgendaEventoDTO agendaEventoDTO){

        Optional<AgendaEvento> agendaEventoEncontrado = agendaEventoRepositorio.findById(id);

        agendaEventoEncontrado.get().setData(agendaEventoDTO.getData());
        agendaEventoEncontrado.get().setNomeEvento(agendaEventoDTO.getNomeEvento());
        agendaEventoEncontrado.get().setQtdPessoas(agendaEventoDTO.getQtdPessoas());

        return agendaEventoRepositorio.save(agendaEventoEncontrado.get());
    }

    @DeleteMapping("/agenda-evento/{id}")
    public void deletarEventos(@PathVariable long id){
        agendaEventoRepositorio.deleteById(id);
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
