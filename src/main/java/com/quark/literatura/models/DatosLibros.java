package com.quark.literatura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        Integer id,
        @JsonAlias("title")
        String titulo,
        @JsonAlias("authors")
        List<Autor> autores,
        @JsonAlias("languages")
        List<String> idiomas,
        @JsonAlias("download_count")
        Integer cantidadDescargas) {
}
