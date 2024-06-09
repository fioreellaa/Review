package org.review.com.pe.Repository;

import org.review.com.pe.model.Comentarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDestinoComentariosRepository extends JpaRepository<Comentarios, Long>{

}
