/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hoteleria.dominio;

/**
 *
 * @author Jeff
 */
public class Facturacion {

    private int Id_Factura;
    private int Id_Reservacion;
    private String Nombre_Factura;
    private String FechaEntrada_Factura;
    private String FechaSalida_Factura;
    private String FormaPago_Factura;
    private int NoTarjeta_Factura;
//    private int Cvv_Factura;
//    private String Caducidad_Factura;
    private int TotalReservacion_Factura;
    private int TotalServicios_Factura;
    private int TotalFactura_Factura;
    private int Estado_Factura;

    public int getId_Factura() {
        return Id_Factura;
    }

    public void setId_Factura(int Id_Factura) {
        this.Id_Factura = Id_Factura;
    }

    public int getId_Reservacion() {
        return Id_Reservacion;
    }

    public void setId_Reservacion(int Id_Reservacion) {
        this.Id_Reservacion = Id_Reservacion;
    }

    public String getNombre_Factura() {
        return Nombre_Factura;
    }

    public void setNombre_Factura(String Nombre_Factura) {
        this.Nombre_Factura = Nombre_Factura;
    }

    public String getFechaEntrada_Factura() {
        return FechaEntrada_Factura;
    }

    public void setFechaEntrada_Factura(String FechaEntrada_Factura) {
        this.FechaEntrada_Factura = FechaEntrada_Factura;
    }

    public String getFechaSalida_Factura() {
        return FechaSalida_Factura;
    }

    public void setFechaSalida_Factura(String FechaSalida_Factura) {
        this.FechaSalida_Factura = FechaSalida_Factura;
    }

    public String getFormaPago_Factura() {
        return FormaPago_Factura;
    }

    public void setFormaPago_Factura(String FormaPago_Factura) {
        this.FormaPago_Factura = FormaPago_Factura;
    }

    public int getNoTarjeta_Factura() {
        return NoTarjeta_Factura;
    }

    public void setNoTarjeta_Factura(int NoTarjeta_Factura) {
        this.NoTarjeta_Factura = NoTarjeta_Factura;
    }

//    public int getCvv_Factura() {
//        return Cvv_Factura;
//    }
//
//    public void setCvv_Factura(int Cvv_Factura) {
//        this.Cvv_Factura = Cvv_Factura;
//    }
//
//    public String getCaducidad_Factura() {
//        return Caducidad_Factura;
//    }
//
//    public void setCaducidad_Factura(String Caducidad_Factura) {
//        this.Caducidad_Factura = Caducidad_Factura;
//    }

    public int getTotalReservacion_Factura() {
        return TotalReservacion_Factura;
    }

    public void setTotalReservacion_Factura(int TotalReservacion_Factura) {
        this.TotalReservacion_Factura = TotalReservacion_Factura;
    }

    public int getTotalServicios_Factura() {
        return TotalServicios_Factura;
    }

    public void setTotalServicios_Factura(int TotalServicios_Factura) {
        this.TotalServicios_Factura = TotalServicios_Factura;
    }

    public int getTotalFactura_Factura() {
        return TotalFactura_Factura;
    }

    public void setTotalFactura_Factura(int TotalFactura_Factura) {
        this.TotalFactura_Factura = TotalFactura_Factura;
    }

    public int getEstado_Factura() {
        return Estado_Factura;
    }

    public void setEstado_Factura(int Estado_Factura) {
        this.Estado_Factura = Estado_Factura;
    }

    @Override
    public String toString() {
        return "Facturacion{"
                + "Id_Factura=" + Id_Factura
                + ", Id_Reservacion=" + Id_Reservacion
                + ", Nombre_Factura=" + Nombre_Factura
                + ", FechaEntrada_Factura=" + FechaEntrada_Factura
                + ", FechaSalida_Factura=" + FechaSalida_Factura
                + ", FormaPago_Factura=" + FormaPago_Factura
                + ", NoTarjeta_Factura=" + NoTarjeta_Factura
//                + ", Cvv_Factura=" + Cvv_Factura
//                + ", Caducidad_Factura=" + Caducidad_Factura
                + "TotalReservacion_Factura=" + TotalReservacion_Factura
                + ", TotalServicios_Factura=" + TotalServicios_Factura
                + ", TotalFactura_Factura=" + TotalFactura_Factura
                + ", Estado_Factura=" + Estado_Factura
                + '}';
    }
}
