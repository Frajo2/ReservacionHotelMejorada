public class Habitacion {
    private String tipo;
    private double precio;
    private boolean disponible;

    public Habitacion(String tipo, double precio) {
        this.tipo = tipo;
        this.precio = precio;
        this.disponible = true;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void reservar() {
        disponible = false;
    }
}
