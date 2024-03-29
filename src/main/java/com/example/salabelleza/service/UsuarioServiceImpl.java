package com.example.salabelleza.service;

import com.example.salabelleza.model.Producto;
import com.example.salabelleza.model.Usuario;
import com.example.salabelleza.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioServiceImpl implements IUsuarioService
{
    @Autowired
    private UsuarioRepository usuarioRepository;

    
    
    @Override
    public List<Usuario> all() {
        return usuarioRepository.findAllExceptAdmin();
    }

    @Override
    public Optional<Usuario> findById(int id) {
        return usuarioRepository.findById(id);
    }
    
    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Usuario save(Usuario usuario) {
        usuarioRepository.save(usuario);
        return usuario;
    }

//    @Override
//    public void update(Usuario usuario) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

//    @Override
//    public void delete(int id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public void update(Usuario usuario) {
        usuarioRepository.save(usuario);
    }
    @Override
    public void delete(int id) {
        usuarioRepository.deleteById(id);
    }

}
