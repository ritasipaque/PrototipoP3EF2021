/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Finanzas.dominio;

/**
 * 
 * @author Santiago Martinez Diaz
 */
public class CuentaBancaria {
    private String Numero_CuentaBancaria;
    private String Moneda_Cuenta;
    private String CuentaHabiente_Cuenta;
    private String Banco_Cuenta;
    private String Saldo_Cuenta;

    public CuentaBancaria() {
    }

    public CuentaBancaria(String Numero_CuentaBancaria, String Moneda_Cuenta, String CuentaHabiente_Cuenta, String Banco_Cuenta, String Saldo_Cuenta) {
        this.Numero_CuentaBancaria = Numero_CuentaBancaria;
        this.Moneda_Cuenta = Moneda_Cuenta;
        this.CuentaHabiente_Cuenta = CuentaHabiente_Cuenta;
        this.Banco_Cuenta = Banco_Cuenta;
        this.Saldo_Cuenta = Saldo_Cuenta;
    }

    public String getNumero_CuentaBancaria() {
        return Numero_CuentaBancaria;
    }

    public void setNumero_CuentaBancaria(String Numero_CuentaBancaria) {
        this.Numero_CuentaBancaria = Numero_CuentaBancaria;
    }

    public String getMoneda_Cuenta() {
        return Moneda_Cuenta;
    }

    public void setMoneda_Cuenta(String Moneda_Cuenta) {
        this.Moneda_Cuenta = Moneda_Cuenta;
    }

    public String getCuentaHabiente_Cuenta() {
        return CuentaHabiente_Cuenta;
    }

    public void setCuentaHabiente_Cuenta(String CuentaHabiente_Cuenta) {
        this.CuentaHabiente_Cuenta = CuentaHabiente_Cuenta;
    }

    public String getBanco_Cuenta() {
        return Banco_Cuenta;
    }

    public void setBanco_Cuenta(String Banco_Cuenta) {
        this.Banco_Cuenta = Banco_Cuenta;
    }

    public String getSaldo_Cuenta() {
        return Saldo_Cuenta;
    }

    public void setSaldo_Cuenta(String Saldo_Cuenta) {
        this.Saldo_Cuenta = Saldo_Cuenta;
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" + "Numero_CuentaBancaria=" + Numero_CuentaBancaria + ", Moneda_Cuenta=" + Moneda_Cuenta + ", CuentaHabiente_Cuenta=" + CuentaHabiente_Cuenta + ", Banco_Cuenta=" + Banco_Cuenta + ", Saldo_Cuenta=" + Saldo_Cuenta + '}';
    }

 
}
