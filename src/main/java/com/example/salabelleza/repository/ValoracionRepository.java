package com.example.salabelleza.repository;

import com.example.salabelleza.model.Usuario;
import com.example.salabelleza.model.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValoracionRepository extends JpaRepository<Valoracion, Long> {

    List<Valoracion> findByUsuario(Usuario usuario);
}