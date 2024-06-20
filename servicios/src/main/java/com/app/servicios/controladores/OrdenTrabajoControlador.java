package com.app.servicios.controladores;

import com.app.servicios.entidades.OrdenTrabajo;
import com.app.servicios.excepciones.MiExcepcion;
import com.app.servicios.servicios.OrdenTrabajoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordenes-trabajo")
public class OrdenTrabajoControlador {

    @Autowired
    private OrdenTrabajoServicios ordenTrabajoServicios;

    // Crea una nueva orden de trabajo
    @PostMapping("/crear")
    public ResponseEntity<String> crearOrdenTrabajo(@RequestBody OrdenTrabajo ordenTrabajo) {
        try {
            ordenTrabajoServicios.crearOrdenTrabajo(
                ordenTrabajo.getTitulo(),
                ordenTrabajo.getProveedor(),
                ordenTrabajo.getCliente(),
                ordenTrabajo.getServicios(),
                ordenTrabajo.getDescripcion(),
                ordenTrabajo.getImagen(),
                ordenTrabajo.getVideo()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body("Orden de trabajo creada exitosamente");
        } catch (MiExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Obtiene todas las órdenes de trabajo
    @GetMapping("/listar")
    public ResponseEntity<List<OrdenTrabajo>> listarOrdenesTrabajo() {
        List<OrdenTrabajo> ordenes = ordenTrabajoServicios.listarOrdenesTrabajo();
        return ResponseEntity.ok(ordenes);
    }

    // Obtiene las órdenes de trabajo de un cliente específico
    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<OrdenTrabajo>> listarOrdenesPorCliente(@PathVariable String id) {
        List<OrdenTrabajo> ordenes = ordenTrabajoServicios.listarOrdenesTrabajoPorCliente(id);
        return ResponseEntity.ok(ordenes);
    }

    // Obtiene las órdenes de trabajo de un proveedor específico
    @GetMapping("/proveedor/{id}")
    public ResponseEntity<List<OrdenTrabajo>> listarOrdenesPorProveedor(@PathVariable String id) {
        List<OrdenTrabajo> ordenes = ordenTrabajoServicios.listarOrdenesTrabajoPorProveedor(id);
        return ResponseEntity.ok(ordenes);
    }

    // Agrega un presupuesto a una orden de trabajo
    @PutMapping("/presupuestar/{id}")
    public ResponseEntity<String> presupuestarOrdenTrabajo(
            @PathVariable String id,
            @RequestParam Integer presupuesto,
            @RequestParam String comentarioPresupuesto) {
        try {
            ordenTrabajoServicios.presupuestarOrdenTrabajo(presupuesto, comentarioPresupuesto, id);
            return ResponseEntity.ok("Orden de trabajo presupuestada exitosamente");
        } catch (MiExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Acepta el presupuesto de una orden de trabajo
    @PutMapping("/aceptar-presupuesto/{id}")
    public ResponseEntity<String> aceptarPresupuestoOrdenTrabajo(@PathVariable String id) {
        try {
            ordenTrabajoServicios.aceptaPresupuestoOrdenTrabajo(id);
            return ResponseEntity.ok("Presupuesto aceptado exitosamente");
        } catch (MiExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Marca una orden de trabajo como terminada
    @PutMapping("/terminar-trabajo/{id}")
    public ResponseEntity<String> terminarTrabajoOrdenTrabajo(@PathVariable String id) {
        try {
            ordenTrabajoServicios.terminaTrabajoOrdenTrabajo(id);
            return ResponseEntity.ok("Trabajo terminado exitosamente");
        } catch (MiExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Cancela una orden de trabajo
    @PutMapping("/cancelar-trabajo/{id}")
    public ResponseEntity<String> cancelarTrabajoOrdenTrabajo(@PathVariable String id) {
        try {
            ordenTrabajoServicios.cancelaTrabajoOrdenTrabajo(id);
            return ResponseEntity.ok("Trabajo cancelado exitosamente");
        } catch (MiExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Rechaza el presupuesto de una orden de trabajo
    @PutMapping("/rechazar-presupuesto/{id}")
    public ResponseEntity<String> rechazarPresupuestoOrdenTrabajo(@PathVariable String id) {
        try {
            ordenTrabajoServicios.rechazaPresupuestoOrdenTrabajo(id);
            return ResponseEntity.ok("Presupuesto rechazado exitosamente");
        } catch (MiExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Rechaza el trabajo realizado en una orden de trabajo
    @PutMapping("/rechazar-trabajo/{id}")
    public ResponseEntity<String> rechazarTrabajoOrdenTrabajo(@PathVariable String id) {
        try {
            ordenTrabajoServicios.rechazaTrabajoOrdenTrabajo(id);
            return ResponseEntity.ok("Trabajo rechazado exitosamente");
        } catch (MiExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Califica una orden de trabajo
    @PutMapping("/calificar/{id}")
    public ResponseEntity<String> calificarOrdenTrabajo(@PathVariable String id) {
        try {
            ordenTrabajoServicios.calificarOrdenTrabajo(id);
            return ResponseEntity.ok("Orden de trabajo calificada exitosamente");
        } catch (MiExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Desactiva una orden de trabajo
    @PutMapping("/desactivar/{id}")
    public ResponseEntity<String> desactivarOrdenTrabajo(@PathVariable String id) {
        try {
            ordenTrabajoServicios.desactivarOrdenTrabajo(id);
            return ResponseEntity.ok("Orden de trabajo desactivada exitosamente");
        } catch (MiExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Activa una orden de trabajo previamente desactivada
    @PutMapping("/activar/{id}")
    public ResponseEntity<String> activarOrdenTrabajo(@PathVariable String id) {
        try {
            ordenTrabajoServicios.activarOrdenTrabajo(id);
            return ResponseEntity.ok("Orden de trabajo activada exitosamente");
        } catch (MiExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}