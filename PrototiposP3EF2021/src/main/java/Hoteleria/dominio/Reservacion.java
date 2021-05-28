package Hoteleria.dominio;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author leone
 */
public class Reservacion {
    private String id_reservacion, dpi, f_reserva, desde, hasta, id_habitacion, precio, estado, cantidadpersonas;

    public String getId_reservacion() {
        return id_reservacion;
    }

    public void setId_reservacion(String id_reservacion) {
        this.id_reservacion = id_reservacion;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getF_reserva() {
        return f_reserva;
    }

    public void setF_reserva(String f_reserva) {
        this.f_reserva = f_reserva;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public String getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(String id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCantidadpersonas() {
        return cantidadpersonas;
    }

    public void setCantidadpersonas(String cantidadpersonas) {
        this.cantidadpersonas = cantidadpersonas;
    }
}
