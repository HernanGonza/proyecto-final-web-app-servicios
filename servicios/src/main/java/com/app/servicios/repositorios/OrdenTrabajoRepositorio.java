package com.app.servicios.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.servicios.entidades.OrdenTrabajo;
import com.app.servicios.enumeraciones.EstadoOrden;
import com.app.servicios.enumeraciones.EstadoTrabajo;

@Repository
public interface OrdenTrabajoRepositorio extends JpaRepository<OrdenTrabajo, String> {
    
    @Query("SELECT o FROM OrdenTrabajo o WHERE o.cliente.id = :id")
    public List<OrdenTrabajo> buscarOrdenPorCliente(@Param("id") String id);
    @Query ("SELECT o FROM OrdenTrabajo o WHERE o.proveedor.id = :id")
    public List<OrdenTrabajo> buscarOrdenPorProveedor(@Param("id") String id);
    @Query ("SELECT o FROM OrdenTrabajo o WHERE o.estadoOrden = :estadoOrden")
    public List<OrdenTrabajo> buscarPorEstadoOrden(@Param("estadoOrden") EstadoOrden estadoOrden);
    @Query ("SELECT o FROM OrdenTrabajo o WHERE o.estadoTrabajo = :estadoTrabajo")
    public List<OrdenTrabajo> buscarPorEstadoTrabajo(@Param("estadoTrabajo") EstadoTrabajo estadoTrabajo);
    @Query ("SELECT o FROM OrdenTrabajo o WHERE o.estado = :estado")
    public List<OrdenTrabajo> buscarPorEstado(@Param("estado") Boolean estado);



    


    //Query para buscar ordenes ABIERTO PRESUPUESTAR de clientes//
    @Query ("SELECT ot FROM OrdenTrabajo ot WHERE ot.cliente = :idCliente AND ot.estadoOrden = 'ABIERTO' AND ot.estadoTrabajo = 'PRESUPUESTAR'")
    public List<OrdenTrabajo> buscarOrdenesAbiertosPresupuestarCliente(@Param("idCliente") String idCliente);

    //Query para buscar ordenes ABIERTO PRESUPUESTAR de proveedores//
    @Query ("SELECT ot FROM OrdenTrabajo ot WHERE ot.proveedor = :idProveedor AND ot.estadoOrden = 'ABIERTO' AND ot.estadoTrabajo = 'PRESUPUESTAR'")
    public List<OrdenTrabajo> buscarOrdenesAbiertosPresupuestarProveedor(@Param("idProveedor") String idProveedor);

    //Query para buscar ordenes ABIERTO PRESUPUESTADO de clientes//
    @Query ("SELECT ot FROM OrdenTrabajo ot WHERE ot.cliente = :idCliente AND ot.estadoTrabajo = 'PRESUPUESTADO' AND ot.estadoOrden = 'ABIERTO'")
    public List<OrdenTrabajo> buscarOrdenesPresupuestadasCliente(@Param("idCliente") String idCliente);

    //Query para buscar ordenes ABIERTO PRESUPUESTADO de proveedor//
    @Query ("SELECT ot FROM OrdenTrabajo ot WHERE ot.proveedor = :idProveedor AND ot.estadoTrabajo = 'PRESUPUESTADO' AND ot.estadoOrden = 'ABIERTO'")
    public List<OrdenTrabajo> buscarOrdenesPresupuestadasProveedor(@Param("idProveedor") String idProveedor);

    //Query para buscar ordenes ABIERTO ACEPTADO de clientes//
    @Query ("SELECT ot FROM OrdenTrabajo ot WHERE ot.cliente = :idCliente AND ot.estadoTrabajo = 'ACEPTADO' AND ot.estadoOrden = 'ABIERTO'")
    public List<OrdenTrabajo> buscarOrdenesAceptadasCliente(@Param("idCliente") String idCliente);

    //Query para buscar ordenes ABIERTO ACEPTADO de proveedor//
    @Query ("SELECT ot FROM OrdenTrabajo ot WHERE ot.proveedor = :idProveedor AND ot.estadoTrabajo = 'ACEPTADO' AND ot.estadoOrden = 'ABIERTO'")
    public List<OrdenTrabajo> buscarOrdenesAceptadasProveedor(@Param("idProveedor") String idProveedor);

