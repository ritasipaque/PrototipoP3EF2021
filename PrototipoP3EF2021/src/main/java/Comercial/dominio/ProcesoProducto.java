/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comercial.dominio;

/**
 *
 * @author Diana
 */
public class ProcesoProducto {

    int PK_id_procesoproducto;
    String nombre_producto;
    String nombre_bodega;
    String existencias_producto;
    String fechaActualizacion;
    String ProductoNuevo;
    String NuevaExistencia;
    String BodegasNuevaExistencia;

    public int getPK_id_procesoproducto() {
        return PK_id_procesoproducto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public String getNombre_bodega() {
        return nombre_bodega;
    }

    public String getExistencias_producto() {
        return existencias_producto;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public String getProductoNuevo() {
        return ProductoNuevo;
    }

    public String getNuevaExistencia() {
        return NuevaExistencia;
    }

    public String getBodegasNuevaExistencia() {
        return BodegasNuevaExistencia;
    }

    public void setPK_id_procesoproducto(int PK_id_procesoproducto) {
        this.PK_id_procesoproducto = PK_id_procesoproducto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public void setNombre_bodega(String nombre_bodega) {
        this.nombre_bodega = nombre_bodega;
    }

    public void setExistencias_producto(String existencias_producto) {
        this.existencias_producto = existencias_producto;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public void setProductoNuevo(String ProductoNuevo) {
        this.ProductoNuevo = ProductoNuevo;
    }

    public void setNuevaExistencia(String NuevaExistencia) {
        this.NuevaExistencia = NuevaExistencia;
    }

    public void setBodegasNuevaExistencia(String BodegasNuevaExistencia) {
        this.BodegasNuevaExistencia = BodegasNuevaExistencia;
    }

    
}
