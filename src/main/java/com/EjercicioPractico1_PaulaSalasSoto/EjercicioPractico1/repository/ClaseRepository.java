package com.EjercicioPractico1_PaulaSalasSoto.EjercicioPractico1.repository;

import com.EjercicioPractico1_PaulaSalasSoto.EjercicioPractico1.domain.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaseRepository extends JpaRepository<Clase, Integer>{
  
}