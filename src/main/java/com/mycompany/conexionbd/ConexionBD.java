package com.mycompany.conexionbd;

import static com.mysql.cj.util.SaslPrep.StringType.QUERY;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConexionBD {

    static final String DB_URL = "jdbc:mysql://localhost:3306/jcvd";
    static final String USER = "alejandro";
    static final String PASS = "1234";

    public static void main(String[] args) {

        boolean prueba;

        String nombre = "Castlevania";

        prueba = buscaNombre(nombre);

        System.out.println(prueba);
        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");

        String nombreVideojuego = "Megaman X";

        String categoria = "Accion";

        Date fecha = new Date(93, 12, 17);

        String compania = "Capcom";

        float precio = 39.99f;

        Videojuego nuevoJuego = new Videojuego(nombreVideojuego, categoria, fecha, compania, precio);

        nuevoRegistro(nuevoJuego);

        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");

        lanzaConsulta();

        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");

        String nombre2 = "Megaman X";

        //si no está comentado este método, borrará el videojuego creado anteriormente
        boolean eliminado = EliminarRegistro(nombre2);

        System.out.println(eliminado);

        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");

        nuevoRegistro2();

    }

    public static boolean buscaNombre(String nombre) {

        boolean encontrado = false;

        String QUERY = "SELECT * FROM videojuegos WHERE NOMBRE='" + nombre + "'";

        System.out.println("Buscando información sobre el videojuego llamado " + nombre);

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(QUERY);) {

            while (rs.next()) {

                String nombreVideojuego = rs.getString("Nombre");

                if (nombreVideojuego != null && !nombreVideojuego.isEmpty()) {

                    System.out.println("ID: " + rs.getInt("id"));
                    System.out.println("Nombre: " + rs.getString("Nombre"));
                    System.out.println("Categoría: " + rs.getString("Categoría"));
                    System.out.println("Fecha Lanzamiento: " + rs.getDate("Fecha_lanzamiento"));
                    System.out.println("Compañía: " + rs.getString("Compañía"));
                    System.out.println("Precio: " + rs.getFloat("Precio"));
                    System.out.println("");

                    encontrado = true;

                }

            }
            if (!encontrado) {
                System.out.println("No se ha encontrado videojuego con el nombre " + nombre);
            }

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }

        return encontrado;
    }

    public static void lanzaConsulta() {
        String QUERY = "SELECT * FROM videojuegos";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(QUERY);) {

            while (rs.next()) {

                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getString("Nombre"));
                System.out.println("Categoría: " + rs.getString("Categoría"));
                System.out.println("Fecha Lanzamiento: " + rs.getDate("Fecha_lanzamiento"));
                System.out.println("Compañía: " + rs.getString("Compañía"));
                System.out.println("Precio: " + rs.getFloat("Precio"));
                System.out.println("");
                System.out.println("");

            }

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }

    }

    public static void nuevoRegistro(Videojuego juego) {
        String QUERY = "INSERT INTO `videojuegos` (`Nombre`, `Categoría`, `Fecha_lanzamiento`, `Compañía`, `Precio`) "
                + "VALUES ('" + juego.getNombre() + "', '" + juego.getCategoria() + "', '" + juego.getFecha_Lanzamiento() + "', '"
                + juego.getCompania() + "', " + juego.getPrecio() + ")";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); Statement stmt = conn.createStatement()) {

            int rowsAffected = stmt.executeUpdate(QUERY);

            if (rowsAffected > 0) {
                System.out.println("Nuevo registro realizado con éxito");
            } else {
                System.out.println("No se ha podido realizar el nuevo registro");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean EliminarRegistro(String nombre) {

        boolean eliminado = false;

        String QUERY = "DELETE FROM videojuegos WHERE Nombre='" + nombre + "'";

        System.out.println("Borrando el videojuego con el nombre " + nombre);

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); Statement stmt = conn.createStatement();) {

            int rowsAffected = stmt.executeUpdate(QUERY);

            if (rowsAffected > 0) {
                System.out.println("Se ha borrado con éxito");
                eliminado = true;
            } else {
                System.out.println("No se ha borrado ningún videojuego");
            }

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }

        return eliminado;

    }

    public static void nuevoRegistro2() {
        String nombre = "";
        String categoria = "";
        Date fecha_Lanzamiento = null;
        String compania = "";
        float precio;
        Scanner teclado = new Scanner(System.in);
        int longitud = 0;
        int ano = 0;
        int mes = 0;
        int dia = 0;

        while (longitud == 0) {
            System.out.println("Dime el nombre del videojuego: ");
            nombre = teclado.nextLine().trim();
            longitud = nombre.length();

            if (longitud == 0) {
                System.out.println("Error, introduzca un nombre");
                System.out.println("");
            }
        }

        System.out.println("Dime la categoría: ");
        categoria = teclado.nextLine().trim();

        System.out.println("Dime el año de lanzamiento, sólo las dos últimas cifras (por ejemplo, 87 para 1987): ");
        ano = teclado.nextInt();
        teclado.nextLine();

        System.out.println("Dime el número de mes de lanzamiento: ");
        mes = teclado.nextInt();
        teclado.nextLine();

        System.out.println("Dime el día de lanzamiento: ");
        dia = teclado.nextInt();
        teclado.nextLine();

        fecha_Lanzamiento = new Date(ano, mes, dia);

        System.out.println("Dime la compañía: ");
        compania = teclado.nextLine().trim();
        System.out.println("Compania ingresada: " + compania);

        System.out.println("Dime el precio: ");
        precio = teclado.nextFloat();

        Videojuego juego = new Videojuego(nombre, categoria, fecha_Lanzamiento, compania, precio);

        String QUERY = "INSERT INTO `videojuegos` (`Nombre`, `Categoría`, `Fecha_lanzamiento`, `Compañía`, `Precio`) "
                + "VALUES ('" + juego.getNombre() + "', '" + juego.getCategoria() + "', '" + juego.getFecha_Lanzamiento() + "', '"
                + juego.getCompania() + "', " + juego.getPrecio() + ")";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); Statement stmt = conn.createStatement()) {

            int rowsAffected = stmt.executeUpdate(QUERY);

            if (rowsAffected > 0) {
                System.out.println("Nuevo registro realizado con éxito");
            } else {
                System.out.println("No se ha podido realizar el nuevo registro");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
