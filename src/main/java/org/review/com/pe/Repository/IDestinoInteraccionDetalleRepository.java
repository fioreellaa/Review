package org.review.com.pe.Repository;

import org.review.com.pe.model.InteraccionDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDestinoInteraccionDetalleRepository extends JpaRepository<InteraccionDetalle, Long>{

}
