package com.example.salabelleza.controller;

import javax.servlet.http.HttpSession;


import com.example.salabelleza.model.Cita;
import com.example.salabelleza.model.Producto;
import com.example.salabelleza.model.Servicio;
import com.example.salabelleza.model.Usuario;
import com.example.salabelleza.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/admin")
public class AdminController 
{
    @Autowired
    private IProductoService productoService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ICompraService compraService;

    @Autowired
    private ICitaService citaService;

    @Autowired
    private IServicioService servicioService;


    

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
    
    
    // Muestra todos los productos
    @GetMapping(value = {"", "/"})
    public String index(Model model) 
    {
        model.addAttribute("productos", productoService.all());
        return "admin/index";
    }





    // Citas ADMIN
    @GetMapping("/citas")
    public String listarTodasLasCitas(Model model) {
        List<Cita> citas = citaService.all();
        model.addAttribute("citas", citas);
        return "admin/cita/listar";
    }

    @GetMapping("/citas/{id}/editar")
    public String editarCita(@PathVariable Integer id, Model model) {
        Cita cita = citaService.findById(id).orElseThrow(() -> new RuntimeException("No se encontró la cita con ID " + id));
        List<Servicio> servicios = servicioService.all();
        model.addAttribute("cita", cita);
        model.addAttribute("servicios", servicios);
        return "admin/cita/editar";
    }

    @PostMapping("/citas/{id}/editar")
    public String guardarCitaEditada(@PathVariable Integer id, @ModelAttribute("cita") Cita cita) {
        cita.setId(id);
        citaService.save(cita);
        return "redirect:/admin/citas";
    }

    // Muestra todas las citas

    @GetMapping("/listCitas")
    public String citas(Model model)
    {
        model.addAttribute("citas", citaService.all());
        return "admin/citas";
    }

    // Muestra todas las ordenes
    @GetMapping("/compras")
    public String ordenes(Model model)
    {
        model.addAttribute("compras", compraService.all());
        return "admin/compras";
    }

}
