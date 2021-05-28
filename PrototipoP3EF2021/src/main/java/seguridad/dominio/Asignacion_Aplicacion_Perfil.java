package seguridad.dominio;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leelu
 */
public class Asignacion_Aplicacion_Perfil {
    private String Codigo_Aplicacion;
    private String Codigo_Perfil;
    private String Ingresar;
    private String Consultar;
    private String Modificar;
    private String Eliminar;
    private String Imprimir;
    
    public String getCodigo_Aplicacion() {
        return Codigo_Aplicacion;
    }
    public void setCodigo_Aplicacion(String Codigo_Aplicacion) {
        this.Codigo_Aplicacion = Codigo_Aplicacion;
    }

    public String getCodigo_Perfil() {
        return Codigo_Perfil;
    }
    public void setCodigo_Perfil(String Codigo_Perfil) {
        this.Codigo_Perfil = Codigo_Perfil;
    }
    
    public String toStrig(){
        return String.valueOf(Codigo_Aplicacion)+ Codigo_Perfil;
    }
    
    public String getIngresar() {
        return Ingresar;
    }

    public void setIngresar(String Ingresar) {
        this.Ingresar = Ingresar;
    }

    public String getConsultar() {
        return Consultar;
    }

    public void setConsultar(String Consultar) {
        this.Consultar = Consultar;
    }

    public String getModificar() {
        return Modificar;
    }

    public void setModificar(String Modificar) {
        this.Modificar = Modificar;
    }

    public String getEliminar() {
        return Eliminar;
    }

    public void setEliminar(String Eliminar) {
        this.Eliminar = Eliminar;
    }

    public String getImprimir() {
        return Imprimir;
    }

    public void setImprimir(String Imprimir) {
        this.Imprimir = Imprimir;
    }
}
