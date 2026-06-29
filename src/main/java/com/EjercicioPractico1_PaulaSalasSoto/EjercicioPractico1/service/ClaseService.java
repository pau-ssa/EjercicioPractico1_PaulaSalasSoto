package com.EjercicioPractico1_PaulaSalasSoto.EjercicioPractico1.service;

import com.EjercicioPractico1_PaulaSalasSoto.EjercicioPractico1.domain.Clase;
import com.EjercicioPractico1_PaulaSalasSoto.EjercicioPractico1.repository.ClaseRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ClaseService {

    // El repositorio es final para asegurar la inmutabilidad
    private final ClaseRepository claseRepository;

    public ClaseService(ClaseRepository claseRepository) {
        this.claseRepository = claseRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Clase> getClase(Integer id) {
        return claseRepository.findById(id);
    }

     @Transactional(readOnly = true)
    public List<Clase> getClasesLista(Integer id) {
        if (id == null) {
            return claseRepository.findAll();
        }
        List<Clase> lista = new ArrayList<>();
        claseRepository.findById(id).ifPresent(lista::add);
        return lista;
    }
    
    
     @Transactional
    public void save(Clase clase) {
        clase = claseRepository.save(clase);
                claseRepository.save(clase);
           
        }
    
    @Transactional
    public void delete(Integer id) {
       
        if (!claseRepository.existsById(id)) {
            throw new IllegalArgumentException("La clase con ID " + id + " no existe.");
        }
        try {
            claseRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            
            throw new IllegalStateException("No se puede eliminar la clase. Tiene datos asociados.", e);
        }
    }
}