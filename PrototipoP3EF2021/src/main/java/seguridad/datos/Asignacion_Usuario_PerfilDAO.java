/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import seguridad.dominio.Asignacion_Usuario_Perfil;

/**
 *
 * @author Santiago Martinez
 */
public class Asignacion_Usuario_PerfilDAO {
    
    //Dos propiedades con objetos del tipo String las cuales contienen consultas de base de datos de seleccionar e insertar. 
    private static final String SQL_SELECT = "SELECT PK_id_usuario, PK_id_perfil FROM tbl_usuario_perfil";
    private static final String SQL_INSERT = "INSERT INTO tbl_usuario_perfil(PK_id_usuario, PK_id_perfil) VALUES(?,?)";

    //Método para listar todos las asignaciones usuario perfil de la base de datos
    public List<Asignacion_Usuario_Perfil> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Asignacion_Usuario_Perfil usuario1 = null;
        List<Asignacion_Usuario_Perfil> usuarios = new ArrayList<Asignacion_Usuario_Perfil>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
               
                
                String usuario = rs.getString("PK_id_usuario");
                String perfil = rs.getString("PK_id_perfil");
                usuario1 = new Asignacion_Usuario_Perfil();
                usuario1.setPK_id_usuario(usuario);   
                usuario1.setPK_id_perfil(perfil);
                            
                usuarios.add(usuario1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return usuarios;
    }

   // Método para insertar las asignaciones en la base de datos 
    public int insert(Asignacion_Usuario_Perfil Asignacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            
           stmt.setString(1, Asignacion.getPK_id_usuario());
           stmt.setString(2, Asignacion.getPK_id_perfil());

           
            rows = stmt.executeUpdate();
           JOptionPane.showMessageDialog(null, "Registro exitoso");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }


}