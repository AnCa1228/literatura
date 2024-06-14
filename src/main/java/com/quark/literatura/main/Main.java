package com.quark.literatura.main;

import com.quark.literatura.models.Autor;
import com.quark.literatura.models.Biblioteca;
import com.quark.literatura.models.DatosBiblioteca;
import com.quark.literatura.models.Libros;
import com.quark.literatura.repository.AutorRepository;
import com.quark.literatura.repository.LibrosRepository;
import com.quark.literatura.service.ConsumirAPI;
import com.quark.literatura.service.ConvierteDatos;

import java.util.*;

public class Main {

    private Scanner entrada = new Scanner(System.in);
    private String URL = "https://gutendex.com/books/";
    private ConsumirAPI consumirAPI = new ConsumirAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private Biblioteca biblioteca;
    private List<Libros> librosBuscados;
    private List<Autor> autoresBuscados;
    private LibrosRepository librosRepositorio;
    private AutorRepository autorRepositorio;

    public Main(LibrosRepository librosRepository, AutorRepository autorRepository) {
        this.librosRepositorio = librosRepository;
        this.autorRepositorio = autorRepository;
    }

    public void muestraMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    
                    1 - Libros Disponibles
                    2 - Buscar libros por titulo
                    3 - Buscar libros por autor
                    4 - Buscar libros por idioma
                    5 - Buscar autores vivos en un año determinado
                    6 - Listar libros almacenados
                    7 - Listar autores almacenados
                    8 - Almacenar libro por ID
                                        
                    0 - Salir
                    """;
            System.out.println(menu);
            try {
                opcion = entrada.nextInt();
                entrada.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero.");
                entrada.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    getDatosBiblioteca();
                    break;
                case 2:
                    buscarLibrosPorTitulo();
                    break;
                case 3:
                    buscarLibrosPorAutor();
                    break;
                case 4:
                    buscarLibrosPorIdioma();
                    break;
                case 5:
                    buscarAutoresVivosPorAnnio();
                    break;
                case 6:
                    listarLibrosAlmacenados();
                    break;
                case 7:
                    listarAutoresAlmacenados();
                    break;
                case 8:
                    try {
                        System.out.println("Introduzca el id del libro que desea añadir a su listado");
                        int idLibroAgg = entrada.nextInt();
                        entrada.nextLine();
                        libroPorID(idLibroAgg);
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Debe ingresar un número entero.");
                        entrada.nextLine();
                    }
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

    private void buscarLibrosPorTitulo(){
        System.out.println("Introduzca el titulo del libro:");
        String titulo = entrada.nextLine();
        var json = consumirAPI.obtenerDatos(URL + "?search=" + titulo.replace(" ", "%20"));
        mostrarLibros(json);
    }

    private void buscarLibrosPorAutor() {
        System.out.println("Introduzca el nombre del autor:");
        String nombreAutor = entrada.nextLine();
        var json = consumirAPI.obtenerDatos(URL + "?search=" + nombreAutor.replace(" ", "%20"));
        mostrarLibros(json);
    }

    private void buscarLibrosPorIdioma(){
        System.out.println("Introduzca la abreviación del idioma: en,es,fr...");
        String idioma = entrada.nextLine();
        var json = consumirAPI.obtenerDatos(URL + "?languages=" + idioma.replace(" ", ","));
        mostrarLibros(json);
    }

    private void buscarAutoresVivosPorAnnio() {
        System.out.println("Introduzca el año:");
        int annio = entrada.nextInt();
        entrada.nextLine();
        var json = consumirAPI.obtenerDatos(URL + "?author_year_start=" + annio + "&author_year_end=" + annio);
        DatosBiblioteca datosBiblioteca = convierteDatos.obtenerDatos(json, DatosBiblioteca.class);
        Biblioteca biblioteca = new Biblioteca(datosBiblioteca);
        List<Autor> autoresVivos = biblioteca.getDatosLibros()
                .stream()
                .flatMap(libro -> libro.getAutores().stream())
                .filter(autor -> estaVivoEnAnnio(autor, annio))
                .distinct()
                .toList();

        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + annio);
        } else {
            autoresVivos.forEach(this::imprimirAutor);
        }
    }

    private void listarLibrosAlmacenados() {
        List<Libros> librosAlmacenados = librosRepositorio.findAllWithAutoresAndIdiomas();

        librosAlmacenados.forEach(libro -> {
            imprimirLibroAlmacenado(libro);
        });
    }

    private void listarAutoresAlmacenados(){
        List<Autor> autoresAlmacenados = autorRepositorio.findAll();

        autoresAlmacenados.forEach(autor -> {
            imprimirAutorAlmacenado(autor);
        });
    }

    private void mostrarLibros(String json) {
        DatosBiblioteca datosBiblioteca = convierteDatos.obtenerDatos(json, DatosBiblioteca.class);
        Biblioteca biblioteca = new Biblioteca(datosBiblioteca);
        System.out.println(biblioteca);

        biblioteca.getDatosLibros().forEach(this::imprimirLbro);

        if(biblioteca.getTotalLibros()!=0){
            var agregar = -1;
            while (agregar != 1 && agregar != 2) {
                var opciones = """
                    
