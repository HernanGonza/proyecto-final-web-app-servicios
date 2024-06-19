<<<<<<< HEAD
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
=======
// package com.app.servicios.controladores;

<<<<<<<< HEAD:servicios/src/main/java/com/app/servicios/controladores/UsuarioControlador.java
// import java.util.Optional;
// import org.apache.catalina.connector.Response;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.HttpStatusCode;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import com.app.servicios.entidades.Usuario;
// import com.app.servicios.repositorios.UsuarioRepositorio;
// import com.app.servicios.servicios.UsuarioServicio;

// @RestController
// @RequestMapping("/servicios")
// public class UsuarioControlador {

//     @Autowired
//     private UsuarioRepositorio usuarioRepositorio;

//     @GetMapping
//     public Lits<Usuario> obtenerTodosLosUsuarios(){
//         return UsuarioServicio.findAll();
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id){
//         Optional<Usuario> usuario = UsuarioServicio.FindById(id);
//         return usuario.map(ResponseEntity::ok)
//         .orElseGet(() -> ResponseEntity.notFound().build());
//     }

//     @PostMapping
//     public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario){
//         Usuario usuarioGuardado = UsuarioServicio.save(usuario);
//         return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardado);
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<Usuario> guardar(@PathVariable Long id, @RequestBody Usuario usuario) {
//         if (usuarioExistente.isPresent()){
//             usuario.setId(id);
//             Usuario usuarioActualizado = UsuarioServicio.save(usuario);
//             return ResponseEntity.ok(usuarioActualizado);
//         } else {
//             return ResponseEntity.notFound().build();
//         }
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id){
//         Optional<Usuario> usuario = UsuarioServicio.FindById(id);
//         if (usuario.isPresent()) {
//             usuarioRepositorio.deleteById(id);
//             return ResponseEntity.noContent().build();
//         } else {
//             return ResponseEntity.notFound().build();
//         }
//     }

// }
========
public class CalificacionControlador {
    
}
>>>>>>>> developer:servicios/src/main/java/com/app/servicios/controladores/CalificacionControlador.java
>>>>>>> developer
