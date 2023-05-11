package com.example.salabelleza.controller;

import com.example.salabelleza.model.Contact;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/contact")
public class ContactController {

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

    @GetMapping
    public String showContactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "public/contacto";
    }

    @PostMapping
    public String submitContactForm(@ModelAttribute("contact") Contact contact) {
        // lógica para guardar el contacto en una base de datos u otro medio de almacenamiento
        return "redirect:/contact/success";
    }

    @GetMapping("/success")
    public String showSuccessPage() {
        return "contact-success";
    }
}