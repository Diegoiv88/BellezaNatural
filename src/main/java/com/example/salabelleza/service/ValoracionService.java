package com.example.salabelleza.service;

import com.example.salabelleza.model.Usuario;
import com.example.salabelleza.model.Valoracion;
import com.example.salabelleza.repository.ValoracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValoracionService {

    @Autowired
    private ValoracionRepository opinionYValoracionRepository;

    public List<Valoracion> obtenerOpinionesYValoracionesPorCliente(Usuario usuario) {
        return opinionYValoracionRepository.findByUsuario(usuario);
    }

    public void agregarOpinionYValoracion(Valoracion Valoracion) {
        opinionYValoracionRepository.save(Valoracion);
    }

    public void eliminarOpinionYValoracion(Valoracion Valoracion) {
        opinionYValoracionRepository.delete(Valoracion);
    }
}
