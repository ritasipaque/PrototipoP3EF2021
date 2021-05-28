package seguridad.dominio;

public class Aplicacion {
    
    private int Id_Aplicacion;
    private int Id_Modulo;
    private String Nombre_Aplicacion;
    private String Descripcion_Aplicacion;
    private int ReporteAsociado_Aplicacion;
    private int Estado_Aplicacion;
    //private int Id_ModuloCbx;

    /*public int getId_ModuloCbx() {
        return Id_ModuloCbx;
    }*/

    /*public void setId_ModuloCbx(int Id_ModuloCbx) {
        this.Id_ModuloCbx = Id_ModuloCbx;
    }*/

    public int getId_Aplicacion() {
        return Id_Aplicacion;
    }

    public void setId_Aplicacion(int Id_Aplicacion) {
        this.Id_Aplicacion = Id_Aplicacion;
    }

    public int getId_Modulo() {
        return Id_Modulo;
    }

    public void setId_Modulo(int Id_Modulo) {
        this.Id_Modulo = Id_Modulo;
    }

    public String getNombre_Aplicacion() {
        return Nombre_Aplicacion;
    }

    public void setNombre_Aplicacion(String Nombre_Aplicacion) {
        this.Nombre_Aplicacion = Nombre_Aplicacion;
    }

    public String getDescripcion_Aplicacion() {
        return Descripcion_Aplicacion;
    }

    public void setDescripcion_Aplicacion(String Descripcion_Aplicacion) {
        this.Descripcion_Aplicacion = Descripcion_Aplicacion;
    }

    public int getReporteAsociado_Aplicacion() {
        return ReporteAsociado_Aplicacion;
    }

    public void setReporteAsociado_Aplicacion(int ReporteAsociado_Aplicacion) {
        this.ReporteAsociado_Aplicacion = ReporteAsociado_Aplicacion;
    }

    public int getEstado_Aplicacion() {
        return Estado_Aplicacion;
    }

    public void setEstado_Aplicacion(int Estado_Aplicacion) {
        this.Estado_Aplicacion = Estado_Aplicacion;
    }
    
    

    @Override
    public String toString() {
        return "Persona{" + "Id_Aplicacion=" + Id_Aplicacion + ", Id_Modulo=" + Id_Modulo 
                + ", Nombre_Aplicacion=" + Nombre_Aplicacion + ", Descripcion_Aplicacion=" + Descripcion_Aplicacion 
                + ", ReporteAsociado_Aplicacion=" + ReporteAsociado_Aplicacion + ", Estado_Aplicacion=" + Estado_Aplicacion + 
                '}';
    }
    
    
}

