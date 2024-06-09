package org.review.com.pe.Repository;

import org.review.com.pe.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDestinoLikesRepository extends JpaRepository<Likes, Long>{

}
