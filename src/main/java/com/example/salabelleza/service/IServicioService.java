package com.example.salabelleza.service;

import com.example.salabelleza.model.Producto;
import com.example.salabelleza.model.Servicio;
import com.example.salabelleza.model.Valoracion;

import java.util.List;
import java.util.Optional;

public interface IServicioService {
    List<Servicio> all();
    Optional<Servicio> findById(int id);

//    List<Servicio> search(String term);

    void save (Servicio servicio);
    void update(Servicio servicio);
    void delete(int id);
    Servicio getServicioById(Long servicioId);

    Servicio getServicioById(Integer servicioId);

//    void saveValoracion(Valoracion valoracion);
}

