package com.quark.literatura.models;


import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "libros")
public class Libros {
    @Id
    @Column(unique = true)
    private Integer id;
    private String titulo;
    @ManyToMany
    @JoinTable(
            name = "libros_autores",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private Set<Autor> autores;
    @ElementCollection
    @CollectionTable(name = "libros_idiomas", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "idioma")
    private Set<String> idiomas;
    private Integer cantidadDescargas;

    public Libros(DatosLibros datosLibros) {
        this.id = datosLibros.id();
        this.titulo = datosLibros.titulo();
        this.autores = datosLibros.autores();
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

    public Set<Autor> getAutores() {
        return autores;
    }

    public void setAutores(Set<Autor> autores) {
        this.autores = autores;
    }

    public Set<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Set<String> idiomas) {
        this.idiomas = idiomas;
    }

    /*public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }*/

    public Integer getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(Integer cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    @Override
    public String toString() {
        return "Libros{" +
                "titulo='" + titulo + '\'' +
                ", autores=" + autores +
                ", idiomas=" + idiomas +
                ", cantidadDescargas=" + cantidadDescargas +
                '}';
    }
}
