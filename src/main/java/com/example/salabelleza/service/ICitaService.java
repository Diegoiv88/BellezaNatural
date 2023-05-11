package com.example.salabelleza.service;

import com.example.salabelleza.model.Carrito;
import com.example.salabelleza.model.Cita;
import com.example.salabelleza.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface ICitaService {

    List<Cita> all();
    Optional<Cita> findById(int id);
    List<Cita> findByUsuario(Usuario usuario);
    List<Cita> findByEstado(String estado);
//    Optional<Carrito> isProductInCart(Integer userId, Integer productId);

    void save(Cita cita);
    void update(Cita cita);
    void delete(Cita cita);


}
