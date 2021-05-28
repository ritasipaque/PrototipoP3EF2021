/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comercial.dominio;

/**
 *
 * @author SIPAQUE.RITA
 */
public class Cuatro {
    int Id_numero;
    String Fecha_tarjeta;
    String Nombre_tarjeta;
    String Password;

    public int getId_numero() {
        return Id_numero;
    }

    public void setId_numero(int Id_numero) {
        this.Id_numero = Id_numero;
    }

    public String getFecha_tarjeta() {
        return Fecha_tarjeta;
    }

    public void setFecha_tarjeta(String Fecha_tarjeta) {
        this.Fecha_tarjeta = Fecha_tarjeta;
    }

    public String getNombre_tarjeta() {
        return Nombre_tarjeta;
    }

    public void setNombre_tarjeta(String Nombre_tarjeta) {
        this.Nombre_tarjeta = Nombre_tarjeta;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    @Override
    public String toString() {
        return "Cuatro{" + "Id_numero=" + Id_numero + ", Fecha_tarjeta=" + Fecha_tarjeta + ", Nombre_tarjeta=" + Nombre_tarjeta + ", Password=" + Password + '}';
    }
    
    
    
    
}