package com.app.servicios.controladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.app.servicios.entidades.Calificacion;
import com.app.servicios.entidades.Usuario;
import com.app.servicios.servicios.CalificacionServicio;
import com.app.servicios.servicios.UsuarioServicio;

@Controller
@RequestMapping("/calificaciones")
public class CalificacionControlador {

    @Autowired
    private CalificacionServicio calificacionServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/listar")
    public String listarCalificaciones(Model model) {
        List<Calificacion> calificaciones = calificacionServicio.listarCalificaciones();
        model.addAttribute("calificaciones", calificaciones);
        return "tablaCalificaciones";
    }

    @GetMapping("/nuevo")
    public String nuevaCalificacion(Model model) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "formularioCalificacion";
    }

    @PostMapping("/guardar")
    public String guardarCalificacion(@RequestParam int puntaje, @RequestParam String comentario,
                                      @RequestParam Usuario usuario, @RequestParam Usuario proveedor, Model model) {
        try {
            Usuario usuario = usuarioServicio.buscarUsuarioPorId(usuarioId);
            Usuario proveedor = usuarioServicio.buscarUsuarioPorId(proveedorId);
            calificacionServicio.crearCalificacion(puntaje, comentario, usuario, proveedor);
            return "redirect:/calificaciones/listar";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "formularioCalificacion";
        }
    }

    @GetMapping("/{id}/editar")
    public String editarCalificacion(@PathVariable Long id, Model model) {
        return "formularioCalificacion";
    }

    @GetMapping("/{id}/eliminar")
    public String eliminarCalificacion(@PathVariable Long id) {
        calificacionServicio.borrarCalificacion(id);
        return "redirect:/calificaciones/listar";
    }

    @GetMapping("/usuario/{id}")
    public String listarCalificacionesPorUsuario(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioServicio.buscarUsuarioPorId(id);
        List<Calificacion> calificaciones = calificacionServicio.listarCalificacionesPorUsuario(usuario);
        model.addAttribute("calificaciones", calificaciones);
        return "tablaCalificaciones";
    }

    @GetMapping("/proveedor/{id}")
    public String listarCalificacionesPorProveedor(@PathVariable Long id, Model model) {
        Usuario proveedor = usuarioServicio.buscarUsuarioPorId(id);
        List<Calificacion> calificaciones = calificacionServicio.listarCalificacionesPorProveedor(proveedor);
        Double promedioPuntaje = calificacionServicio.calcularPromedioPuntajeProveedor(proveedor);
        model.addAttribute("calificaciones", calificaciones);
        model.addAttribute("promedioPuntaje", promedioPuntaje);
        return "tablaCalificaciones";
    }
}