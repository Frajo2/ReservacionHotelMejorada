public class Reserva {
    private Habitacion habitacion;
    private String nombreCliente;
    private int diasEstancia;

    public Reserva(Habitacion habitacion, String nombreCliente, int diasEstancia) {
        this.habitacion = habitacion;
        this.nombreCliente = nombreCliente;
        this.diasEstancia = diasEstancia;
    }

    public Reserva(Habitacion habitacion, int dias) {
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public int getDiasEstancia() {
        return diasEstancia;
    }
}
