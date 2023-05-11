//package com.example.salabelleza.controller;
//
//import com.example.salabelleza.model.Producto;
//import com.example.salabelleza.model.Rating;
//import com.example.salabelleza.service.CitaService;
//import com.example.salabelleza.service.ValoracionService;
//import com.example.salabelleza.service.IServicioService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/opiniones")
//public class ValoracionController {
//
//    @Autowired
//    private IServicioService servicioService;
//    @Autowired
//    private CitaService citaService;
//    @Autowired
//    private ValoracionService opinionYValoracionService;
//
//
//    @GetMapping("/products/{id}/ratings")
//    @ResponseBody
//    public List<Rating> getProductRatings(@PathVariable Long id) {
//        Producto producto = productService.getProductById(id);
//        return producto.getRatings();
//    }
//}
