package com.example.salabelleza.repository;

import java.util.List;
import java.util.Optional;

import com.example.salabelleza.model.Carrito;
import com.example.salabelleza.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer> 
{
    List<Carrito> findByUsuario(Usuario usuario);

    @Query(value = "SELECT * FROM carritos WHERE usuario_id = ?1 AND producto_id = ?2 LIMIT 1", nativeQuery = true)
    Optional<Carrito> isProductInCart(Integer userId, Integer productId);
}
