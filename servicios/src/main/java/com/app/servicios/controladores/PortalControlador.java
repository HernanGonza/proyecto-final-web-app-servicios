package com.app.servicios.controladores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.app.servicios.entidades.Servicio;
import com.app.servicios.entidades.Usuario;
import com.app.servicios.repositorios.ServicioRepositorio;
import com.app.servicios.servicios.ServicioServicios;
import com.app.servicios.servicios.UsuarioServicios;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private ServicioServicios servicioServicios;

    @Autowired 
    private UsuarioServicios usuarioServicios;

    @Autowired
    private ServicioRepositorio servicioRepositorio;

   

    @GetMapping("/")
    public String index(ModelMap modelo) {
        List<Servicio> servicios = servicioServicios.listarServiciosActivos();
        modelo.addAttribute("servicios", servicios);
        return "index.html";
    }

    @GetMapping("/registrarCliente")
    public String registrarCliente() {
        return "formularioCliente.html";
    }

    @PostMapping("/registroCliente")
    public String registrarClientePost(@RequestParam String nombre, 
                                       @RequestParam String apellido, 
                                       @RequestParam String localidad, 
                                       @RequestParam String direccion, 
                                       @RequestParam String barrio, 
                                       @RequestParam String telefono, 
                                       @RequestParam String email, 
                                       @RequestParam String password, 
                                       @RequestParam String password2, 
                                       ModelMap modelo, 
                                       @RequestParam MultipartFile archivo) throws Exception {
        try {
            usuarioServicios.crearCliente(nombre, apellido, direccion, localidad, barrio, telefono, email, password, password2, archivo);
            return "login.html";
        } catch (Exception ex) {
            modelo.put("error", ex.getMessage());
            return "redirect:/registrarCliente";
        }
    }

    @GetMapping("/registrarProveedor")
    public String registrarProveedor(ModelMap modelo) {
        List<Servicio> servicios = servicioServicios.listarServiciosActivos();
        modelo.addAttribute("servicios", servicios);
        return "formularioProveedor.html";
    }

    @PostMapping("/registroProveedor")
    public String registrarProveedorPost(@RequestParam String email,
                                         @RequestParam String nombre,
                                         @RequestParam String apellido,
                                         @RequestParam Integer dni,
                                         @RequestParam String direccion,
                                         @RequestParam String localidad,
                                         @RequestParam List<String> serviciosIds,
                                         @RequestParam String descripcion,
                                         @RequestParam String telefono,
                                         @RequestParam MultipartFile archivo,
                                         @RequestParam Integer experiencia,
                                         @RequestParam String password,
                                         @RequestParam String password2,
                                         ModelMap modelo) {
        try {
            Set<Servicio> servicios = new HashSet<>();
            for (String servicioId : serviciosIds) {
                Servicio servicio = servicioRepositorio.findById(servicioId).orElse(null);
                if (servicio != null) {
                    servicios.add(servicio);
                }
            }
            usuarioServicios.crearProveedor(nombre, apellido, direccion, localidad, telefono, email, password, password2, archivo, dni, experiencia, descripcion, servicios);
            return "login.html";
        } catch (Exception ex) {
            modelo.put("error", ex.getMessage());
            return "redirect:/registrarProveedor";
        }
    }

    @GetMapping("/registarClienteProveedor/{id}")
    public String registrarClienteProveedor(@PathVariable String id, ModelMap modelo) {
        List<Servicio> servicios = servicioServicios.listarServiciosActivos();
        modelo.addAttribute("servicios", servicios);
        return "registrarClienteProveedor.html";
    }

    @PostMapping("/registroClienteProveedor/{id}")
    public String registrarClienteProveedorPost(@PathVariable String id,
                                                @RequestParam Integer dni, 
                                                @RequestParam Integer experiencia, 
                                                @RequestParam String descripcion, 
                                                @RequestParam List<String> serviciosIds, 
                                                ModelMap modelo) {
        try {
            Set<Servicio> servicios = new HashSet<>();
            for (String servicioId : serviciosIds) {
                Servicio servicio = servicioRepositorio.findById(servicioId).orElse(null);
                if (servicio != null) {
                    servicios.add(servicio);
                }
            }
            usuarioServicios.crearClienteProveedor(experiencia, descripcion, dni, servicios, id);
            return "inicio.html";
        } catch (Exception ex) {
            modelo.put("error", ex.getMessage());
            return "redirect:/registrarProveedor";
        }
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "Email o contraseña incorrectos");
        }
        return "index.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROVEEDOR', 'ROLE_CLIENTEPROVEEDOR', 'ROLE_CLIENTE', 'ROLE_SUPERADMIN')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session, ModelMap modelo) {
       
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        
        if (logueado.getRol().toString().equals("ADMIN")) {
            return "redirect:/admin";
        }
        if (logueado.getRol().toString().equals("SUPERADMIN")) {
            return "redirect:/superadmin";
        }
        modelo.put("exito", "Bienvenido " + logueado.getNombre());
       
        return "inicio.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROVEEDOR', 'ROLE_CLIENTEPROVEEDOR', 'ROLE_CLIENTE', 'ROLE_SUPERADMIN')")
    @GetMapping("/panelUsuario")
    public String panelUsuario(HttpSession session, ModelMap modelo) {
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        if (logueado.getRol().toString().equals("ADMIN")) {
            return "redirect:/admin";
        }
        if (logueado.getRol().toString().equals("SUPERADMIN")) {
            return "redirect:/superadmin";
        }
        return "panelUsuario.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROVEEDOR', 'ROLE_CLIENTEPROVEEDOR', 'ROLE_CLIENTE', 'ROLE_SUPERADMIN')")
    @GetMapping("/proveedores")
    public String vistaProveedores(@RequestParam String servicio, ModelMap modelo) {
        List<Usuario> proveedores = usuarioServicios.listarPorServicio(servicio);
        modelo.addAttribute("proveedores", proveedores);
        return "resultado-busqueda.html";
    }

   

    @GetMapping("/proveedores/{nombreServicio}")
    public String mostrarCarpinteria(@PathVariable String nombreServicio, ModelMap modelo) {

        System.out.println("nombreServicio: " + nombreServicio);
        
        Servicio servicio = new Servicio();

        servicio = servicioServicios.buscarServicioPorNombre(nombreServicio);

        System.out.println("servicio: " + servicio.toString());

        String id = servicio.getId();
        
        System.out.println("id: " + id);

        List<Usuario> proveedores = usuarioServicios.listarPorServicio(id);
        
        // Crear una lista de listas de nombres de servicios
        List<List<String>> listaDeNombresDeServicios = new ArrayList<>();
        
        for (Usuario proveedor : proveedores) {
            List<String> nombresServicios = proveedor.getServicios().stream()
                                                     .map(Servicio::getNombre) // Suponiendo que tienes un método getNombre en tu entidad Servicio
                                                     .collect(Collectors.toList());
            listaDeNombresDeServicios.add(nombresServicios);
        }
    
        modelo.addAttribute("proveedores", proveedores);
        modelo.addAttribute("servicio", id);
        modelo.addAttribute("listaDeNombresDeServicios", listaDeNombresDeServicios);
    
        return "vistaProveedorPorServicio.html";
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROVEEDOR', 'ROLE_CLIENTEPROVEEDOR', 'ROLE_CLIENTE', 'ROLE_SUPERADMIN')")
    @GetMapping("/perfil")
    public String perfil(HttpSession session, ModelMap modelo) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        modelo.put("usuario", usuario);
        return "perfilUsuario.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROVEEDOR', 'ROLE_CLIENTEPROVEEDOR', 'ROLE_CLIENTE', 'ROLE_SUPERADMIN')")
    @GetMapping("/actualizarPerfil/{id}")
    public String actualizarPerfil(HttpSession session, ModelMap modelo) {
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        if (logueado.getRol().toString().equals("PROVEEDOR")) {
            return "redirect:/actualizarProveedor";
        }
        if (logueado.getRol().toString().equals("CLIENTEPROVEEDOR")) {
            return "redirect:/actualizarClienteProveedor";
        }
        return "actualizarCliente.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLIENTE', 'ROLE_SUPERADMIN')")
    @PostMapping("/perfil/{id}")
    public String modificarCliente(@RequestParam String nombre, 
                                @RequestParam String apellido, 
                                @RequestParam String direccion, 
                                @RequestParam String localidad, 
                                @RequestParam String barrio, 
                                @RequestParam String telefono, 
                                @RequestParam String email, 
                                @RequestParam String password, 
                                @RequestParam String password2,
                                MultipartFile archivo,
                                @RequestParam String id,
                                ModelMap modelo) {

        try {
            usuarioServicios.modificarCliente(nombre, apellido, direccion, localidad, barrio, telefono, email, password, password2, archivo, id);
            return "redirect:/";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "error.html";
        }
    }
}


