package com.example.salabelleza.controller;

import com.example.salabelleza.model.Producto;
import com.example.salabelleza.model.Valoracion;
import com.example.salabelleza.model.Usuario;
import com.example.salabelleza.service.ImageService;
import com.example.salabelleza.service.RatingForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.example.salabelleza.service.IProductoService;
import com.example.salabelleza.service.IUsuarioService;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping("/productos")
public class ProductoController 
{
    @Autowired
    private IUsuarioService usuarioService;
    
    @Autowired
    private IProductoService productoService;
    
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
    
    
    // Ruta principal - muestra todos los productos
    @GetMapping(value = {"", "/"}) 
    public String index(Model model)
    {
        model.addAttribute("productos", productoService.all());
        return "productos/index";
    }

    
    // Crear producto

    @GetMapping("/crear")
    public String create(Model model)
    {
        model.addAttribute("producto", new Producto());
        return "productos/crear";
    }
    
    @PostMapping("/crear")
    public String create_POST(@Valid Producto producto, BindingResult result, @RequestParam("imagenFile") MultipartFile file, Model model, HttpSession session) throws IOException
    {
        // Verificar errores
        if(file.isEmpty()) // No hay imagen del producto
        {
            result.addError(new ObjectError("tmp", "tmp")); // Añadir un error temporal
        }

        if(result.hasErrors())
        {
            if(file.isEmpty()) model.addAttribute("emptyImage", 1); // Añadir atributo de que no hay imagen
            return "productos/crear";
        }

        // Crear producto
        int userId = Integer.parseInt(session.getAttribute("usuario.id").toString());
        Usuario usuario = usuarioService.findById(userId).get();
        String imageName = image.saveImage(file);

        producto.setUsuario(usuario);
        producto.setImagen(imageName);
        productoService.save(producto);
        return "redirect:/productos";
    }
    

    // Editar producto
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) throws JsonProcessingException
    {
        Optional<Producto> tmp = productoService.findById(id);
        if(!tmp.isPresent()) return "redirect:/productos/";

        //
        Producto producto = tmp.get();

        model.addAttribute("producto", producto);
        return "productos/editar";
    }
    
    @PostMapping("/editar")
    public String editar_POST(@Valid Producto producto, BindingResult result, @RequestParam("imagenFile") MultipartFile file) throws IOException
    {
        // Verificar errores
        if(result.hasErrors())
        {
            return "productos/editar";
        }

        // Editar producto
        Producto tmp = productoService.findById(producto.getId()).get();

        if(file.isEmpty()) // La imagen no cambia
        {
            producto.setImagen(tmp.getImagen());
        }
        else // La imagen cambia
        {
            if(!tmp.getImagen().equals("default.jpg")) // Eliminar la imagen anterior
            {
                image.deleteImage(tmp.getImagen());
            }

            String imageName = image.saveImage(file); // Crear la imagen nueva
            producto.setImagen(imageName);
        }

        producto.setUsuario(tmp.getUsuario());
        productoService.update(producto);

        return "redirect:/productos";
    }
    

    // Eliminar un producto
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id)
    {
        Producto tmp = productoService.findById(id).get();
        if(!tmp.getImagen().equals("default.jpg")) // Eliminar imagen del producto si no usa la imagen default
        {
            image.deleteImage(tmp.getImagen());
        }
        
        productoService.delete(id);
        return "redirect:/productos";
    }

    //Valoración producto
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{id}/rate")
    public String rateProduct(@PathVariable("id") int productoId, @ModelAttribute("ratingForm") RatingForm ratingForm, Model model){
        Producto producto = productoService.findById(productoId)
                .orElseThrow(() -> new NoSuchElementException("No existe producto con ID " + productoId));

        Valoracion valoracion = new Valoracion();
        valoracion.setStars(ratingForm.getStars());
        valoracion.setComment(ratingForm.getComment());
        valoracion.setProducto(producto);

        productoService.saveValoracion(valoracion);

        return "redirect:/productos/" + productoId;
    }
}
