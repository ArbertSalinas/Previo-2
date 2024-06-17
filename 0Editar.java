import java.util.Date;
import java.util.Calendar;

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

// Clase abstracta RegimenPensional
abstract class RegimenPensional {
    public abstract double calcularPension(Trabajador trabajador);
}

// Clase RPM que hereda de RegimenPensional
class RPM extends RegimenPensional {
    @Override
    public double calcularPension(Trabajador trabajador) {
        double porcentajePension;
        if (trabajador.getSemanasCotizadas() < 1300) {
            porcentajePension = 0.65;
        } else {
            int semanasExtras = trabajador.getSemanasCotizadas() - 1300;
            double porcentajeExtra = (semanasExtras / 50) * 0.015;
            porcentajePension = Math.min(0.65 + porcentajeExtra, 0.80);
        }

        double IBL = trabajador.getSalario(); // Simplificación: salario actual como IBL
        return IBL * porcentajePension;
    }
}

// Clase RAIS que hereda de RegimenPensional
class RAIS extends RegimenPensional {
    @Override
    public double calcularPension(Trabajador trabajador) {
        int expectativaVida = 20; // Suponiendo expectativa de vida de 20 años post-jubilación
        return trabajador.getAportesAcumulados() / (expectativaVida * 12);
    }
}

// Clase principal SistemaPensional para gestionar el sistema
public class SistemaPensional {
    public static void main(String[] args) {
        Trabajador trabajador1 = new Trabajador("Juan Perez", "12345678", "dependiente", 2000000, new RPM());
        Trabajador trabajador2 = new Trabajador("Maria Lopez", "87654321", "independiente", 3000000, new RAIS());

        // Afiliación y aportes
        trabajador1.realizarAporte();
        trabajador2.realizarAporte();

        // Simulación de pensión
        double pensionRPM = trabajador1.getRegimen().calcularPension(trabajador1);
        double pensionRAIS = trabajador2.getRegimen().calcularPension(trabajador2);

        System.out.println("Pensión proyectada (RPM) para " + trabajador1.getNombre() + ": " + pensionRPM);
        System.out.println("Pensión proyectada (RAIS) para " + trabajador2.getNombre() + ": " + pensionRAIS);
    }
}
