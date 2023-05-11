package com.example.salabelleza.service;

import com.example.salabelleza.model.Servicio;
import com.example.salabelleza.model.Valoracion;

import java.util.List;
import java.util.Optional;

public class ValoracionServiceImpl implements  IValoracionService{
    @Override
    public List<Valoracion> all() {
        return null;
    }

    @Override
    public Optional<Valoracion> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void save(Valoracion valoracion) {

    }

    @Override
    public void update(Valoracion valoracion) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Valoracion getServicioById(Long valoracionId) {
        return null;
    }

    @Override
    public Servicio getServicioById(Integer valoracionId) {
        return null;
    }
}
