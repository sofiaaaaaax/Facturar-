package MiProyectoFacturacion;

/**
 * Representa un producto dentro del sistema de facturación.
 */
public class Producto {

    private String nombre;
    private double cantidad;
    private double precioUnitario;

    public Producto(String nombre, double cantidad, double precioUnitario) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public String getNombre() {
        return nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getSubtotal() {
        return cantidad * precioUnitario;
    }
}
