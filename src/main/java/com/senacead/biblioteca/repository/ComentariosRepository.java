
package com.senacead.biblioteca.repository;

import com.senacead.biblioteca.model.Comentarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentariosRepository extends JpaRepository<Comentarios, Integer> {
    
    List<Comentarios> findByLivroId(Integer id);
    
}
