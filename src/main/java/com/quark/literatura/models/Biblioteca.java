package com.quark.literatura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Biblioteca {

    @JsonAlias("count")
    private Integer totalLibros;
    @JsonAlias("results")
    private List<Libros> datosLibros;

    public Biblioteca(Integer totalLibros, List<Libros> datosLibros) {
        this.totalLibros = totalLibros;
        this.datosLibros = datosLibros;
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
}
