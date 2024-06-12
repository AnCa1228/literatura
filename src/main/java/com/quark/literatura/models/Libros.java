package com.quark.literatura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Libros {
    private int id;
    @JsonAlias("title")
    private String titulo;
    @JsonAlias("authors")
    private List<Autor> autores;
    private List<String> subjects;
    @JsonAlias("languages")
    private List<String> idiomas;
    @JsonAlias("download_count")
    private int cantidadDescargas;

    public Libros(int id, String titulo, List<Autor> autores, List<String> subjects, List<String> idiomas, int cantidadDescargas) {
        this.id = id;
        this.titulo = titulo;
        this.autores = autores;
        this.subjects = subjects;
        this.idiomas = idiomas;
        this.cantidadDescargas = cantidadDescargas;
    }

    public Libros(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public int getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(int cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }
}
