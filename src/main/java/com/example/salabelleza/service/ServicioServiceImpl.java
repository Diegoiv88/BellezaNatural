package com.example.salabelleza.service;

import com.example.salabelleza.model.Servicio;
import com.example.salabelleza.model.Valoracion;
import com.example.salabelleza.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioServiceImpl implements IServicioService {

    @Autowired
    private ServicioRepository servicioRepository;


    @Override
    public List<Servicio> all() {
        return servicioRepository.findAll();
    }

    @Override
    public Optional<Servicio> findById(int id) {
        return servicioRepository.findById(id);
    }

//    @Override
//    public List<Servicio> search(String term) {
//        return servicioRepository.search(term);
//    }

    @Override
    public void save(Servicio servicio) {
        servicioRepository.save(servicio);
    }

    @Override
    public void update(Servicio servicio) {
        servicioRepository.save(servicio);
    }

    @Override
    public void delete(int id) {
        servicioRepository.deleteById(id);
    }

    @Override
    public Servicio getServicioById(Long servicioId) {
        return null;
    }

    @Override
    public Servicio getServicioById(Integer servicioId) {
        return null;
    }

    //    @Override
//    public void saveValoracion(Valoracion valoracion) {
//
//    }
    public Servicio buscarPorId(Integer id) {
        Optional<Servicio> optionalServicio = servicioRepository.findById(id);
        return optionalServicio.orElse(null);
    }
}
