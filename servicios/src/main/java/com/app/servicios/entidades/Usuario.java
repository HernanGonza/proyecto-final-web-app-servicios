package com.app.servicios.entidades;

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Basic
    private String email;
    private String password;
    private String nombre;
    private String apellido;
    private String direccion;
    private String localidad;
    private String barrio;
    private Integer telefono;
    private String imagen;

    // atributos adicionales para proveedor
    @ManyToMany
    @JoinTable(name = "proveedor_servicio", joinColumns = @JoinColumn(name = "proveedor_id"), inverseJoinColumns = @JoinColumn(name = "servicio_id"))
    private Set<Servicio> servicios = new HashSet<>();
    private Integer dni;
    private Integer experiencia;
    private String descripcion;

    
    
}
