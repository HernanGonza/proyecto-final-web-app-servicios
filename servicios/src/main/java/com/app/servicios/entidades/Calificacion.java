package com.app.servicios.entidades;

<<<<<<< HEAD
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
=======
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
>>>>>>> developer
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

<<<<<<< HEAD
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Calificacion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int puntaje;
    private String comentario; 

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario proveedor;


    }

=======
@SuppressWarnings("deprecation")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Calificacion {
    

@Id
    @GeneratedValue (generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private Integer puntaje;

    private String comentario;

    @ManyToOne
    private Usuario cliente;

    @ManyToOne
    private Usuario proveedor;
}
>>>>>>> developer
