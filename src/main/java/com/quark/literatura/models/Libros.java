package com.quark.literatura.models;


import java.util.List;


public class Libros {
    private Integer id;
    private String titulo;
    private List<Autor> autores;
    private List<String> subjects;
    private List<String> idiomas;
    private Integer cantidadDescargas;

    public Libros(DatosLibros datosLibros) {
        this.id = datosLibros.id();
        this.titulo = datosLibros.titulo();
        this.autores = datosLibros.autores();
        this.subjects = datosLibros.subjects();
        this.idiomas = datosLibros.idiomas();
        this.cantidadDescargas = datosLibros.cantidadDescargas();
    }

    public Libros(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(Integer cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    @Override
    public String toString() {
        return "Libros{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autores=" + autores +
                ", subjects=" + subjects +
                ", idiomas=" + idiomas +
                ", cantidadDescargas=" + cantidadDescargas +
                '}';
    }
}
