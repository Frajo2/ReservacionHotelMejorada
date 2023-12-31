import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SistemaReservasHotel {

    private Factura factura;
    private String nombreCliente;

    public void iniciar(Stage primaryStage) {
        factura = new Factura();

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        Label labelNombre = new Label("Ingrese su nombre:");
        TextField nombreField = new TextField();
        nombreField.setPromptText("Nombre del cliente");

        Label labelHabitacion = new Label("Seleccione el tipo de habitación:");
        ComboBox<String> habitacionComboBox = new ComboBox<>();
        habitacionComboBox.getItems().addAll("Habitación Simple", "Habitación Grande", "Suite Ático");
        habitacionComboBox.setValue("Habitación Simple");

        TextField diasEstanciaField = new TextField();
        diasEstanciaField.setPromptText("Días de estancia");

        Button reservarButton = new Button("Reservar");
        reservarButton.setOnAction(e -> reservar(nombreField.getText(), habitacionComboBox.getValue(), diasEstanciaField.getText()));

        root.getChildren().addAll(labelNombre, nombreField, labelHabitacion, habitacionComboBox, diasEstanciaField, reservarButton);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Sistema de Reservas de Hotel");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void reservar(String nombre, String tipoHabitacion, String diasEstancia) {
        this.nombreCliente = nombre;
        int dias = Integer.parseInt(diasEstancia);
        Habitacion habitacion = crearHabitacion(tipoHabitacion);

        if (habitacion != null) {
            Reserva reserva = new Reserva(habitacion, dias);
            factura.agregarReserva(reserva);
            mostrarFactura();
        } else {
            alerta("No se pudo realizar la reserva.");
        }
    }

    private Habitacion crearHabitacion(String tipoHabitacion) {
        switch (tipoHabitacion) {
            case "Habitación Simple":
                return new HabitacionSimple();
            case "Habitación Grande":
                return new HabitacionGrande();
            case "Suite Ático":
                return new HabitacionSuiteAtico();
            default:
                return null;
        }
    }

    private void mostrarFactura() {
        double total = factura.calcularTotal();
        StringBuilder facturaText = new StringBuilder("----- Factura -----\n");
        facturaText.append("Nombre del cliente: ").append(nombreCliente).append("\n");

        for (Reserva reserva : factura.getReservas()) {
            Habitacion habitacion = reserva.getHabitacion();
            if (habitacion != null && habitacion.isDisponible()) {
                facturaText.append("Tipo de habitación: ").append(habitacion.getTipo()).append("\n");
                facturaText.append("Días de estancia: ").append(reserva.getDiasEstancia()).append("\n");
            }
        }

        facturaText.append("Total a pagar: $").append(total);

        alerta(facturaText.toString());
    }




    private void alerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public Factura getFactura() {
        return factura;
    }
}
