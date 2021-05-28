/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comercial.dominio;






/**
 *
 * @author Carlos Alberto Flores De Paz 
 */
public class Bitacora {
    /**
 *
 *  declaraciones de  variables  de tipo privada
 */
    private  String Id_Bitacora;
    private String Id_Usuario;
    private String Fecha;
    private String Hora;
     private String Host;
        private String Ip;
    private String Accion;
    private String codigoAplicacion;

    public String getHost() {
        return Host;
    }

    public void setHost(String Host) {
        this.Host = Host;
    }

    public String getCodigoAplicacion() {
        return codigoAplicacion;
    }

    public void setCodigoAplicacion(String codigoAplicacion) {
        this.codigoAplicacion = codigoAplicacion;
    }
   

 
/**
 *
 * generacion de  get y set de los variables
 * 
 */
    
   

    public String getId_Bitacora() {
        return Id_Bitacora;
    }

    public void setId_Bitacora(String Id_Bitacora) {
        this.Id_Bitacora = Id_Bitacora;
    }

    public String getId_Usuario() {
        return Id_Usuario;
    }

    public void setId_Usuario(String Id_Usuario) {
        this.Id_Usuario = Id_Usuario;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }



    public String getIp() {
        return Ip;
    }

    public void setIp(String Ip) {
        this.Ip = Ip;
    }

    public String getAccion() {
        return Accion;
    }

    public void setAccion(String Accion) {
        this.Accion = Accion;
    }

    public String getcodigoAplicacion() {
        return codigoAplicacion;
    }

    public void setcodigoAplicacion(String codigoAplicacion) {
        this.codigoAplicacion = codigoAplicacion;
    }

    /**
 *
 *  retorno de de las  variables declaradas
 */
    @Override
    public String toString() {

        return "Bitacora{" + "Id_Bitacora=" + Id_Bitacora + ", Id_Usuario=" + Id_Usuario + ", Fecha=" + Fecha + ", Hora=" + Hora +  ", Ip=" + Ip + ", Accion=" + Accion + ", codigoAplicacion=" + codigoAplicacion +  + '}';

     


    }

   

    
}