package com.example.salabelleza.service;

import com.example.salabelleza.model.Producto;
import com.example.salabelleza.model.Valoracion;

import java.util.List;
import java.util.Optional;


public interface IProductoService 
{
    List<Producto> all();
    Optional<Producto> findById(int id);
    List<Producto> search(String term);
    
    void save(Producto producto);
    void update(Producto producto);
    void delete(int id);

    Producto getProductoById(Long productId);

    Producto getProductoById(Integer productoId);

    void saveValoracion(Valoracion valoracion);
}
