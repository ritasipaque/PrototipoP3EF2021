package Hoteleria.dominio;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author gegmo
 */
public class EntregaHabitaciones {
    private String Pk_id_entrega,Pk_id_reservacion,Pk_id_habitacion,Pk_no_identificacion,Nombre,fecha,estado;

    public String getPk_id_entrega() {
        return Pk_id_entrega;
    }

    public void setPk_id_entrega(String Pk_id_entrega) {
        this.Pk_id_entrega = Pk_id_entrega;
    }

    public String getPk_id_reservacion() {
        return Pk_id_reservacion;
    }

    public void setPk_id_reservacion(String Pk_id_reservacion) {
        this.Pk_id_reservacion = Pk_id_reservacion;
    }

    public String getPk_id_habitacion() {
        return Pk_id_habitacion;
    }

    public void setPk_id_habitacion(String Pk_id_habitacion) {
        this.Pk_id_habitacion = Pk_id_habitacion;
    }

    public String getPk_no_identificacion() {
        return Pk_no_identificacion;
    }

    public void setPk_no_identificacion(String Pk_no_identificacion) {
        this.Pk_no_identificacion = Pk_no_identificacion;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}