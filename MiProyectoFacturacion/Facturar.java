package MiProyectoFacturacion;

import java.util.ArrayList;

/**
 * Clase encargada de procesar los productos y generar la factura.
 */
public class Facturar {

    private ArrayList<Producto> productos;
    private double total;

    // Constructor que recibe el carrito
    public Facturar(ArrayList<Producto> productos) {
        this.productos = productos;
        this.total = 0;
    }

    // Calcula total de la factura
    public void procesarFactura() {
        total = 0;
        for (Producto p : productos) {
            total += p.getSubtotal();
        }
    }

    public double getTotal() {
        return total;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    // Generar ticket en formato texto
    public String generarTicket(String metodoPago) {
        StringBuilder sb = new StringBuilder();

        sb.append("==========================================\n");
        sb.append("                 FACTURA\n");
        sb.append("==========================================\n\n");

        for (Producto p : productos) {
            sb.append(String.format(
                "%-15s  x%-5.2f  $%-7.2f  Subtotal: $%-7.2f\n",
                p.getNombre(),
                p.getCantidad(),
                p.getPrecioUnitario(),
                p.getSubtotal()
            ));
        }

        sb.append("\n------------------------------------------\n");
        sb.append(String.format("TOTAL: $%.2f\n", total));
        sb.append("Método de pago: " + metodoPago + "\n");
        sb.append("==========================================\n");

        return sb.toString();
    }
}
