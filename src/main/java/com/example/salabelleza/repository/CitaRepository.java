package com.example.salabelleza.repository;

import com.example.salabelleza.model.Cita;
import com.example.salabelleza.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {
    List<Cita> findByUsuario(Usuario usuario);

    List<Cita> findByEstado(String pendiente);

}