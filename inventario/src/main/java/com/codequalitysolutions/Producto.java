package com.codequalitysolutions;

/**
 * Representa un producto dentro del sistema de gestión de inventario.
 *
 * @author TUS_INICIALES
 * @version 1.0
 */
public class Producto {

    /** Identificador único del producto. */
    private int id;

    /** Nombre del producto. */
    private String nombre;

    /** Cantidad disponible en inventario. */
    private int cantidad;

    /** Precio unitario del producto. */
    private double precio;

    /**
     * Construye un nuevo producto con todos sus atributos.
     *
     * @param id       identificador único del producto
     * @param nombre   nombre descriptivo del producto
     * @param cantidad cantidad inicial en stock (debe ser >= 0)
     * @param precio   precio unitario (debe ser > 0)
     */
    public Producto(int id, String nombre, int cantidad, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    /**
     * Devuelve el identificador del producto.
     *
     * @return id del producto
     */
    public int getId() {
        return id;
    }

    /**
     * Devuelve el nombre del producto.
     *
     * @return nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la cantidad disponible en inventario.
     *
     * @return cantidad en stock
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad disponible en inventario.
     *
     * @param cantidad nueva cantidad (debe ser >= 0)
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Devuelve el precio unitario del producto.
     *
     * @return precio del producto
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio unitario del producto.
     *
     * @param precio nuevo precio (debe ser > 0)
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Devuelve una representación textual del producto.
     *
     * @return cadena con los datos del producto
     */
    @Override
    public String toString() {
        return "Producto{id=" + id + ", nombre='" + nombre + "', cantidad=" + cantidad + ", precio=" + precio + "}";
    }
}
