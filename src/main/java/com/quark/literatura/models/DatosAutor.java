package com.quark.literatura.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        String name,
        Integer birth_year,
        Integer death_year) {
}
