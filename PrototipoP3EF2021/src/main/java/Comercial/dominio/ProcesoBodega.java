/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comercial.dominio;

import Comercial.datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Diana
 */
public class ProcesoBodega {

    int PK_id_procesobodega;
    String nombre_producto;
    String descripcion_producto;
    String fechaIngreso;
    String fechaSalida;

    public int getPK_id_procesobodega() {
        return PK_id_procesobodega;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setPK_id_procesobodega(int PK_id_procesobodega) {
        this.PK_id_procesobodega = PK_id_procesobodega;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    
}
