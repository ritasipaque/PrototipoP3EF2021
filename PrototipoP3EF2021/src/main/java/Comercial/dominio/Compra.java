/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comercial.dominio;

/**
 *
 * @author SipaqueRitaMaria
 */
public class Compra {
    int Id_factura;
String Id_proveedor;
String Nombre_proveedor;
String Nit_proveedor;
String Id_producto;
String Nombre_producto;
String Precio_producto;
String Cantidad_producto;
String Subtotal_producto;
String Total_producto;
String Fecha_producto;
String Pago_producto;

    public int getId_factura() {
        return Id_factura;
    }

    public void setId_factura(int Id_factura) {
        this.Id_factura = Id_factura;
    }

    public String getId_proveedor() {
        return Id_proveedor;
    }

    public void setId_proveedor(String Id_proveedor) {
        this.Id_proveedor = Id_proveedor;
    }

    public String getNombre_proveedor() {
        return Nombre_proveedor;
    }

    public void setNombre_proveedor(String Nombre_proveedor) {
        this.Nombre_proveedor = Nombre_proveedor;
    }

    public String getNit_proveedor() {
        return Nit_proveedor;
    }

    public void setNit_proveedor(String Nit_proveedor) {
        this.Nit_proveedor = Nit_proveedor;
    }

    public String getId_producto() {
        return Id_producto;
    }

    public void setId_producto(String Id_producto) {
        this.Id_producto = Id_producto;
    }

    public String getNombre_producto() {
        return Nombre_producto;
    }

    public void setNombre_producto(String Nombre_producto) {
        this.Nombre_producto = Nombre_producto;
    }

    public String getPrecio_producto() {
        return Precio_producto;
    }

    public void setPrecio_producto(String Precio_producto) {
        this.Precio_producto = Precio_producto;
    }

    public String getCantidad_producto() {
        return Cantidad_producto;
    }

    public void setCantidad_producto(String Cantidad_producto) {
        this.Cantidad_producto = Cantidad_producto;
    }

    public String getSubtotal_producto() {
        return Subtotal_producto;
    }

    public void setSubtotal_producto(String Subtotal_producto) {
        this.Subtotal_producto = Subtotal_producto;
    }

    public String getTotal_producto() {
        return Total_producto;
    }

    public void setTotal_producto(String Total_producto) {
        this.Total_producto = Total_producto;
    }

    public String getFecha_producto() {
        return Fecha_producto;
    }

    public void setFecha_producto(String Fecha_producto) {
        this.Fecha_producto = Fecha_producto;
    }

    public String getPago_producto() {
        return Pago_producto;
    }

    public void setPago_producto(String Pago_producto) {
        this.Pago_producto = Pago_producto;
    }

}