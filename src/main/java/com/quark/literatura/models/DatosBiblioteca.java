package com.quark.literatura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosBiblioteca(
        @JsonAlias("count") Integer totalLibros,
        @JsonAlias("results") List<DatosLibros> datosLibros) {
}
