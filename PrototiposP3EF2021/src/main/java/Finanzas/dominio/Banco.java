/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Finanzas.dominio;

/**
 *
 * @author Nay Ale
 */
public class Banco {

   private String Codigo_Banco;
   private String Nombre_Banco;
   private String Clave_Banco;
   private String Telefono_Banco;
          
    public Banco() {
    }
    
    public Banco(String Codigo_Banco, String Nombre_Banco, String Clave_Banco, String Telefono_Banco) {
        this.Codigo_Banco = Codigo_Banco;
        this.Nombre_Banco = Nombre_Banco;
        this.Clave_Banco = Clave_Banco;
        this.Telefono_Banco = Telefono_Banco;
    }
    
    public String getCodigo_Banco() {
        return Codigo_Banco;
    }

    
    public void setCodigo_Banco(String Codigo_Banco) {
        this.Codigo_Banco = Codigo_Banco;
    }

    public String getNombre_Banco() {
        return Nombre_Banco;
    }

    public void setNombre_Banco(String Nombre_Banco) {
        this.Nombre_Banco = Nombre_Banco;
    }

    public String getClave_Banco() {
        return Clave_Banco;
    }

    public void setClave_Banco(String Clave_Banco) {
        this.Clave_Banco = Clave_Banco;
    }

    public String getTelefono_Banco() {
        return Telefono_Banco;
    }

    public void setTelefono_Banco(String Telefono_Banco) {
        this.Telefono_Banco = Telefono_Banco;
    }
    
    
   
   @Override
     public String toString() {
    return "Banco{" + "Codigo_Banco=" + Codigo_Banco + ", Nombre_Banco=" + Nombre_Banco + ", Clave_Banco=" + Clave_Banco + ", Telefono_Banco=" + Telefono_Banco+'}';
    }
    
}
