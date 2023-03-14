package org.bguerra.java.jdbc;

import org.bguerra.java.jdbc.modelo.Categoria;
import org.bguerra.java.jdbc.modelo.Producto;
import org.bguerra.java.jdbc.repositorio.ProductoRepositorioImpl;
import org.bguerra.java.jdbc.repositorio.Repositorio;
import org.bguerra.java.jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class EjemploJdbcTrx {
    public static void main(String[] args) throws SQLException {

        try (Connection conn = ConexionBaseDatos.getInstance()) {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try {
                Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
                System.out.println("=============LISTAR================");
                repositorio.listar().forEach(System.out::println);
                System.out.println("==============OBTENER POR ID===============");
                System.out.println(repositorio.porId(1L));
                System.out.println("==============INSERTAR NUEVO PRODUCTO===============");
                Producto producto = new Producto();
                producto.setNombre("Teclado IBM mecanico");
                producto.setPrecio(1450);
                Categoria categoria = new Categoria();
                categoria.setId(3L);
                producto.setCategoria(categoria);
                producto.setSku("HC45201");
                producto.setFechaRegistro(new Date());
                repositorio.guardar(producto);
                System.out.println("Producto guardado con exito");


                System.out.println("==============EDITAR PRODUCTO===============");
                producto = new Producto();
                producto.setId(5L);
                producto.setNombre("Teclado Corsair k95 mecanico");
                producto.setPrecio(1000);
                producto.setSku("HC57083");
                categoria = new Categoria();
                categoria.setId(2L);
                producto.setCategoria(categoria);
                repositorio.guardar(producto);
                System.out.println("Producto actualizado con exito");
                repositorio.listar().forEach(System.out::println);

                conn.commit();
            } catch (SQLException exception) {
                conn.rollback();
                exception.printStackTrace();
            }
        }
    }
}