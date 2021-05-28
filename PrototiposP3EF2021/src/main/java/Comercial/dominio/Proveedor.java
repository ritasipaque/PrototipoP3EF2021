/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comercial.dominio;

/**
 *
 * @author Rita  Sipaque
 */
public class Proveedor {
    
   int PK_id_proveedor;
  String nombre_proveedor; 
  String direccion_proveedor; 
  String contacto_proveedor;
  String telefono_proveedor;
  String nit_proveedor; 
  String email_proveedor;
 String estatus_proveedor; 

    public int getPK_id_proveedor() {
        return PK_id_proveedor;
    }

    public void setPK_id_proveedor(int PK_id_proveedor) {
        this.PK_id_proveedor = PK_id_proveedor;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public String getDireccion_proveedor() {
        return direccion_proveedor;
    }

    public void setDireccion_proveedor(String direccion_proveedor) {
        this.direccion_proveedor = direccion_proveedor;
    }

    public String getContacto_proveedor() {
        return contacto_proveedor;
    }

    public void setContacto_proveedor(String contacto_proveedor) {
        this.contacto_proveedor = contacto_proveedor;
    }

    public String getTelefono_proveedor() {
        return telefono_proveedor;
    }

    public void setTelefono_proveedor(String telefono_proveedor) {
        this.telefono_proveedor = telefono_proveedor;
    }

    public String getNit_proveedor() {
        return nit_proveedor;
    }

    public void setNit_proveedor(String nit_proveedor) {
        this.nit_proveedor = nit_proveedor;
    }

    public String getEmail_proveedor() {
        return email_proveedor;
    }

    public void setEmail_proveedor(String email_proveedor) {
        this.email_proveedor = email_proveedor;
    }

    public String getEstatus_proveedor() {
        return estatus_proveedor;
    }

    public void setEstatus_proveedor(String estatus_proveedor) {
        this.estatus_proveedor = estatus_proveedor;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "PK_id_proveedor=" + PK_id_proveedor + ", nombre_proveedor=" + nombre_proveedor + ", direccion_proveedor=" + direccion_proveedor + ", contacto_proveedor=" + contacto_proveedor + ", telefono_proveedor=" + telefono_proveedor + ", nit_proveedor=" + nit_proveedor + ", email_proveedor=" + email_proveedor + ", estatus_proveedor=" + estatus_proveedor + '}';
    }


  
  
}
