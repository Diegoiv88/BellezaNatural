package com.example.salabelleza.service;

import java.util.List;
import java.util.Optional;

import com.example.salabelleza.model.Compra;
import com.example.salabelleza.model.Usuario;

public interface ICompraService 
{
    List<Compra> all();
    Optional<Compra> findById(Integer id);
    List<Compra> findByUsuario(Usuario usuario);

    void save(Compra compra);
    void update(Compra compra);
    void delete(Compra compra);
}
