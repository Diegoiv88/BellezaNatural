package com.example.salabelleza.repository;

import com.example.salabelleza.model.Usuario;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>
{
    Optional<Usuario> findByEmail(String email);

    @Query(value = "SELECT * FROM usuarios  WHERE tipo != 'ADMIN'", nativeQuery = true)
    List<Usuario> findAllExceptAdmin();
}
