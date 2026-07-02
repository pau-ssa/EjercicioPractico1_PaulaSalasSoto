package com.EjercicioPractico1_PaulaSalasSoto.EjercicioPractico1.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

    // Se recomienda añadir un serialVersionUID
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", unique = true, nullable = false, length = 100)
    @NotNull
    @Size(max = 100)
    private String nombre; 
    
    @OneToMany(mappedBy="categoria")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Clase> clases;

}
