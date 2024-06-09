package org.review.com.pe.Repository;

import org.review.com.pe.model.Destino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDestinoRespository extends JpaRepository<Destino, String>{

}
