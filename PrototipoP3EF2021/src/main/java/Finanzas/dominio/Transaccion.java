

package Finanzas.dominio;

public class Transaccion {
     private String Codigo_CuentaHabiente;
    private String Balance;
    private String Transaccion;
    private String fecha;

    public Transaccion() {
    }

    public Transaccion(String Codigo_CuentaHabiente, String Balance, String Transaccion, String fecha) {
        this.Codigo_CuentaHabiente = Codigo_CuentaHabiente;
        this.Balance = Balance;
        this.Transaccion = Transaccion;
        this.fecha = fecha;
    }

    public String getCodigo_CuentaHabiente() {
        return Codigo_CuentaHabiente;
    }

    public void setCodigo_CuentaHabiente(String Codigo_CuentaHabiente) {
        this.Codigo_CuentaHabiente = Codigo_CuentaHabiente;
    }

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String Balance) {
        this.Balance = Balance;
    }

    public String getTransaccion() {
        return Transaccion;
    }

    public void setTransaccion(String Transaccion) {
        this.Transaccion = Transaccion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Deposito{" + "Codigo_CuentaHabiente=" + Codigo_CuentaHabiente + ", Balance=" + Balance + ", Transaccion=" + Transaccion + ", fecha=" + fecha + '}';
    }
    

}
