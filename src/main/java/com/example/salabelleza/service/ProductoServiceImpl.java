package com.example.salabelleza.service;

import com.example.salabelleza.model.Producto;
import com.example.salabelleza.model.Valoracion;
import com.example.salabelleza.repository.ProductoRepository;
import java.util.List;
import java.util.Optional;

import com.example.salabelleza.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductoServiceImpl implements IProductoService
{
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Producto getProductoById(Integer productoId) {
        return productoRepository.findById(productoId).orElse(null);
    }

    @Override
    public void saveValoracion(Valoracion valoracion) {
        ratingRepository.save(valoracion);
    }


    @Override
    public List<Producto> all() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> findById(int id) {
        return productoRepository.findById(id);
    }

    @Override
    public List<Producto> search(String term) {
        return productoRepository.search(term);
    }

    @Override
    public void save(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void update(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void delete(int id) {
        productoRepository.deleteById(id);
    }

    @Override
    public Producto getProductoById(Long productId) {
        return null;
    }
}
