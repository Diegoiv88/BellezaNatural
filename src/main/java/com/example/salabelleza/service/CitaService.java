package com.example.salabelleza.service;


import com.example.salabelleza.model.Cita;
import com.example.salabelleza.model.Usuario;
import com.example.salabelleza.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class CitaService {
    @Autowired
    private CitaRepository citaRepository;


    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }

    public boolean guardarCita(Cita cita) {
        try {
            citaRepository.save(cita);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public List<Cita> findByUsuario(Usuario usuario) {
        return citaRepository.findByUsuario(usuario);
    }
}
