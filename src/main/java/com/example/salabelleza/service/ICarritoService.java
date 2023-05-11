package com.example.salabelleza.service;

import java.util.List;
import java.util.Optional;

import com.example.salabelleza.model.Carrito;
import com.example.salabelleza.model.Usuario;


public interface ICarritoService 
{
    List<Carrito> all();
    Optional<Carrito> findById(int id);
    List<Carrito> findByUsuario(Usuario usuario);
    Optional<Carrito> isProductInCart(Integer userId, Integer productId);

    void save(Carrito carrito);
    void update(Carrito carrito);
    void delete(Carrito carrito);
}
