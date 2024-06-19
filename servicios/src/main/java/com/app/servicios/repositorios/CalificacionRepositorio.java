package com.app.servicios.repositorios;

<<<<<<< HEAD
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.servicios.entidades.Calificacion;
import com.app.servicios.entidades.Usuario;

@Repository
public interface CalificacionRepositorio extends JpaRepository<Calificacion, Long> {

    @Query("SELECT c FROM Calificacion c WHERE c.usuario = :usuario")
    public List<Calificacion> buscarCalificacionesPorUsuario(@Param("usuario") Usuario usuario);

    @Query("SELECT c FROM Calificacion c WHERE c.proveedor = :proveedor")
    public List<Calificacion> buscarCalificacionesPorProveedor(@Param("proveedor") Usuario proveedor);

    @Query("SELECT AVG(c.puntaje) FROM Calificacion c WHERE c.proveedor = :proveedor")
    public Double calcularPromedioPuntajeProveedor(@Param("proveedor") Usuario proveedor);
}
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.servicios.entidades.Calificacion;

@Repository
public interface CalificacionRepositorio extends JpaRepository<Calificacion, String> {

    
}
>>>>>>> developer
