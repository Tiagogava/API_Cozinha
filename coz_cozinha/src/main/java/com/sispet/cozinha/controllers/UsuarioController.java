package com.sispet.cozinha.controllers;

import com.sispet.cozinha.DTO.UsuarioDTO;
import com.sispet.cozinha.entidades.Usuario;
import com.sispet.cozinha.repositorios.UsuarioRepositorio;
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
public class UsuarioController {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/usuarios")
    public List<Usuario> listarUsuarios(){
        return usuarioRepositorio.findAll();
    }

    @PostMapping("/usuarios")
    public Usuario criarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO){
        Usuario usuarioNovo = new Usuario();

        usuarioNovo.setNome(usuarioDTO.getNome());
        usuarioNovo.setLogin(usuarioDTO.getLogin());
        usuarioNovo.setSenha(usuarioDTO.getSenha());

        return usuarioRepositorio.save(usuarioNovo);
    }

    @PutMapping("/usuarios/{id}")
    public Usuario editarUsuario(@PathVariable long id, @RequestBody UsuarioDTO usuarioDTO){

        Optional<Usuario> usuarioEncontrado = usuarioRepositorio.findById(id);

        usuarioEncontrado.get().setNome(usuarioDTO.getNome());
        usuarioEncontrado.get().setLogin(usuarioDTO.getLogin());
        usuarioEncontrado.get().setSenha(usuarioDTO.getSenha());

        return usuarioRepositorio.save(usuarioEncontrado.get());
    }

    @DeleteMapping("/usuarios/{id}")
    public void deletarUsuario(@PathVariable long id){
        usuarioRepositorio.deleteById(id);
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
