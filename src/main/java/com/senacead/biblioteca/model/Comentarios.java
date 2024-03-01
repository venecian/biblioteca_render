
package com.senacead.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Comentarios")
public class Comentarios {
  
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)    
    private Integer Id;
    
    private String descricao;
    
    @ManyToOne
    @JoinColumn(name="id_livro")
    private Livro livro;
    
}
