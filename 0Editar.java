import java.util.Date;
import java.util.Calendar;



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
