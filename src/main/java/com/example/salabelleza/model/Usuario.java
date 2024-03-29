package com.example.salabelleza.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuarios")
@NoArgsConstructor
public class Usuario 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //
    @Column(length = 64)
    @Size(min = 3, max = 64, message = "El nombre debe contener entre 3 y 64 caracteres")
    private String nombre;

    //
    @Column(length = 64)
    @Size(min = 3, max = 64, message = "El apellido debe contener entre 3 y 64 caracteres")
    private String apellido;

    //
    @Column(length = 64)
    @Size(min = 0, max = 64, message = "El email debe contener entre 3 y 64 caracteres")
    @Email(regexp = ".+[@].+[\\.].+", message = "La dirección email no es válida")
    private String email;

    //
    @Column(length = 62) // 62 caracteres debido al hash
    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;

    //
    @Column(length = 15) // Máximo de caracteres de un número de celular
    @Size(min = 0, max = 15, message = "El número de teléfono no puede superar los 15 caracteres")
    private String telefono;

    //
    @Column(length = 255)
    @Size(min = 0, max = 255, message = "La dirección no puede superar los 255 caracteres")
    private String direccion;

    //
    @Column(length = 41) // 36 caracteres por el randomUUID + extension (.jpg, .webp)
    private String imagen;

    //
    private String tipo;

    
    @OneToMany(mappedBy = "usuario")
    private List<Producto> productos;
    
    @OneToMany(mappedBy = "usuario")
    private List<Carrito> carrito;

    @OneToMany(mappedBy = "usuario")
    private List<Compra> compras;

    @OneToMany(mappedBy = "usuario")
    private List<Servicio> servicios;

    @OneToMany(mappedBy = "usuario")
    private List<Cita> citas;


    
//    public Usuario() {
//    }
//
//    public Usuario(Integer id, String nombre, String apellido, String password, String email, String telefono, String direccion, String tipo) {
//        this.id = id;
//        this.nombre = nombre;
//        this.apellido = apellido;
//        this.password = password;
//        this.email = email;
//        this.telefono = telefono;
//        this.direccion = direccion;
//        this.tipo = tipo;
//    }
//
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public String getApellido() {
//        return apellido;
//    }
//
//    public void setApellido(String apellido) {
//        this.apellido = apellido;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getTelefono() {
//        return telefono;
//    }
//
//    public void setTelefono(String telefono) {
//        this.telefono = telefono;
//    }
//
//    public String getDireccion() {
//        return direccion;
//    }
//
//    public void setDireccion(String direccion) {
//        this.direccion = direccion;
//    }
//
//    public String getTipo() {
//        return tipo;
//    }
//
//    public void setTipo(String tipo) {
//        this.tipo = tipo;
//    }
//
//    public List<Producto> getProductos() {
//        return productos;
//    }
//
//    public void setProductos(List<Producto> productos) {
//        this.productos = productos;
//    }
//
//    public List<Carrito> getCarrito() {
//        return carrito;
//    }
//
//    public void setCarrito(List<Carrito> carrito) {
//        this.carrito = carrito;
//    }
//
//    public List<Compra> getCompras() {
//        return compras;
//    }
//
//    public void setCompras(List<Compra> compras) {
//        this.compras = compras;
//    }
}
