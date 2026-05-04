package com.codequalitysolutions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Suite de pruebas unitarias para la clase {@link Inventario}.
 * Cubre los métodos principales: agregarProducto, calcularValorTotal y actualizarCantidad.
 *
 * @author DIEGO ANDRES OSAN
 * @version 1.0
 */
class InventarioTest {

   

    private Inventario inventario;

    
    @BeforeEach
    void setUp() {
        inventario = new Inventario();
    }

    // =========================================================
    // TESTS: agregarProducto
    // =========================================================

    @Test
    @DisplayName("Agregar producto válido incrementa el contador")
    void testAgregarProductoValido() {
        Producto p = new Producto(1, "Teclado", 10, 49.99);
        inventario.agregarProducto(p);
        assertEquals(1, inventario.contarProductos());
    }

    @Test
    @DisplayName("Agregar producto con cantidad negativa lanza excepción")
    void testAgregarProductoCantidadNegativa() {
        Producto p = new Producto(2, "Ratón", -5, 29.99);
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> inventario.agregarProducto(p)
        );
        assertEquals("La cantidad no puede ser negativa.", ex.getMessage());
    }

    @Test
    @DisplayName("Agregar producto con precio cero lanza excepción")
    void testAgregarProductoPrecioCero() {
        Producto p = new Producto(3, "Cable USB", 10, 0.0);
        assertThrows(IllegalArgumentException.class, () -> inventario.agregarProducto(p));
    }

    @Test
    @DisplayName("Agregar producto con precio negativo lanza excepción")
    void testAgregarProductoPrecioNegativo() {
        Producto p = new Producto(4, "Auriculares", 3, -15.0);
        assertThrows(IllegalArgumentException.class, () -> inventario.agregarProducto(p));
    }

    @Test
    @DisplayName("Agregar producto con ID duplicado lanza excepción")
    void testAgregarProductoIdDuplicado() {
        inventario.agregarProducto(new Producto(1, "Teclado", 5, 49.99));
        assertThrows(IllegalArgumentException.class,
            () -> inventario.agregarProducto(new Producto(1, "Otro", 2, 10.0)));
    }

    // =========================================================
    // TESTS: calcularValorTotal
    // =========================================================

    @Test
    @DisplayName("Valor total de inventario vacío es cero")
    void testCalcularValorTotalInventarioVacio() {
        assertEquals(0.0, inventario.calcularValorTotal(), 0.001);
    }

    @Test
    @DisplayName("Valor total con un producto es precio * cantidad")
    void testCalcularValorTotalUnProducto() {
        inventario.agregarProducto(new Producto(1, "Monitor", 2, 200.0));
        assertEquals(400.0, inventario.calcularValorTotal(), 0.001);
    }

    @Test
    @DisplayName("Valor total con múltiples productos es la suma correcta")
    void testCalcularValorTotalMultiplesProductos() {
        inventario.agregarProducto(new Producto(1, "Monitor", 2, 200.0));   // 400.0
        inventario.agregarProducto(new Producto(2, "Teclado", 5, 50.0));    // 250.0
        inventario.agregarProducto(new Producto(3, "Ratón", 10, 25.0));     // 250.0
        assertEquals(900.0, inventario.calcularValorTotal(), 0.001);
    }

    // =========================================================
    // TESTS: actualizarCantidad
    // =========================================================

    @Test
    @DisplayName("Actualizar cantidad válida modifica el producto correctamente")
    void testActualizarCantidadValida() {
        inventario.agregarProducto(new Producto(1, "Teclado", 10, 49.99));
        inventario.actualizarCantidad(1, 25);
        assertEquals(25, inventario.buscarProductoPorId(1).getCantidad());
    }

    @Test
    @DisplayName("Actualizar cantidad a cero es válido (sin stock)")
    void testActualizarCantidadACero() {
        inventario.agregarProducto(new Producto(1, "Teclado", 10, 49.99));
        inventario.actualizarCantidad(1, 0);
        assertEquals(0, inventario.buscarProductoPorId(1).getCantidad());
    }

    @Test
    @DisplayName("Actualizar cantidad negativa lanza excepción")
    void testActualizarCantidadNegativa() {
        inventario.agregarProducto(new Producto(1, "Teclado", 10, 49.99));
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> inventario.actualizarCantidad(1, -3)
        );
        assertEquals("La cantidad no puede ser negativa.", ex.getMessage());
    }

    @Test
    @DisplayName("Actualizar cantidad de producto inexistente lanza excepción")
    void testActualizarCantidadProductoInexistente() {
        assertThrows(IllegalArgumentException.class,
            () -> inventario.actualizarCantidad(999, 5));
    }

    // =========================================================
    // TESTS: buscarProductoPorId
    // =========================================================

    @Test
    @DisplayName("Buscar producto existente devuelve el producto correcto")
    void testBuscarProductoExistente() {
        Producto p = new Producto(1, "Teclado", 10, 49.99);
        inventario.agregarProducto(p);
        Producto resultado = inventario.buscarProductoPorId(1);
        assertNotNull(resultado);
        assertEquals("Teclado", resultado.getNombre());
    }

    @Test
    @DisplayName("Buscar producto inexistente devuelve null")
    void testBuscarProductoInexistente() {
        assertNull(inventario.buscarProductoPorId(999));
    }

    // =========================================================
    // TESTS: eliminarProducto
    // =========================================================

    @Test
    @DisplayName("Eliminar producto existente devuelve true y reduce el contador")
    void testEliminarProductoExistente() {
        inventario.agregarProducto(new Producto(1, "Teclado", 10, 49.99));
        assertTrue(inventario.eliminarProducto(1));
        assertEquals(0, inventario.contarProductos());
    }

    @Test
    @DisplayName("Eliminar producto inexistente devuelve false")
    void testEliminarProductoInexistente() {
        assertFalse(inventario.eliminarProducto(999));
    }
}
