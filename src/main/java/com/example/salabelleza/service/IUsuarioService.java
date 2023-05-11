package com.example.salabelleza.service;

import com.example.salabelleza.model.Usuario;
import java.util.List;
import java.util.Optional;


public interface IUsuarioService 
{
    List<Usuario> all();
    Optional<Usuario> findById(int id);
    Optional<Usuario> findByEmail(String email);
    
    Usuario save(Usuario usuario);
    void update(Usuario usuario);
    void delete(int id);
}
