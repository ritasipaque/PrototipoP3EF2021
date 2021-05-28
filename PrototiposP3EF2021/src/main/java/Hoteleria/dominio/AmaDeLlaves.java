package Hoteleria.dominio;

public class AmaDeLlaves {

    private int Id_Ama_De_Llaves;
    private String Nombre_Ama_De_Llaves;
    private String Apellido_Ama_De_Llaves;
    private int Piso_Ama_De_Llaves;
    private String Entrada_Ama_De_Llaves;
    private String Salida_Ama_De_Llaves;
    private String Inicio_Ama_De_Llaves;
    private String Descripcion_Ama_De_Llaves;
    private int Estado_Ama_De_Llaves;

    public int getId_Ama_De_Llaves() {
        return Id_Ama_De_Llaves;
    }

    public void setId_Ama_De_Llaves(int Id_Ama_De_Llaves) {
        this.Id_Ama_De_Llaves = Id_Ama_De_Llaves;
    }

    public String getNombre_Ama_De_Llaves() {
        return Nombre_Ama_De_Llaves;
    }

    public void setNombre_Ama_De_Llaves(String Nombre_Ama_De_Llaves) {
        this.Nombre_Ama_De_Llaves = Nombre_Ama_De_Llaves;
    }

    public String getApellido_Ama_De_Llaves() {
        return Apellido_Ama_De_Llaves;
    }

    public void setApellido_Ama_De_Llaves(String Apellido_Ama_De_Llaves) {
        this.Apellido_Ama_De_Llaves = Apellido_Ama_De_Llaves;
    }

    public int getPiso_Ama_De_Llaves() {
        return Piso_Ama_De_Llaves;
    }

    public void setPiso_Ama_De_Llaves(int Piso_Ama_De_Llaves) {
        this.Piso_Ama_De_Llaves = Piso_Ama_De_Llaves;
    }

    public String getEntrada_Ama_De_Llaves() {
        return Entrada_Ama_De_Llaves;
    }

    public void setEntrada_Ama_De_Llaves(String Entrada_Ama_De_Llaves) {
        this.Entrada_Ama_De_Llaves = Entrada_Ama_De_Llaves;
    }

    public String getSalida_Ama_De_Llaves() {
        return Salida_Ama_De_Llaves;
    }

    public void setSalida_Ama_De_Llaves(String Salida_Ama_De_Llaves) {
        this.Salida_Ama_De_Llaves = Salida_Ama_De_Llaves;
    }

    public String getInicio_Ama_De_Llaves() {
        return Inicio_Ama_De_Llaves;
    }

    public void setInicio_Ama_De_Llaves(String Inicio_Ama_De_Llaves) {
        this.Inicio_Ama_De_Llaves = Inicio_Ama_De_Llaves;
    }

    public String getDescripcion_Ama_De_Llaves() {
        return Descripcion_Ama_De_Llaves;
    }

    public void setDescripcion_Ama_De_Llaves(String Descripcion_Ama_De_Llaves) {
        this.Descripcion_Ama_De_Llaves = Descripcion_Ama_De_Llaves;
    }

    public int getEstado_Ama_De_Llaves() {
        return Estado_Ama_De_Llaves;
    }

    public void setEstado_Ama_De_Llaves(int Estado_Ama_De_Llaves) {
        this.Estado_Ama_De_Llaves = Estado_Ama_De_Llaves;
    }

    @Override
    public String toString() {
        return "Ama de Llaves{"
                + "Id_Ama_De_Llaves=" + Id_Ama_De_Llaves
                + ", Nombre_Ama_De_Llaves=" + Nombre_Ama_De_Llaves
                + ", Apellido_Ama_De_Llaves=" + Apellido_Ama_De_Llaves
                + ", Piso_Ama_De_Llaves=" + Piso_Ama_De_Llaves
                + ", Entrada_Ama_De_Llaves=" + Entrada_Ama_De_Llaves
                + ", Salida_Ama_De_Llaves=" + Salida_Ama_De_Llaves
                + ", Inicio_Ama_De_Llaves=" + Inicio_Ama_De_Llaves
                + ", Descripcion_Ama_De_Llaves=" + Descripcion_Ama_De_Llaves
                + ", Estado_Ama_De_Llaves=" + Estado_Ama_De_Llaves
                + '}';
    }

}
