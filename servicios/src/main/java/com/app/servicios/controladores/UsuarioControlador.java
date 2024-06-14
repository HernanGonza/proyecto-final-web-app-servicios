// package com.app.servicios.controladores;

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
