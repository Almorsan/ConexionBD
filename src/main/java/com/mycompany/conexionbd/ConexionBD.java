package com.mycompany.conexionbd;

import static com.mysql.cj.util.SaslPrep.StringType.QUERY;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.time.LocalDate;

public class ConexionBD {

    static final String DB_URL = "jdbc:mysql://localhost:3306/jcvd"; //dirección de la base de datos
    static final String USER = "alejandro"; //usuario de la base de datos
    static final String PASS = "1234"; //contraseña de la bbdd

    public static void main(String[] args) {
        //comprobamos si un videojuego se encuentra en nuestra base de 
        //datos pasandole el nombre del mismo
        boolean prueba;

        String nombre = "Castlevania";

        prueba = buscaNombre(nombre);

        System.out.println(prueba);
        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");
        
        //añadimos un videojuego a nuestra base de datos
        //mediante la construcción de un objeto Videojuego

        String nombreVideojuego = "Megaman X";

        String categoria = "Accion";

        LocalDate fecha =  LocalDate.of(1993, 12, 17);

        String compania = "Capcom";

        float precio = 39.99f;

        Videojuego nuevoJuego = new Videojuego(nombreVideojuego, categoria, fecha, compania, precio);

        nuevoRegistro(nuevoJuego);

        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");
        
        //lanzamos una consulta para consultar toda la información
        //de la base de datos
        lanzaConsulta();

        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");
        
        //borramos un videojuego de la base de datos
        //mediante un nombre pasado como parámetro
        String nombre2 = "Megaman X";

        //si no está comentado este método, borrará el videojuego creado anteriormente
        boolean eliminado = EliminarRegistro(nombre2);

        System.out.println(eliminado);

        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");
        
        //efectuamos un nuevo registro

        nuevoRegistro2();

    }

    public static boolean buscaNombre(String nombre) {
        //con este método, buscamos un videojuego en la base de datos
        //mediante un nombre pasado por teclado. Si el juego existe
        //devolvemos un boolean de valor true. Si no, el valor del
        //boolean será false

        boolean encontrado = false;

        String QUERY = "SELECT * FROM videojuegos WHERE NOMBRE='" + nombre + "'";
        //utilizamos la consulta de arriba para buscar el juego

        System.out.println("Buscando información sobre el videojuego llamado " + nombre);

        try (Connection conexion = DriverManager.getConnection(DB_URL, USER, PASS); Statement sentencia = conexion.createStatement(); ResultSet resultado = sentencia.executeQuery(QUERY);) {

            while (resultado.next()) { //mientras se lea la base de datos

                String nombreVideojuego = resultado.getString("Nombre"); //se guarda el nombre del 
                //videojuego que se esté leyendo en ese momento en una variable
                

                if (nombreVideojuego != null && !nombreVideojuego.isEmpty()) { //si el nombre no es null y no está vacío
                   //mostramos los datos del juego
                    System.out.println("ID: " + resultado.getInt("id"));
                    System.out.println("Nombre: " + resultado.getString("Nombre"));
                    System.out.println("Categoría: " + resultado.getString("Categoría"));
                    System.out.println("Fecha Lanzamiento: " + resultado.getDate("Fecha_lanzamiento"));
                    System.out.println("Compañía: " + resultado.getString("Compañía"));
                    System.out.println("Precio: " + resultado.getFloat("Precio"));
                    System.out.println("");

                    encontrado = true; //cambiamos el valor de encontrado a true

                }

            }
            if (!encontrado) { //si encontrado es false
                System.out.println("No se ha encontrado videojuego con el nombre " + nombre);
            }

            sentencia.close(); //cerramos la conexión

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }

        return encontrado; //devolvemos el valor de encontrado
    }

