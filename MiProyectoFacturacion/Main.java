package MiProyectoFacturacion;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;

/**
 * Sistema de Facturación Mejorado v2.0
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Producto> cart = new ArrayList<>();

        System.out.println("==========================================");
        System.out.println("        SISTEMA DE FACTURACION v2.0");
        System.out.println("==========================================");

        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Fecha y hora: " + ahora.format(formato));
        System.out.println();

        boolean seguirComprando = true;

        while (seguirComprando) {
            System.out.print("Nombre del producto: ");
            String nombre = scanner.nextLine();

            double cantidad = leerDouble(scanner, "Cantidad: ", "ERROR: La cantidad debe ser un número positivo.");
            double precio = leerDouble(scanner, "Precio unitario: $", "ERROR: El precio debe ser un número positivo.");

            cart.add(new Producto(nombre, cantidad, precio));

            System.out.print("\nAgregar otro producto? (s/n): ");
            seguirComprando = scanner.nextLine().trim().equalsIgnoreCase("s");
            System.out.println();
        }

        int metodoPago = leerEnteroEnRango(scanner,
                "Método de pago (1-Efectivo, 2-Tarjeta, 3-Transferencia): ",
                1, 3,
                "ERROR: Ingrese una opción válida (1-3).");

        String metodo = switch (metodoPago) {
            case 1 -> "Efectivo";
            case 2 -> "Tarjeta";
            case 3 -> "Transferencia";
            default -> "Transferencia";
        };

        // Crear procesador de factura
        Facturar factura = new Facturar(cart);
        factura.procesarFactura();

        // Generar texto del ticket
        String ticket = factura.generarTicket(metodo);

        // Mostrar en consola
        System.out.println(ticket);

        // Mostrar en ventanita Swing
        mostrarFacturaEnVentana(ticket);
    }

    // ==========================
    // MÉTODOS AUXILIARES
    // ==========================

    private static double leerDouble(Scanner scanner, String mensaje, String mensajeError) {
        while (true) {
            try {
                System.out.print(mensaje);
                double valor = Double.parseDouble(scanner.nextLine());
                if (valor > 0) return valor;
                System.out.println(mensajeError);
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Debe ingresar un número válido.");
            }
        }
    }

    private static int leerEnteroEnRango(Scanner scanner, String mensaje, int min, int max, String mensajeError) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = Integer.parseInt(scanner.nextLine());
                if (valor >= min && valor <= max) return valor;
                System.out.println(mensajeError);
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Debe ingresar un número válido.");
            }
        }
    }

    // ==========================
    // VENTANA SWING
    // ==========================

    private static void mostrarFacturaEnVentana(String ticket) {
        JFrame frame = new JFrame("Factura Generada");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea areaTexto = new JTextArea(ticket);
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Consolas", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(areaTexto);
        frame.add(scroll);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
