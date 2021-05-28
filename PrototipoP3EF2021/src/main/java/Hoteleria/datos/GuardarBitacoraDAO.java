package Hoteleria.datos;

import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import seguridad.datos.BitacoraDao;
import seguridad.dominio.Bitacora;
import seguridad.vista.Aplicacion_Perfil;

/**
 *
 * @author Leonel
 */
public class GuardarBitacoraDAO {

    public void GuardarEnBitacora(String accion, String codigoModulo, String idUsuario) {
        BitacoraDao BitacoraDAO = new BitacoraDao();
        Bitacora AInsertar = new Bitacora();
        boolean estado = false;
        switch (accion) {
            case "Insertar":
                AInsertar.setId_Usuario(idUsuario);
                AInsertar.setAccion("Inserción");
                AInsertar.setCodigoAplicacion(codigoModulo);
                AInsertar.setModulo("2000");
                estado = true;
                break;
            case "Modificacion":
                AInsertar.setId_Usuario(idUsuario);
                AInsertar.setAccion("Modificación");
                AInsertar.setCodigoAplicacion(codigoModulo);
                AInsertar.setModulo("2000");
                estado = true;
                break;
            case "Eliminacion":
                AInsertar.setId_Usuario(idUsuario);
                AInsertar.setAccion("Eliminación");
                AInsertar.setCodigoAplicacion(codigoModulo);
                AInsertar.setModulo("2000");
                estado = true;
                break;
            case "Acceso":
                AInsertar.setId_Usuario(idUsuario);
                AInsertar.setAccion("Acceso");
                AInsertar.setCodigoAplicacion(codigoModulo);
                AInsertar.setModulo("2000");
                estado = true;
                break;
            case "Salir":
                AInsertar.setId_Usuario(idUsuario);
                AInsertar.setAccion("CERRAR SESIÓN");
                AInsertar.setCodigoAplicacion(codigoModulo);
                AInsertar.setModulo("2000");
                estado = true;
                break;
        }
        if (estado == true) {
            try {
                BitacoraDAO.insert(AInsertar);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Aplicacion_Perfil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
