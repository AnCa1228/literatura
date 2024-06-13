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
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private Scanner entrada = new Scanner(System.in);
    private String URL = "https://gutendex.com/books/";
    private ConsumirAPI consumirAPI = new ConsumirAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();

    public void muestraMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libros por titulo
                    2 - Buscar libros por autor
                    3 - Buscar libros por categoria
                    4 - Libros Disponibles
                                        
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Buscando libros por titulo");
                    break;
                case 2:
                    System.out.println("Buscando libros por autor");
                    break;
                case 3:
                    System.out.println("Buscando libros por categoria");
                    break;
                case 4:
                    System.out.println("Buscando libros disponibles");
                    getDatosBiblioteca();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    /*private void buscarLibrosDisponibles(){
        var json = consumirAPI.obtenerDatos(URL);
    }

    private void buscarLibrosDisponibles() {
        String json = consumirAPI.obtenerDatos(URL);

        if (json == null || json.isEmpty()) {
            System.out.println("Error: JSON recibido está vacío o es nulo.");
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            Biblioteca biblioteca = mapper.readValue(json, Biblioteca.class);
            System.out.println("Total de libros disponibles: " + biblioteca.getTotalLibros());
            System.out.println("Detalles de los libros disponibles:");
            for (Libros libro : biblioteca.getDatosLibros()) {
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autores: ");
                for (Autor autor : libro.getAutores()) {
                    System.out.println("- " + autor.getName());
                }
                System.out.println("Idiomas: " + libro.getIdiomas());
                System.out.println("Cantidad de Descargas: " + libro.getCantidadDescargas());
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Error al obtener los libros disponibles: " + e.getMessage());
            e.printStackTrace();
        }
    }*/
/*
    private void getDatosBiblioteca(){
        var json = consumirAPI.obtenerDatos(URL);
        System.out.println(json);
        DatosBiblioteca datos = convierteDatos.obtenerDatos(json, DatosBiblioteca.class);
        Biblioteca biblioteca = new Biblioteca(datos);
        System.out.println(biblioteca);;
    }*/

    private void getDatosBiblioteca() {
        var json = consumirAPI.obtenerDatos(URL);
        System.out.println(json);
        DatosBiblioteca datosBiblioteca = convierteDatos.obtenerDatos(json, DatosBiblioteca.class);
        Biblioteca biblioteca = new Biblioteca(datosBiblioteca);
        System.out.println(biblioteca);
    }




}
