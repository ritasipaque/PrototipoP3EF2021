package Hoteleria.dominio;

public class Pisos {

    private int Id_Numero_De_Piso;
    private int Cantidad_De_Habitaciones;
    private String Descripcion_De_Piso;
    private int Estado_De_Piso;

    public int getId_Numero_De_Piso() {
        return Id_Numero_De_Piso;
    }

    public void setId_Numero_De_Piso(int Id_Numero_De_Piso) {
        this.Id_Numero_De_Piso = Id_Numero_De_Piso;
    }

    public int getCantidad_De_Habitaciones() {
        return Cantidad_De_Habitaciones;
    }

    public void setCantidad_De_Habitaciones(int Cantidad_De_Habitaciones) {
        this.Cantidad_De_Habitaciones = Cantidad_De_Habitaciones;
    }

    public String getDescripcion_De_Piso() {
        return Descripcion_De_Piso;
    }

    public void setDescripcion_De_Piso(String Descripcion_De_Piso) {
        this.Descripcion_De_Piso = Descripcion_De_Piso;
    }

    public int getEstado_De_Piso() {
        return Estado_De_Piso;
    }

    public void setEstado_De_Piso(int Estado_De_Piso) {
        this.Estado_De_Piso = Estado_De_Piso;
    }

    @Override
    public String toString() {
        return "Ama de Llaves{"
                + "Id_Numero_De_Piso=" + Id_Numero_De_Piso
                + ", Cantidad_De_Habitaciones=" + Cantidad_De_Habitaciones
                + ", Descripcion_De_Piso=" + Descripcion_De_Piso
                + ", Estado_De_Piso=" + Estado_De_Piso
                + '}';
    }

}
