// Clase base Persona
class Persona {
    private String nombre;
    private String identificacion;
    private String tipoTrabajador;

    public Persona(String nombre, String identificacion, String tipoTrabajador) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.tipoTrabajador = tipoTrabajador;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }

    public String getTipoTrabajador() { return tipoTrabajador; }
    public void setTipoTrabajador(String tipoTrabajador) { this.tipoTrabajador = tipoTrabajador; }
}

