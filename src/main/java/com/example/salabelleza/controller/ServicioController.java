package com.example.salabelleza.controller;

import com.example.salabelleza.model.Producto;
import com.example.salabelleza.model.Servicio;
import com.example.salabelleza.model.Usuario;
import com.example.salabelleza.model.Valoracion;
import com.example.salabelleza.repository.ServicioRepository;
import com.example.salabelleza.service.IServicioService;
import com.example.salabelleza.service.IUsuarioService;
import com.example.salabelleza.service.ImageService;
import com.example.salabelleza.service.RatingForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/servicios")
public class ServicioController {

    @Autowired
    private ServicioRepository servicioRepository;
    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IServicioService servicioService;

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

    // Ruta principal - muestra todos los servicios
    @GetMapping(value = {"", "/"})
    public String index(Model model)
    {
        model.addAttribute("servicios", servicioService.all());
        return "servicios/index";
    }

    // Crear servicio
    @GetMapping("/crear")
    public String create(Model model)
    {
        model.addAttribute("servicio", new Servicio());
        return "servicios/crear";
    }

    @PostMapping("/crear")
    public String create_POST(@Valid Servicio servicio, BindingResult result, @RequestParam("imagenFile") MultipartFile file, Model model, HttpSession session) throws IOException
    {
        // Verificar errores
        if(file.isEmpty()) // No hay imagen del Servicio
        {
            result.addError(new ObjectError("tmp", "tmp")); // Añadir un error temporal
        }

        if(result.hasErrors())
        {
            if(file.isEmpty()) model.addAttribute("emptyImage", 1); // Añadir atributo de que no hay imagen
            return "servicios/crear";
        }

        // Crear servicio
        int userId = Integer.parseInt(session.getAttribute("usuario.id").toString());
        Usuario usuario = usuarioService.findById(userId).get();
        String imageName = image.saveImage(file);

        servicio.setUsuario(usuario);
        servicio.setImagen(imageName);
        servicioService.save(servicio);
        return "redirect:/servicios";
    }

    // Editar servicio
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) throws JsonProcessingException
    {
        Optional<Servicio> tmp = servicioService.findById(id);
        if(!tmp.isPresent()) return "redirect:/servicos/";

        //
        Servicio servicio = tmp.get();

        model.addAttribute("servicio", servicio);
        return "servicios/editar";
    }
    @PostMapping("/editar")
    public String editar_POST(@Valid Servicio servicio, BindingResult result, @RequestParam("imagenFile") MultipartFile file) throws IOException
    {
        // Verificar errores
        if(result.hasErrors())
        {
            return "servicios/editar";
        }

        // Editar servicio
        Servicio tmp = servicioService.findById(servicio.getId()).get();

        if(file.isEmpty()) // La imagen no cambia
        {
            servicio.setImagen(tmp.getImagen());
        }
        else // La imagen cambia
        {
            if(!tmp.getImagen().equals("default.jpg")) // Eliminar la imagen anterior
            {
                image.deleteImage(tmp.getImagen());
            }

            String imageName = image.saveImage(file); // Crear la imagen nueva
            servicio.setImagen(imageName);
        }

        servicio.setUsuario(tmp.getUsuario());
        servicioService.update(servicio);

        return "redirect:/servicios";
    }




    // Eliminar un servicio
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id)
    {
        Servicio tmp = servicioService.findById(id).get();
        if(!tmp.getImagen().equals("default.jpg")) // Eliminar imagen del servicio si no usa la imagen default
        {
            image.deleteImage(tmp.getImagen());
        }

        servicioService.delete(id);
        return "redirect:/servicios";
    }

// Valoración servcio->

//    @PostMapping("/{id}/rate")
//    public String rateProduct(@PathVariable("id") int servicioId, @ModelAttribute("ratingForm") RatingForm ratingForm, Model model) {
//        Servicio servicio = servicioService.findById(servicioId)
//                .orElseThrow(() -> new NoSuchElementException("No existe producto con ID " + servicioId));
//
//        Valoracion valoracion = new Valoracion();
//        valoracion.setStars(ratingForm.getStars());
//        valoracion.setComment(ratingForm.getComment());
//        valoracion.setProducto(servicio);
//
//        servicioService.saveValoracion(valoracion);
//
//        return "redirect:/productos/" + productoId;
//    }
}
