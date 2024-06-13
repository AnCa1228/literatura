package com.quark.literatura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        Integer id,
        @JsonAlias("title")
        String titulo,
        @JsonAlias("authors")
        Set<Autor> autores,
        @JsonAlias("languages")
        Set<String> idiomas,
        @JsonAlias("download_count")
        Integer cantidadDescargas) {
}
