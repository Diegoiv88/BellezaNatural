package com.example.salabelleza.repository;

import com.example.salabelleza.model.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Valoracion, Long> {

}
