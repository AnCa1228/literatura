package com.quark.literatura.repository;

import com.quark.literatura.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Integer> {
    Autor findByName(String name);
}
