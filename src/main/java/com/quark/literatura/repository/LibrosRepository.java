package com.quark.literatura.repository;

import com.quark.literatura.models.Libros;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrosRepository extends JpaRepository<Libros, Integer> {
}
