package com.example.salabelleza.repository;

import java.util.List;

import com.example.salabelleza.model.Compra;
import com.example.salabelleza.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer>
{
    List<Compra> findByUsuario(Usuario usuario);
}
