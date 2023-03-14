package org.bguerra.java.jdbc;

import org.bguerra.java.jdbc.modelo.Producto;
import org.bguerra.java.jdbc.repositorio.ProductoRepositorioImpl;
import org.bguerra.java.jdbc.repositorio.Repositorio;
import org.bguerra.java.jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;

public class EjemploJdbcEliminar {
    public static void main(String[] args) {

        try (Connection conn = ConexionBaseDatos.getInstance()) {

            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
            System.out.println("=============LISTAR================");
            repositorio.listar().forEach(System.out::println);
            System.out.println("==============OBTENER POR ID===============");
            System.out.println(repositorio.porId(1L));
            System.out.println("==============ELIMINAR PRODUCTO===============");
            Producto producto = new Producto();
            producto.setId(5L);
            repositorio.eliminar(producto.getId());
            System.out.println("Producto eliminado con exito");
            repositorio.listar().forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}