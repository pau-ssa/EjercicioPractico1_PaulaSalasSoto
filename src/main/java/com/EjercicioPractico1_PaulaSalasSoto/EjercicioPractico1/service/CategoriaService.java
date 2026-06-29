/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EjercicioPractico1_PaulaSalasSoto.EjercicioPractico1.service;

import com.EjercicioPractico1_PaulaSalasSoto.EjercicioPractico1.domain.Categoria;
import com.EjercicioPractico1_PaulaSalasSoto.EjercicioPractico1.repository.CategoriaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CategoriaService {

    // El repositorio es final para asegurar la inmutabilidad
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Categoria> getCategoria(Integer id) {
        return categoriaRepository.findById(id);
    }

     @Transactional(readOnly = true)
    public List<Categoria> getCategoriasLista(Integer id) {
        if (id == null) {
            return categoriaRepository.findAll();
        }
        List<Categoria> lista = new ArrayList<>();
        categoriaRepository.findById(id).ifPresent(lista::add);
        return lista;
    }
    
    
     @Transactional
    public void save(Categoria categoria) {
        categoria = categoriaRepository.save(categoria);
                categoriaRepository.save(categoria);
           
        }
    
    @Transactional
    public void delete(Integer id) {
        // Verifica si la categoría existe antes de intentar eliminarlo
        if (!categoriaRepository.existsById(id)) {
            // Lanza una excepción para indicar que el usuario no fue encontrado
            throw new IllegalArgumentException("La categoría con ID " + id + " no existe.");
        }
        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            // Lanza una nueva excepción para encapsular el problema de integridad de datos
            throw new IllegalStateException("No se puede eliminar la categoria. Tiene datos asociados.", e);
        }
    }
}
 