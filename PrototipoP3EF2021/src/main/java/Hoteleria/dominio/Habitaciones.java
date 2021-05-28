/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hoteleria.dominio;

/**
 *
 * @author gegmo
 */
public class Habitaciones {
    private int Id_Habitaciones;
    private String Descripcion;
    private String Max_personas;
    private int Precio;
    private int piso;
    private int Estado_Habitacion;
    private String Tipo_Habitacion;

    public int getId_Habitaciones() {
        return Id_Habitaciones;
    }

    public void setId_Habitaciones(int Id_Habitaciones) {
        this.Id_Habitaciones = Id_Habitaciones;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getMax_personas() {
        return Max_personas;
    }

    public void setMax_personas(String Max_personas) {
        this.Max_personas = Max_personas;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int Precio) {
        this.Precio = Precio;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getEstado_Habitacion() {
        return Estado_Habitacion;
    }

    public void setEstado_Habitacion(int Estado_Habitacion) {
        this.Estado_Habitacion = Estado_Habitacion;
    }

    public String getTipo_Habitacion() {
        return Tipo_Habitacion;
    }

    public void setTipo_Habitacion(String Tipo_Habitacion) {
        this.Tipo_Habitacion = Tipo_Habitacion;
    }

}