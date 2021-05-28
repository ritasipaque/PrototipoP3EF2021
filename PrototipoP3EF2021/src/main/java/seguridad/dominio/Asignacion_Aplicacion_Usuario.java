/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad.dominio;

/**
 *
 * @author gegmo
 */
public class Asignacion_Aplicacion_Usuario {

    private String Codigo_Usuario;
    private String Codigo_Aplicacion;
    private int Ingresar;
    private String Consultar;
    private String Modificar;
    private String Eliminar;
    private String Imprimir;

    public String getCodigo_Usuario() {
        return Codigo_Usuario;
    }

    public void setCodigo_Usuario(String Codigo_Usuario) {
        this.Codigo_Usuario = Codigo_Usuario;
    }

    public String getCodigo_Aplicacion() {
        return Codigo_Aplicacion;
    }

    public void setCodigo_Aplicacion(String Codigo_Aplicacion) {
        this.Codigo_Aplicacion = Codigo_Aplicacion;
    }

    public int getIngresar() {
        return Ingresar;
    }

    public void setIngresar(int Ingresar) {
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
