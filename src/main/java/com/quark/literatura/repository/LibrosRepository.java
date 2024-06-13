package com.quark.literatura.repository;

import com.quark.literatura.models.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LibrosRepository extends CrudRepository<Libros, Integer> {
    @Query("SELECT DISTINCT l FROM Libros l JOIN FETCH l.autores JOIN FETCH l.idiomas")
    List<Libros> findAllWithAutoresAndIdiomas();
}
