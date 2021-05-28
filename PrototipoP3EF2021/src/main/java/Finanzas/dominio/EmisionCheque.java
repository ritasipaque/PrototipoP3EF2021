
package Finanzas.dominio;

/**
 *
 * @author Nay Ale
 */
public class EmisionCheque {
    private String Numero_Cheque ;
    private String Fecha_Cheque ; 
    private String FK_Banco ;
    private String FK_Cuenta ;
    private String FK_Cuentahabiente ;
    private String Monto_Cheque;
    
    
    public EmisionCheque(){  
}
    public EmisionCheque(String Numero_Cheque , String Fecha_Cheque , String FK_Banco , String FK_Cuenta , String FK_Cuentahabiente , String Monto_Cheque  ) {
        this.Numero_Cheque = Numero_Cheque;
        this.Fecha_Cheque = Fecha_Cheque;
        this.FK_Banco = FK_Banco;
        this.FK_Cuenta = FK_Cuenta;
        this.FK_Cuentahabiente = FK_Cuentahabiente;
        this.Monto_Cheque = Monto_Cheque;
       
    }

    public String getNumero_Cheque() {
        return Numero_Cheque;
    }

    public void setNumero_Cheque(String Numero_Cheque) {
        this.Numero_Cheque = Numero_Cheque;
    }

    public String getFecha_Cheque() {
        return Fecha_Cheque;
    }

    public void setFecha_Cheque(String Fecha_Cheque) {
        this.Fecha_Cheque = Fecha_Cheque;
    }

    public String getFK_Banco() {
        return FK_Banco;
    }

    public void setFK_Banco(String FK_Banco) {
        this.FK_Banco = FK_Banco;
    }

    public String getFK_Cuenta() {
        return FK_Cuenta;
    }

    public void setFK_Cuenta(String FK_Cuenta) {
        this.FK_Cuenta = FK_Cuenta;
    }

    public String getFK_Cuentahabiente() {
        return FK_Cuentahabiente;
    }

    public void setFK_Cuentahabiente(String FK_Cuentahabiente) {
        this.FK_Cuentahabiente = FK_Cuentahabiente;
    }

    public String getMonto_Cheque() {
        return Monto_Cheque;
    }

    public void setMonto_Cheque(String Monto_Cheque) {
        this.Monto_Cheque = Monto_Cheque;
    }
  

@Override
public String toString() {
     return "EmisionCheque{" + "Numero_Cheque=" + Numero_Cheque + ", Fecha_Cheque=" + Fecha_Cheque + ", FK_Banco=" + FK_Banco + ", FK_Cuenta=" + FK_Cuenta + ", FK_Cuentahabiente=" + FK_Cuentahabiente + ", Monto_Cheque=" + Monto_Cheque + '}'; 
}
}

