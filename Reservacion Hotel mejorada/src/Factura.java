import java.util.ArrayList;
import java.util.List;

public class Factura {
    private List<Reserva> reservas;

    public Factura() {
        this.reservas = new ArrayList<>();
    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public double calcularTotal() {
        double total = 150.0;

        for (Reserva reserva : reservas) {
            Habitacion habitacion = reserva.getHabitacion();
            if (habitacion != null && habitacion.isDisponible()) {
                total += habitacion.getPrecio() * reserva.getDiasEstancia();
            }
        }

        return total;
    }
}
