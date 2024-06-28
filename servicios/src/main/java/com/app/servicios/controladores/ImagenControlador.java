package com.app.servicios.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.app.servicios.servicios.ImagenServicios;
import com.app.servicios.servicios.UsuarioServicios;
import com.app.servicios.entidades.Imagen;
import com.app.servicios.entidades.Usuario;
import com.app.servicios.repositorios.UsuarioRepositorio;

@Controller
@RequestMapping("/imagen")

public class ImagenControlador {

    @Autowired
    UsuarioServicios usuarioServicios;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    ImagenServicios imagenServicios;
    

    @RequestMapping("/perfil/{id}")
    public ResponseEntity<byte[]> imagenUsuario(@PathVariable String id){
        Usuario usuario =usuarioRepositorio.findById(id).orElse(null);
        if (usuario != null && usuario.getImagen() != null) {
        byte[] imagen = usuario.getImagen().getContenido();
        HttpHeaders headers = new HttpHeaders(); 
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<> (imagen, headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/modificar/{id}")
    public String mostrarFormularioImagen(@PathVariable String id, ModelMap modelo) {
        modelo.put("id", id);
        return "formularioImagen.html";
    }

    @PostMapping("/modificado/{id}")
    public String modificarImagen(@PathVariable String id,
                                    @RequestParam MultipartFile archivo,
                                    ModelMap modelo) {
        try {
            Imagen imagen = imagenServicios.actualizarImagen(archivo, id);
            Usuario usuario = usuarioRepositorio.findById(id).orElse(null);
            if (usuario != null) {
                usuario.setImagen(imagen);
                usuarioRepositorio.save(usuario);
            }
            return "redirect:/inicio";
        } catch (Exception ex) {
            modelo.put("error", ex.getMessage());
            return "redirect:/imagen/modificar/" + id;
        }
    }
}
