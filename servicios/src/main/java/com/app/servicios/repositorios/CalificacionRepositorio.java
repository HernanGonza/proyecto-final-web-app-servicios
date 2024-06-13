package com.app.servicios.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.servicios.entidades.Servicio;
import com.app.servicios.entidades.Usuario;

@Repository
public interface CalificacionRepositorio extends JpaRepository<Servicio, Long> {

    // Buscar calificaciones por ID de usuario
    List<Servicio> findByUsuarioId(Usuario usuario);

    // Buscar calificaciones por ID de proveedor
    List<Servicio> findByProveedorId(Usuario proveedor);

    // Buscar calificaciones por puntaje
    List<Servicio> findByPuntaje(int puntaje);

    // Buscar calificaciones por puntaje mayor que
    List<Servicio> findByPuntajeGreaterThan(int puntaje);

    // Buscar calificaciones por puntaje menor que
    List<Servicio> findByPuntajeLessThan(int puntaje);

    // Buscar calificaciones por usuario y proveedor
    List<Servicio> findByUsuarioIdAndProveedorId(Usuario usuario, Usuario proveedor);

    // Contar el número de calificaciones para un proveedor
    long countByProveedorId(Usuario proveedor);

    // Eliminar calificaciones por ID de usuario
    void deleteByUsuarioId(Usuario usuario);
}