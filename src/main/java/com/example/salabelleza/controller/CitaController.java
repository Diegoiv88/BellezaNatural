package com.example.salabelleza.controller;

import com.example.salabelleza.model.Cita;
import com.example.salabelleza.model.Servicio;
import com.example.salabelleza.model.Usuario;
import com.example.salabelleza.service.CitaService;
import com.example.salabelleza.service.ICitaService;
import com.example.salabelleza.service.IUsuarioService;
import com.example.salabelleza.service.IServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.context.Context;

@Controller
@RequestMapping("/citas")
public class CitaController {
    @Autowired
    private IServicioService servicioService;
    @Autowired
    private ICitaService citaService;
    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;


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

    @GetMapping(value = {"", "/"})
    public String index(Model model, HttpSession session) {
        List<Servicio> servicios = servicioService.all();
        List<Cita> citas = citaService.findByUsuario(usuarioService.findById(Integer.parseInt(session.getAttribute("usuario.id").toString())).get());
        model.addAttribute("servicios", servicios);
        model.addAttribute("citas", citas);
        return "cita/index";
    }

    @GetMapping("/citas")
    public String listarCitas(Model model, HttpSession session) {

        Integer userId = Integer.parseInt(session.getAttribute("usuario.id").toString());
        Usuario usuario = usuarioService.findById(userId).get();
        List<Cita> citas = citaService.findByUsuario(usuario);
        List<Servicio> servicios = servicioService.all();
        model.addAttribute("citas", citas);
        model.addAttribute("servicios", servicios);
        return "cita/index";
    }

    @PostMapping("/guardarCita")
    public String guardarCita(@RequestParam("servicio") Integer servicioId,
                              @RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha,
                              @RequestParam("hora") LocalTime hora,
                              Model model, HttpSession session) throws MessagingException {
        // Obtiene el usuario de la sesión
        Integer userId = Integer.parseInt(session.getAttribute("usuario.id").toString());
        Usuario usuario = usuarioService.findById(userId).get();

        // Obtiene el servicio seleccionado
        Servicio servicio = servicioService.findById(servicioId).get();

        // Crea la cita
        Cita cita = new Cita();
        cita.setFecha(fecha);
        cita.setHora(hora);
        cita.setEstado("PENDIENTE");
        cita.setUsuario(usuario);
        cita.setServicio(servicio);
        citaService.save(cita);


        try {
            // Crea el contexto de la plantilla
            Context context = new Context();
            context.setVariable("nombre", usuario.getNombre());
            context.setVariable("servicio", servicio.getNombre());
            context.setVariable("fecha", fecha.toString());
            context.setVariable("hora", hora.toString());

            // Procesa la plantilla
            String htmlContent = templateEngine.process("cita/confirmacion-cita", context);
            System.out.println(htmlContent);
            // Envía un correo electrónico con la información de la cita
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(usuario.getEmail());
            helper.setSubject("Confirmación de cita");
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (Exception e) {
            // Maneja la excepción
            e.printStackTrace();
        }

        // Obtiene todos los servicios y los agrega al modelo
        List<Servicio> servicios = servicioService.all();
        model.addAttribute("servicios", servicios);

        // Obtiene las citas del usuario y las agrega al modelo
        List<Cita> citas = citaService.findByUsuario(usuario);
        model.addAttribute("citas", citas);


        return "cita/index";
    }

    @Scheduled(fixedDelay = 2400000) // se ejecuta cada 40 minutos
    public void actualizarEstadosCitas() {
        List<Cita> citas = citaService.findByEstado("PENDIENTE");
        LocalDateTime hace40Minutos = LocalDateTime.now().minusMinutes(40);
        for (Cita cita : citas) {
            LocalDateTime fechaHoraCita = LocalDateTime.of(cita.getFecha(), cita.getHora());
            if (cita.getEstado().equals("PENDIENTE") && fechaHoraCita.isBefore(hace40Minutos)) {
                cita.setEstado("FINALIZADA");
                citaService.save(cita);
            }
        }
    }
}