                    ¿Desea agregar algun libro a su listado?
                    
                    1 - Si
                    2 - No
                    
                    """;
                System.out.println(opciones);
                try {
                    agregar = entrada.nextInt();
                    entrada.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Error: Debe ingresar un número entero.");
                    entrada.nextLine();
                    continue;
                }

                switch (agregar) {
                    case 1:
                        try {
                            System.out.println("Introduzca el id del libro que desea añadir a su listado");
                            int idLibroSeleccionado = entrada.nextInt();
                            entrada.nextLine();
                            libroPorID(idLibroSeleccionado);
                        }catch (InputMismatchException e) {
                            System.out.println("Error: Debe ingresar un número entero.");
                            entrada.nextLine();
                        }
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            }
        }else {
            System.out.println("Libro no encontrado");
        }

    }

    private void libroPorID(int idLibroSeleccionado) {
        var json = consumirAPI.obtenerDatos(URL + "?ids=" + idLibroSeleccionado);
        DatosBiblioteca datosBiblioteca = convierteDatos.obtenerDatos(json, DatosBiblioteca.class);
        Biblioteca biblioteca = new Biblioteca(datosBiblioteca);

        biblioteca.getDatosLibros().forEach(libro -> {
            libro.getAutores().forEach(autor -> {
                Autor autorExistente = autorRepositorio.findByName(autor.getName());

                if(autorExistente != null){
                    libro.getAutores().remove(autor);
                    libro.getAutores().add(autorExistente);
                }else{
                    autorRepositorio.save(autor);
                }
            });

            librosRepositorio.save(libro);
            imprimirLibroAgregado(libro);
        });
    }

    private void imprimirLbro(Libros libro){
        System.out.println("""
                -------- LIBRO --------
                Id: %d
                Titulo: %s
                Autor: %s
                Idioma: %s
                Número de descargas: %d
                -----------------------
                """.formatted(libro.getId(), libro.getTitulo(), libro.getAutores(), libro.getIdiomas(), libro.getCantidadDescargas()));
    }

    private void imprimirLibroAgregado(Libros libro) {
        System.out.println("""
                
                -------- LIBRO AGREGADO --------
                Id: %d
                Titulo: %s
                Autor: %s
                Idioma: %s
                Número de descargas: %d
                --------------------------------
                """.formatted(libro.getId(), libro.getTitulo(), libro.getAutores(), libro.getIdiomas(), libro.getCantidadDescargas()));
    }

    private void imprimirLibroAlmacenado(Libros libro) {
        System.out.println("""
                
                ------- LIBRO ALMACENADO -------
                Titulo: %s
                Autor: %s
                Idioma: %s
                Número de descargas: %d
                --------------------------------
                """.formatted(libro.getTitulo(), libro.getAutores(), libro.getIdiomas(), libro.getCantidadDescargas()));
    }

    private void imprimirAutor(Autor autor) {
        System.out.println("""
                
                ---------- AUTOR ----------
                Nombre: %s
                Fecha de Nacimiento: %d
                Fecha de Fallecimiento: %d
                ---------------------------
                """.formatted(autor.getName(), autor.getBirth_year(), autor.getDeath_year()));
    }

    private void imprimirAutorAlmacenado(Autor autor) {
        System.out.println("""
                
                ------- AUTOR ALMACENADO -------
                Nombre: %s
                Fecha de Nacimiento: %d
                Fecha de Fallecimiento: %d
                --------------------------------
                """.formatted(autor.getName(), autor.getBirth_year(), autor.getDeath_year()));
    }

    private boolean estaVivoEnAnnio(Autor autor, int annio) {
        Integer birthYear = autor.getBirth_year();
        Integer deathYear = autor.getDeath_year();

        return (birthYear != null && birthYear <= annio) && (deathYear == null || deathYear >= annio);
    }
}
