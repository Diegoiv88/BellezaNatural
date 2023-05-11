    package com.example.salabelleza.model;

    import lombok.Getter;
    import lombok.Setter;

    import javax.persistence.*;

    @Getter
    @Setter
    @Entity
    @Table(name = "Valoracion")
    public class Valoracion {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private Integer stars;

        private String comment;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "usuario_id")
        private Usuario usuario;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "producto_id")
        private Producto producto;
    }
