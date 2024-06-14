package com.app.servicios.servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.servicios.entidades.Calificacion;
import com.app.servicios.entidades.Usuario;
import com.app.servicios.excepciones.MiExcepcion;
import com.app.servicios.repositorios.CalificacionRepositorio;

@Service
public class CalificacionServicio {

    @Autowired
    private CalificacionRepositorio calificacionRepositorio;

    public void validarCalificacion(int puntaje, String comentario) throws MiExcepcion {
        if (puntaje < 1 || puntaje > 5) {
            throw new MiExcepcion("El puntaje debe estar entre 1 y 5");
        }
        if (comentario == null || comentario.isEmpty()) {
            throw new MiExcepcion("El comentario no puede estar vacío");
        }
    }

    @Transactional
    public void crearCalificacion(int puntaje, String comentario, Usuario usuario, Usuario proveedor) throws MiExcepcion {
        validarCalificacion(puntaje, comentario);
        Calificacion calificacion = new Calificacion();
        calificacion.setPuntaje(puntaje);
        calificacion.setComentario(comentario);
        calificacion.setUsuario(usuario);
        calificacion.setProveedor(proveedor);
        calificacionRepositorio.save(calificacion);
    }

    @Transactional(readOnly = true)
    public List<Calificacion> listarCalificaciones() {
        return calificacionRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public List<Calificacion> listarCalificacionesPorUsuario(Usuario usuario) {
        return calificacionRepositorio.buscarCalificacionesPorUsuario(usuario);
    }

    @Transactional(readOnly = true)
    public List<Calificacion> listarCalificacionesPorProveedor(Usuario proveedor) {
        return calificacionRepositorio.buscarCalificacionesPorProveedor(proveedor);
    }

    @Transactional(readOnly = true)
    public Double calcularPromedioPuntajeProveedor(Usuario proveedor) {
        return calificacionRepositorio.calcularPromedioPuntajeProveedor(proveedor);
    }

    @Transactional
    public void actualizarCalificacion(Long id, int puntaje, String comentario) throws MiExcepcion {
        validarCalificacion(puntaje, comentario);
        Calificacion calificacion = calificacionRepositorio.findById(id).orElse(null);
        if (calificacion != null) {
            calificacion.setPuntaje(puntaje);
            calificacion.setComentario(comentario);
            calificacionRepositorio.save(calificacion);
        }
    }

    @Transactional
    public void borrarCalificacion(Long id) {
        calificacionRepositorio.deleteById(id);
    }
}