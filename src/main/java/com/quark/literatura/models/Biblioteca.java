package com.quark.literatura.models;

import java.util.List;
import java.util.stream.Collectors;


public class Biblioteca {

    private Integer totalLibros;
    private List<Libros> datosLibros;

    public Biblioteca(DatosBiblioteca datosBiblioteca) {
        this.totalLibros = datosBiblioteca.totalLibros();
        this.datosLibros = datosBiblioteca.datosLibros().stream()
                .map(Libros::new)
                .collect(Collectors.toList());
    }

    public Biblioteca(){}

    public Integer getTotalLibros() {
        return totalLibros;
    }

    public void setTotalLibros(Integer totalLibros) {
        this.totalLibros = totalLibros;
    }

    public List<Libros> getDatosLibros() {
        return datosLibros;
    }

    public void setDatosLibros(List<Libros> datosLibros) {
        this.datosLibros = datosLibros;
    }

    @Override
    public String toString() {
        return "Biblioteca {" +
                "Total Libros = " + totalLibros +
                '}';
    }
}
