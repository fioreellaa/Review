package org.review.com.pe.Repository;

import java.util.Optional;

import org.review.com.pe.model.Destino;
import org.review.com.pe.model.DestinoInteraccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDestinoInteraccionRepository extends JpaRepository <DestinoInteraccion, String> {
	DestinoInteraccion findByDestino(Destino destino);
}
