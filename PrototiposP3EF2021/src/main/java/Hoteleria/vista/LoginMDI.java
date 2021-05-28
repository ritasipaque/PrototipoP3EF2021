/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hoteleria.vista;

import static Hoteleria.vista.MDIHoteleria.jDesktopPane1;
import java.awt.Dimension;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author leone
 */
public class LoginMDI {

    public static String getIp() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        return address.getHostAddress();
    }
    
    

}
