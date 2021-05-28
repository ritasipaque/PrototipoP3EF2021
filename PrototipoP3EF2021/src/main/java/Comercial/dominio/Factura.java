package Comercial.dominio;


import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PERSONAL
 */
public class Factura  {
    
String Id_cliente;
String Cliente;  
String Nit;  
String Telefono; 
String producto; 
String Cantidad;  
String  Precio_por_unidad; 
String Monto; 
String TotalmoNto;
String productoB; 
String Existencia;
String tipo; 

String numero;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }


    public String getProductoB() {
        return productoB;
    }

    public void setProductoB(String productoB) {
        this.productoB = productoB;
    }

    public String getExistencia() {
        return Existencia;
    }

    public void setExistencia(String Existencia) {
        this.Existencia = Existencia;
    }

    public String getId_cliente() {
        return Id_cliente;
    }

    public void setId_cliente(String Id_cliente) {
        this.Id_cliente = Id_cliente;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }

    public String getNit() {
        return Nit;
    }

    public void setNit(String Nit) {
        this.Nit = Nit;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String Cantidad) {
        this.Cantidad = Cantidad;
    }

    public String getPrecio_por_unidad() {
        return Precio_por_unidad;
    }

    public void setPrecio_por_unidad(String Precio_por_unidad) {
        this.Precio_por_unidad = Precio_por_unidad;
    }

    public String getMonto() {
        return Monto;
    }

    public void setMonto(String Monto) {
        this.Monto = Monto;
    }

    public String getTotalmoNto() {
        return TotalmoNto;
    }

    public void setTotalmoNto(String TotalmoNto) {
        this.TotalmoNto = TotalmoNto;
    }

    @Override
    public String toString() {
        return "Factura{" + "Id_cliente=" + Id_cliente + ", Cliente=" + Cliente + ", Nit=" + Nit + ", Telefono=" + Telefono + ", producto=" + producto + ", Cantidad=" + Cantidad + ", Precio_por_unidad=" + Precio_por_unidad + ", Monto=" + Monto + ", TotalmoNto=" + TotalmoNto + '}';
    }

    
    
 
   
}
