package org.bguerra.java.jdbc;

import org.bguerra.java.jdbc.modelo.Categoria;
import org.bguerra.java.jdbc.modelo.Producto;
import org.bguerra.java.jdbc.repositorio.ProductoRepositorioImpl;
import org.bguerra.java.jdbc.repositorio.Repositorio;
import org.bguerra.java.jdbc.util.ConexionBaseDatos;

import java.sql.*;
import java.util.Date;

public class EjemploJdbcInsertar {
    public static void main(String[] args) {

        try (Connection conn = ConexionBaseDatos.getInstance()) {

            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
            System.out.println("=============LISTAR================");
            repositorio.listar().forEach(System.out::println);
            System.out.println("==============OBTENER POR ID===============");
            System.out.println(repositorio.porId(1L));
            System.out.println("==============INSERTAR NUEVO PRODUCTO===============");
            Producto producto = new Producto();
            producto.setNombre("Teclado Corsair Red Dragon mecanico");
            producto.setPrecio(450);
            Categoria categoria = new Categoria();
            categoria.setId(2L);
            producto.setCategoria(categoria);
            producto.setFechaRegistro(new Date());
            repositorio.guardar(producto);
            System.out.println("Producto guardado con exito");
            repositorio.listar().forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}