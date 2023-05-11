package com.example.salabelleza.service;

import java.util.List;
import java.util.Optional;

import com.example.salabelleza.model.Carrito;
import com.example.salabelleza.model.Usuario;
import com.example.salabelleza.repository.CarritoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CarritoServiceImpl implements ICarritoService
{
    @Autowired
    private CarritoRepository carritoRepository;


    @Override
    public List<Carrito> all() {
        return carritoRepository.findAll();
    }

    @Override
    public Optional<Carrito> findById(int id) {
        return carritoRepository.findById(id);
    }

    @Override
    public List<Carrito> findByUsuario(Usuario usuario) {
        return carritoRepository.findByUsuario(usuario);
    }

    @Override
    public Optional<Carrito> isProductInCart(Integer userId, Integer productId) {
        return carritoRepository.isProductInCart(userId, productId);
    }

    @Override
    public void save(Carrito carrito) {
        carritoRepository.save(carrito); 
    }

    @Override
    public void update(Carrito carrito) {
        carritoRepository.save(carrito);
    }

    @Override
    public void delete(Carrito carrito) {
        carritoRepository.delete(carrito);
    }
}
