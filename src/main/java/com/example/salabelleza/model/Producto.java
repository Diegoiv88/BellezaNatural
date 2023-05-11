package com.example.salabelleza.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import java.util.List;


@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "productos")
@NoArgsConstructor
public class Producto 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //
    @Column(length = 255)
    @Size(max = 255, message = "El nombre del producto debe tener maximo 255 caracteres")
    private String nombre;

    //
    @Column(length = 2048)
    @Size(max = 2048, message = "La descripción del producto debe tener meximo 2048 caracteres")
    private String descripcion;

    //
    @Column(length = 41) // 36 caracteres por el randomUUID + extension (.jpg, .webp)
    private String imagen;

    //
    @NotNull(message = "Debes ingresar un precio")
    @NumberFormat(pattern = "#,###.##", style = Style.CURRENCY)
    @Min(value = 1, message = "El precio debe ser mayor a $1")
    private double precio;

    //
    @NotNull(message = "Debes ingresar la cantidad de stock del producto")
    @Min(value = 1, message = "La cantidad de stock no debe ser menor a 1")
    private int cantidad;
    
    //
    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "producto")
    private List<Valoracion> valoraciones;


//    public Producto() {
//    }
//
//    public Producto(Integer id, String nombre, String descripcion, String imagen, double precio, int cantidad, Usuario usuario) {
//        this.id = id;
//        this.nombre = nombre;
//        this.descripcion = descripcion;
//        this.imagen = imagen;
//        this.precio = precio;
//        this.cantidad = cantidad;
//        this.usuario = usuario;
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
//    public String getDescripcion() {
//        return descripcion;
//    }
//
//    public void setDescripcion(String descripcion) {
//        this.descripcion = descripcion;
//    }
//
//    public String getImagen() {
//        return imagen;
//    }
//
//    public void setImagen(String imagen) {
//        this.imagen = imagen;
//    }
//
//    public double getPrecio() {
//        return precio;
//    }
//
//    public void setPrecio(double precio) {
//        this.precio = precio;
//    }
//
//    public int getCantidad() {
//        return cantidad;
//    }
//
//    public void setCantidad(int cantidad) {
//        this.cantidad = cantidad;
//    }
//
//    public Usuario getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }
//
//
    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", imagen=" + imagen + ", precio=" + precio + ", cantidad=" + cantidad + '}';
    }
}
