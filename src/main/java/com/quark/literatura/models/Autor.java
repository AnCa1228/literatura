package com.quark.literatura.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Integer id;
    private String name;
    private Integer birth_year;
    private Integer death_year;

    @ManyToMany(mappedBy = "autores")
    private List<Libros> libros;

    public Autor(DatosAutor datosAutor) {
        this.name = datosAutor.name();
        this.birth_year = datosAutor.birth_year();
        this.death_year = datosAutor.death_year();
    }

    public Autor() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(Integer birth_year) {
        this.birth_year = birth_year;
    }

    public Integer getDeath_year() {
        return death_year;
    }

    public void setDeath_year(Integer death_year) {
        this.death_year = death_year;
    }

    @Override
    public String toString() {
        return name;
    }
}