    public static void lanzaConsulta() {
        //con este método, lanzamos una consulta para ver toda la información
        //contenida en la base de datos
        
        String QUERY = "SELECT * FROM videojuegos";

        try (Connection conexion = DriverManager.getConnection(DB_URL, USER, PASS); Statement sentencia = conexion.createStatement(); ResultSet resultado = sentencia.executeQuery(QUERY);) {

            while (resultado.next()) { //mientras se lea la base de datos 

                System.out.println("ID: " + resultado.getInt("id"));
                System.out.println("Nombre: " + resultado.getString("Nombre"));
                System.out.println("Categoría: " + resultado.getString("Categoría"));
                System.out.println("Fecha Lanzamiento: " + resultado.getDate("Fecha_lanzamiento"));
                System.out.println("Compañía: " + resultado.getString("Compañía"));
                System.out.println("Precio: " + resultado.getFloat("Precio"));
                System.out.println("");
                System.out.println("");

            }

            sentencia.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }

    }

    public static void nuevoRegistro(Videojuego juego) {
         //añadimos un nuevo registro a la base de datos 
         //mediante un objeto de tipo Videojuego pasado como parámetro
        
       //construimos la sentencia ayudándonos de los getters y setters 
       //de la clase videojuego
        String QUERY = "INSERT INTO `videojuegos` (`Nombre`, `Categoría`, `Fecha_lanzamiento`, `Compañía`, `Precio`) "
                + "VALUES ('" + juego.getNombre() + "', '" + juego.getCategoria() + "', '" + juego.getFecha() + "', '"
                + juego.getCompania() + "', " + juego.getPrecio() + ")";

        try (Connection conexion = DriverManager.getConnection(DB_URL, USER, PASS); Statement sentencia = conexion.createStatement()) {

            int filasAfectadas = sentencia.executeUpdate(QUERY);

            if (filasAfectadas > 0) {
                System.out.println("Nuevo registro realizado con éxito");
            } else {
                System.out.println("No se ha podido realizar el nuevo registro");
            }
            
             sentencia.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean EliminarRegistro(String nombre) {
        //con este método, eliminamos un videojuego
        //de la base de datos mediante un nombre
        //pasado como parámetro
        //devolvemos un boolean que será true si
        //se elimina y false si no

        boolean eliminado = false;

        String QUERY = "DELETE FROM videojuegos WHERE Nombre='" + nombre + "'";
        //la sentencia de arriba borrará el videjuego con el nombre pasado
        //como parámetro

        System.out.println("Borrando el videojuego con el nombre " + nombre);

        try (Connection conexion = DriverManager.getConnection(DB_URL, USER, PASS); Statement sentencia = conexion.createStatement();) {

            int filasAfectadas = sentencia.executeUpdate(QUERY);

            if (filasAfectadas > 0) {
                System.out.println("Se ha borrado con éxito");
                eliminado = true;
            } else {
                System.out.println("No se ha borrado ningún videojuego");
            }

            sentencia.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }

        return eliminado;

    }

    public static void nuevoRegistro2() {
        //añadimos a la base de datos un nuevo juego 
        //mediante entrada por teclado
        String nombre = "";
        String categoria = "";
        LocalDate fecha_lanzamiento=null;
        String compania = "";
        float precio;
        Scanner teclado = new Scanner(System.in);
        int longitud = 0;
        int ano = 0;
        int mes = 0;
        int dia = 0;
        //ya que el nombre es un campo obligatorio, comprobaremos
        //que el usuario lo ha introducido 
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

        System.out.println("Dime el año de lanzamiento: ");
        ano = teclado.nextInt();
        teclado.nextLine();

        System.out.println("Dime el número de mes de lanzamiento: ");
        mes = teclado.nextInt();
        teclado.nextLine();

        System.out.println("Dime el día de lanzamiento: ");
        dia = teclado.nextInt();
        teclado.nextLine();

        fecha_lanzamiento =  LocalDate.of(ano, mes, dia);
       

        System.out.println("Dime la compañía: ");
        compania = teclado.nextLine().trim();
        System.out.println("Compania ingresada: " + compania);
        
        System.out.println("Dime el precio: ");
        precio = teclado.nextFloat();

        Videojuego juego = new Videojuego(nombre, categoria, fecha_lanzamiento, compania, precio);
        
        //llamamos al método nuevoRegistro
        nuevoRegistro(juego);

       

        
    }
}
