package com.example.salabelleza.controller;

import com.example.salabelleza.model.Producto;
import com.example.salabelleza.model.Usuario;
import com.example.salabelleza.service.IUsuarioService;
import com.example.salabelleza.service.ImageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private ImageService image;


    // Atributos globales
    @ModelAttribute("mainAttributes")
    public void mainAttributes(Model model, HttpSession session)
    {
        Object usuario_id = session.getAttribute("usuario.id");
        Object usuario_tipo = session.getAttribute("usuario.tipo");

        Boolean isLoggedIn = (usuario_id == null) ? (false) : (!usuario_id.toString().equals("0"));
        Boolean isAdmin = (usuario_tipo == null) ? (false) : (usuario_tipo.toString().equals("ADMIN"));

        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("isAdmin", isAdmin);
    }




    // Muestra todos los usuarios
    @GetMapping(value = {"", "/"})
    public String usuarios(Model model)
    {
        model.addAttribute("usuarios", usuarioService.all());
        return "/usuarios/index";
    }

    // Editar un usuarios
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) throws JsonProcessingException
    {
        Optional<Usuario> tmp = usuarioService.findById(id);
        if(!tmp.isPresent()) return "redirect:/usuarios/";

        //
        Usuario usuario = tmp.get();

        model.addAttribute("usuario", usuario);
        return "usuarios/editar";
    }

    //Editar usuarios POST


    @PostMapping("/editar")
    public String editarUsuario(@Valid Usuario usuario, BindingResult result) {
        // Verificar errores
        if(result.hasErrors()) {
            for(ObjectError error : result.getAllErrors()) {
                if(error instanceof FieldError) {
                    FieldError fieldError = (FieldError) error;
                    String fieldName = fieldError.getField();
                    String errorMessage = fieldError.getDefaultMessage();
                    System.out.println("Error en el campo " + fieldName + ": " + errorMessage);
                } else {
                    String errorMessage = error.getDefaultMessage();
                    System.out.println("Error de validación general: " + errorMessage);
                }
            }
            return "usuarios/editar";
        }

        // Editar usuario
        Usuario tmp = usuarioService.findById(usuario.getId()).get();



        usuarioService.update(usuario);

        return "redirect:/usuario/";
    }

//     Eliminar un usuario
@GetMapping("/eliminar/{id}")
public String eliminar(@PathVariable Integer id) {
    Usuario tmp = usuarioService.findById(id).orElse(null);

    if (tmp != null) {
        usuarioService.delete(id);
    }

    return "redirect:/usuarios";
}

    //Perfil usuario
    @GetMapping("/perfil")
    public String verPerfil(Model model, HttpSession session) {

        Integer userId = Integer.parseInt(session.getAttribute("usuario.id").toString());
        Usuario usuario = usuarioService.findById(userId).orElse(null);

        if (usuario == null) {
            // Si el usuario no se encuentra, redireccionar a la página de inicio de sesión
            return "redirect:/public/login";
        }

// Agregar los datos del usuario al modelo para que se muestren en la vista
        model.addAttribute("usuario", usuario);

        return "/usuarios/perfil";
    }
}