    //Query para buscar ordenes CERRADO CANCELADO de clientes//
    @Query ("SELECT ot FROM OrdenTrabajo ot WHERE ot.cliente = :idCliente AND ot.estadoTrabajo = 'CANCELADO' AND ot.estadoOrden = 'CERRADO'")
    public List<OrdenTrabajo> buscarOrdenesCanceladasCliente(@Param("idCliente") String idCliente);

    //Query para buscar ordenes CERRADO CANCELADO de proveedor//
    @Query ("SELECT ot FROM OrdenTrabajo ot WHERE ot.proveedor = :idProveedor AND ot.estadoTrabajo = 'CANCELADO' AND ot.estadoOrden = 'CERRADO'")
    public List<OrdenTrabajo> buscarOrdenesCanceladasProveedor(@Param("idProveedor") String idProveedor);

    // Query para buscar ordenes CERRADO PRESUPUESTO_RECHAZADO de clientes//
    @Query ("SELECT ot FROM OrdenTrabajo ot WHERE ot.cliente = :idCliente AND ot.estadoTrabajo = 'PRESUPUESTO_RECHAZADO' AND ot.estadoOrden = 'CERRADO'")
    public List<OrdenTrabajo> buscarOrdenesPresupuestadasRechazadasCliente(@Param("idCliente") String idCliente);

    // Query para buscar ordenes CERRADO PRESUPUESTO_RECHAZADO de proveedor//
    @Query ("SELECT ot FROM OrdenTrabajo ot WHERE ot.proveedor = :idProveedor AND ot.estadoTrabajo = 'PRESUPUESTO_RECHAZADO' AND ot.estadoOrden = 'CERRADO'")
    public List<OrdenTrabajo> buscarOrdenesPresupuestadasRechazadasProveedor(@Param("idProveedor") String idProveedor);

    // Query para buscar ordenes CERRADO TRABAJO_RECHAZADO de clientes//
    @Query ("SELECT ot FROM OrdenTrabajo ot WHERE ot.cliente = :idCliente AND ot.estadoTrabajo = 'TRABAJO_RECHAZADO' AND ot.estadoOrden = 'CERRADO'")
    public List<OrdenTrabajo> buscarOrdenesTrabajoRechazadasCliente(@Param("idCliente") String idCliente);

    // Query para buscar ordenes CERRADO TRABAJO_RECHAZADO de proveedor//
    @Query ("SELECT ot FROM OrdenTrabajo ot WHERE ot.proveedor = :idProveedor AND ot.estadoTrabajo = 'TRABAJO_RECHAZADO' AND ot.estadoOrden = 'CERRADO'")
    public List<OrdenTrabajo> buscarOrdenesTrabajoRechazadasProveedor(@Param("idProveedor") String idProveedor);

    // Query para buscar ordenes FINALIZADO CALIFICAR de clientes//
    @Query ("SELECT ot FROM OrdenTrabajo ot WHERE ot.cliente = :idCliente AND ot.estadoTrabajo = 'FINALIZADO' AND ot.estadoOrden = 'CALIFICAR'")
    public List<OrdenTrabajo> buscarOrdenesCalificarCliente(@Param("idCliente") String idCliente);

    // Query para buscar ordenes FINALIZADO CALIFICAR de proveedor//
    @Query ("SELECT ot FROM OrdenTrabajo ot WHERE ot.proveedor = :idProveedor AND ot.estadoTrabajo = 'FINALIZADO' AND ot.estadoOrden = 'CALIFICAR'")
    public List<OrdenTrabajo> buscarOrdenesCalificarProveedor(@Param("idProveedor") String idProveedor);

    // Query para buscar ordenes CALIFICADO FINALIZADO de clientes//
    @Query ("SELECT ot FROM OrdenTrabajo ot WHERE ot.cliente = :idCliente AND ot.estadoTrabajo = 'FINALIZADO' AND ot.estadoOrden = 'CALIFICADO'")
    public List<OrdenTrabajo> buscarOrdenesFinalizadasCliente(@Param("idCliente") String idCliente);

    // Query para buscar ordenes CALIFICADO FINALIZADO de proveedor//
    @Query ("SELECT ot FROM OrdenTrabajo ot WHERE ot.proveedor = :idProveedor AND ot.estadoTrabajo = 'FINALIZADO' AND ot.estadoOrden = 'CALIFICADO'")
    public List<OrdenTrabajo> buscarOrdenesFinalizadasProveedor(@Param("idProveedor") String idProveedor);

}
