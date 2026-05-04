package com.codequalitysolutions;

/**
 * Punto de entrada principal de la aplicación de gestión de inventario.
 *
 * @author TUS_INICIALES
 * @version 1.0
 */
public class Main {

    /**
     * Método principal que demuestra el uso del sistema de inventario.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        Inventario inventario = new Inventario();

        // Añadir productos de ejemplo
        inventario.agregarProducto(new Producto(1, "Teclado Mecánico", 10, 89.99));
        inventario.agregarProducto(new Producto(2, "Monitor 24\"", 5, 249.99));
        inventario.agregarProducto(new Producto(3, "Ratón Inalámbrico", 20, 34.50));

        // Mostrar inventario
        System.out.println("=== INVENTARIO ACTUAL ===");
        for (Producto p : inventario.getProductos()) {
            System.out.println(p);
        }

        // Calcular valor total
        System.out.printf("%nValor total del inventario: %.2f €%n", inventario.calcularValorTotal());

        // Buscar un producto
        Producto encontrado = inventario.buscarProductoPorId(2);
        System.out.println("\nProducto encontrado: " + encontrado);

        // Actualizar cantidad
        inventario.actualizarCantidad(1, 15);
        System.out.println("\nCantidad actualizada para ID 1: " + inventario.buscarProductoPorId(1).getCantidad());

        // Eliminar producto
        boolean eliminado = inventario.eliminarProducto(3);
        System.out.println("Producto ID 3 eliminado: " + eliminado);
        System.out.println("Productos restantes: " + inventario.contarProductos());
    }
}
