package org.review.com.pe.Repository;

import org.review.com.pe.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByUsername(String username);
}
