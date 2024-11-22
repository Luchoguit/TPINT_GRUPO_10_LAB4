package utilidades;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Formato {
    public static String formatoMonetario(BigDecimal valor) {
        if (valor == null) {
            return "$0.00"; // Manejo de valores nulos
        }
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));
        return formatter.format(valor);
    }
  
    public static String formatoFecha(LocalDateTime fecha) {
        if (fecha == null) {
            return "";
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.toLocalDate().format(dateFormatter);
    }

    public static String formatoFechaHora(LocalDateTime fecha) {
        if (fecha == null) {
            return "";
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return fecha.format(dateTimeFormatter);
    }
}
