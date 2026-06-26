
package com.EjercicioPractico1_PaulaSalasSoto.EjercicioPractico1.repository;

import com.EjercicioPractico1_PaulaSalasSoto.EjercicioPractico1.domain.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
    public List<Categoria> findById();
}