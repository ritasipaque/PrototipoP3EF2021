package seguridad.vista;

import java.net.InetAddress;
import java.net.UnknownHostException;
import seguridad.dominio.Usuario;

/**
 *
 * @author Diego Vásquez
 */
public class MDI_Components {

    public String getIp() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        return address.getHostAddress();
    }
}
