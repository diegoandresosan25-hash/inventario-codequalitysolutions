package com.codequalitysolutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona el inventario de productos de CodeQuality Solutions S.L.
 * Permite añadir, eliminar, buscar y calcular el valor total del inventario.
 *
 * @author TUS_INICIALES
 * @version 1.0
 */
public class Inventario {

    /** Lista interna de productos almacenados. */
    private List<Producto> productos;

    /**
     * Construye un inventario vacío.
     */
    public Inventario() {
        this.productos = new ArrayList<>();
    }

    /**
     * Añade un producto al inventario.
     * No permite productos con cantidad negativa ni precio negativo o cero.
     *
     * @param producto el producto a añadir
     * @throws IllegalArgumentException si la cantidad es negativa o el precio no es positivo
     * @throws IllegalArgumentException si ya existe un producto con el mismo ID
     */
    public void agregarProducto(Producto producto) {
        if (producto.getCantidad() < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }
        if (producto.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que cero.");
        }
        for (Producto p : productos) {
            if (p.getId() == producto.getId()) {
                throw new IllegalArgumentException("Ya existe un producto con el ID: " + producto.getId());
            }
        }
        productos.add(producto);
    }

    /**
     * Elimina un producto del inventario por su ID.
     *
     * @param id identificador del producto a eliminar
     * @return true si se eliminó, false si no se encontró
     */
    public boolean eliminarProducto(int id) {
        return productos.removeIf(p -> p.getId() == id);
    }

    /**
     * Busca un producto por su ID.
     *
     * @param id identificador del producto
     * @return el producto encontrado, o null si no existe
     */
    public Producto buscarProductoPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    /**
     * Calcula el valor total del inventario sumando (precio * cantidad) de cada producto.
     *
     * @return valor total del inventario
     */
    public double calcularValorTotal() {
        double total = 0.0;
        for (Producto p : productos) {
            total = calcularValorProducto(total, p);
        }
        return total;
    }

	public double calcularValorProducto(double total, Producto p) {
		total += p.getPrecio() * p.getCantidad();
		return total;
	}

    /**
     * Actualiza la cantidad en stock de un producto existente.
     *
     * @param id       identificador del producto a actualizar
     * @param cantidad nueva cantidad (debe ser >= 0)
     * @throws IllegalArgumentException si la cantidad es negativa
     * @throws IllegalArgumentException si el producto no existe
     */
    public void actualizarCantidad(int id, int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }
        Producto producto = buscarProductoPorId(id);
        if (producto == null) {
            throw new IllegalArgumentException("Producto con ID " + id + " no encontrado.");
        }
        producto.setCantidad(cantidad);
    }

    /**
     * Devuelve la lista completa de productos en el inventario.
     *
     * @return lista de productos (copia defensiva)
     */
    public List<Producto> getProductos() {
        return new ArrayList<>(productos);
    }

    /**
     * Devuelve el número de productos distintos en el inventario.
     *
     * @return número total de productos
     */
    public int contarProductos() {
        return productos.size();
    }
}
