

package Finanzas.dominio;

/**
 * 
 * @author  Santiago Martinez Diaz
 */
public class TipoTransaccion {
    private String Codigo_TipoTransaccion;
    private String Transaccion_Tipo;
    private int Efecto_TipoTransaccion;

    public TipoTransaccion() {
    }

    public TipoTransaccion(String Codigo_TipoTransaccion, String Transaccion_Tipo, int Efecto_TipoTransaccion) {
        this.Codigo_TipoTransaccion = Codigo_TipoTransaccion;
        this.Transaccion_Tipo = Transaccion_Tipo;
        this.Efecto_TipoTransaccion = Efecto_TipoTransaccion;
    }

    public String getCodigo_TipoTransaccion() {
        return Codigo_TipoTransaccion;
    }

    public void setCodigo_TipoTransaccion(String Codigo_TipoTransaccion) {
        this.Codigo_TipoTransaccion = Codigo_TipoTransaccion;
    }

    public String getTransaccion_Tipo() {
        return Transaccion_Tipo;
    }

    public void setTransaccion_Tipo(String Transaccion_Tipo) {
        this.Transaccion_Tipo = Transaccion_Tipo;
    }

    public int getEfecto_TipoTransaccion() {
        return Efecto_TipoTransaccion;
    }

    public void setEfecto_TipoTransaccion(int Efecto_TipoTransaccion) {
        this.Efecto_TipoTransaccion = Efecto_TipoTransaccion;
    }

    @Override
    public String toString() {
        return "TipoTransaccion{" + "Codigo_TipoTransaccion=" + Codigo_TipoTransaccion + ", Transaccion_Tipo=" + Transaccion_Tipo + ", Efecto_TipoTransaccion=" + Efecto_TipoTransaccion + '}';
    }
    
    

}
