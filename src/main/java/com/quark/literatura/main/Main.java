package com.quark.literatura.main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quark.literatura.models.Autor;
import com.quark.literatura.models.Biblioteca;
import com.quark.literatura.models.DatosBiblioteca;
import com.quark.literatura.models.Libros;
import com.quark.literatura.service.ConsumirAPI;
import com.quark.literatura.service.ConvierteDatos;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private Scanner entrada = new Scanner(System.in);
    private String URL = "https://gutendex.com/books/";
    private ConsumirAPI consumirAPI = new ConsumirAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private Biblioteca biblioteca;

    public void muestraMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    
                    1 - Libros Disponibles
                    2 - Buscar libros por titulo
                    3 - Buscar libros por autor
                    4 - Mostrar libros teniendo en cuenta si el autor estaba vivo en un rango de tiempo
                    5 - Listar libros buscados
                    6 - Listar autores buscados
                    7 - Listar autores vivos en determinado año
                                        
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1:
                    getDatosBiblioteca();
                    break;
                case 2:
                    buscarLibrosPorTitulo();
                    break;
                case 3:
                    System.out.println("Buscando libros por autor");
                    buscarLibrosPorAutor();
                    break;
                case 4:
                    librosRangoTiempo();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void getDatosBiblioteca() {
        var json = consumirAPI.obtenerDatos(URL);
        DatosBiblioteca datosBiblioteca = convierteDatos.obtenerDatos(json, DatosBiblioteca.class);
        Biblioteca biblioteca = new Biblioteca(datosBiblioteca);
        System.out.println(biblioteca);
    }

    private void buscarLibrosPorTitulo() {
        System.out.println("Introduzca el titulo del libro:");
        String titulo = entrada.nextLine();
        var json = consumirAPI.obtenerDatos(URL + "?search=" + titulo.replace(" ", "%20"));
        DatosBiblioteca datosBiblioteca = convierteDatos.obtenerDatos(json, DatosBiblioteca.class);
        Biblioteca biblioteca = new Biblioteca(datosBiblioteca);
        System.out.println(biblioteca);

        for (Libros libro : biblioteca.getDatosLibros()) {
            System.out.println("");
            System.out.println("-------- LIBRO --------");
            System.out.println("Id: " + libro.getId());
            System.out.println("Titulo: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutores());
            System.out.println("Idioma: " + libro.getIdiomas());
            System.out.println("Número de descargas: " + libro.getCantidadDescargas());
            System.out.println("-----------------------");
            System.out.println("");
        }

        var agregar = -1;
        while (agregar != 1 && agregar != 2) {
            var opciones = """
                    
                    ¿Desea agregar algun libro a su listado?
                    
                    1 - Si
                    2 - No
                    
                    """;
            System.out.println(opciones);
            agregar = entrada.nextInt();
            entrada.nextLine();

            switch (agregar) {
                case 1:
                    System.out.println("Introduzca el id del libro que desea añadir a su listado");
                    int idLibroSeleccionado = entrada.nextInt();
                    libroPorID(idLibroSeleccionado);
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private void libroPorID(int idLibroSeleccionado) {
        var json = consumirAPI.obtenerDatos(URL + "?ids=" + idLibroSeleccionado);
        DatosBiblioteca datosBiblioteca = convierteDatos.obtenerDatos(json, DatosBiblioteca.class);
        Biblioteca biblioteca = new Biblioteca(datosBiblioteca);

        for (Libros libro : biblioteca.getDatosLibros()) {
            System.out.println("");
            System.out.println("-------- LIBRO AGREGADO --------");
            System.out.println("Id: " + libro.getId());
            System.out.println("Titulo: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutores());
            System.out.println("Idioma: " + libro.getIdiomas());
            System.out.println("Número de descargas: " + libro.getCantidadDescargas());
            System.out.println("--------------------------------");
            System.out.println("");
        }
    }

    private void buscarLibrosPorAutor() {
    }

    private void librosRangoTiempo(){
        System.out.println("Introduzca el año inicial:");
        String inicio = entrada.nextLine();
        System.out.println("Introduzca el año final:");
        String fin = entrada.nextLine();
        var json = consumirAPI.obtenerDatos(URL + "?author_year_start=" + inicio + "&author_year_end=" + fin);
        DatosBiblioteca datosBiblioteca = convierteDatos.obtenerDatos(json, DatosBiblioteca.class);
        Biblioteca biblioteca = new Biblioteca(datosBiblioteca);
        System.out.println(biblioteca);

        for (Libros libro : biblioteca.getDatosLibros()) {
            System.out.println("");
            System.out.println("-------- LIBRO --------");
            System.out.println("Id: " + libro.getId());
            System.out.println("Titulo: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutores());
            System.out.println("Idioma: " + libro.getIdiomas());
            System.out.println("Número de descargas: " + libro.getCantidadDescargas());
            System.out.println("-----------------------");
            System.out.println("");
        }

        var agregar = -1;
        while (agregar != 1 && agregar != 2) {
            var opciones = """
                    
                    ¿Desea agregar algun libro a su listado?
                    
                    1 - Si
                    2 - No
                    
                    """;
            System.out.println(opciones);
            agregar = entrada.nextInt();
            entrada.nextLine();

            switch (agregar) {
                case 1:
                    System.out.println("Introduzca el id del libro que desea añadir a su listado");
                    int idLibroSeleccionado = entrada.nextInt();
                    libroPorID(idLibroSeleccionado);
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
}
