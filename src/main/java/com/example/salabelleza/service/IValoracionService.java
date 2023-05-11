package com.example.salabelleza.service;

import com.example.salabelleza.model.Servicio;
import com.example.salabelleza.model.Valoracion;

import java.util.List;
import java.util.Optional;

public interface IValoracionService {

    List<Valoracion> all();

    Optional<Valoracion> findById(int id);

    void save(Valoracion valoracion);

    void update(Valoracion valoracion);

    void delete(int id);

    Valoracion getServicioById(Long valoracionId);

    Servicio getServicioById(Integer valoracionId);
}
