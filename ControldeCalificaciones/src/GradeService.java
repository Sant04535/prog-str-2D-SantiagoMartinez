public class GradeService {


    public double calcularPromedio(double a, double b, double c) {
        return (a + b + c) / 3.0;
    }

    public double calcularFinal(double promedio, int asistencia) {
        return (promedio * 0.7) + (asistencia * 0.3);
    }

    public String determinarEstado(double finalCalificacion, int asistencia, boolean entregoProyecto) {

        if (asistencia < 80) {
            return "REPROBADO por asistencia";
        }

        if (!entregoProyecto) {
            return "REPROBADO por proyecto";
        }

        if (finalCalificacion >= 70) {
            return "APROBADO";
        } else {
            return "REPROBADO por calificación";
        }
    }
}
