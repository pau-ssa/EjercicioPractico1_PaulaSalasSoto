package com.EjercicioPractico1_PaulaSalasSoto.EjercicioPractico1.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "servicio")
public class Clase implements Serializable {

    // Se recomienda añadir un serialVersionUID
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idClase;

    @Column(name = "nombre", unique = true, nullable = false, length = 100)
    @NotNull
    @Size(max = 100)
    private String nombre;
    
     @NotNull
    @Digits(integer = 6, fraction = 2)
    @Column(name = "precio", nullable = false, precision = 8, scale = 2)
    private BigDecimal precio;
     
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
     
}