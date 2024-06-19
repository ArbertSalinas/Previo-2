// Clase Trabajador que hereda de Persona
class Trabajador extends Persona {
    private double salario;
    private RegimenPensional regimen;
    private double aportesAcumulados;
    private int semanasCotizadas;
    private Date fechaAfiliacion;

    public Trabajador(String nombre, String identificacion, String tipoTrabajador, double salario, RegimenPensional regimen) {
        super(nombre, identificacion, tipoTrabajador);
        this.salario = salario;
        this.regimen = regimen;
        this.aportesAcumulados = 0;
        this.semanasCotizadas = 0;
        this.fechaAfiliacion = new Date();
    }

    // Método para calcular el aporte mensual
    public double calcularAporteMensual() {
        return this.salario * 0.16;
    }

    // Método para realizar el aporte mensual
    public void realizarAporte() {
        double aporte = calcularAporteMensual();
        this.aportesAcumulados += aporte;
        this.semanasCotizadas += 4; // Suponiendo 4 semanas por mes
    }

    // Getters y Setters
    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    public RegimenPensional getRegimen() { return regimen; }
    public void setRegimen(RegimenPensional regimen) { this.regimen = regimen; }

    public double getAportesAcumulados() { return aportesAcumulados; }
    public void setAportesAcumulados(double aportesAcumulados) { this.aportesAcumulados = aportesAcumulados; }

    public int getSemanasCotizadas() { return semanasCotizadas; }
    public void setSemanasCotizadas(int semanasCotizadas) { this.semanasCotizadas = semanasCotizadas; }

    public Date getFechaAfiliacion() { return fechaAfiliacion; }
    public void setFechaAfiliacion(Date fechaAfiliacion) { this.fechaAfiliacion = fechaAfiliacion; }
}
