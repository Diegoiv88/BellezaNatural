package com.example.salabelleza.service;

import com.example.salabelleza.model.Carrito;
import com.example.salabelleza.model.Cita;
import com.example.salabelleza.model.Usuario;
import com.example.salabelleza.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaServiceImpl implements  ICitaService{

    @Autowired
    private CitaRepository citaRepository;

    @Override
    public List<Cita> all() {
        return citaRepository.findAll();
    }

    @Override
    public Optional<Cita> findById(int id) {
        return citaRepository.findById(id);
    }

    @Override
    public List<Cita> findByUsuario(Usuario usuario) {
        return citaRepository.findByUsuario(usuario);
    }

    @Override
    public List<Cita> findByEstado(String pendiente) {
        return citaRepository.findByEstado(pendiente);
    }

//    @Override
//    public Optional<Carrito> isProductInCart(Integer userId, Integer productId) {
//        return Optional.empty();
//    }


    @Override
    public void save(Cita cita) {
        citaRepository.save(cita);
    }

    @Override
    public void update(Cita cita) {
        citaRepository.save(cita);
    }

    @Override
    public void delete(Cita cita) {
        citaRepository.delete(cita);
    }
}
