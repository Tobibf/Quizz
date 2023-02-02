package com.isge.gsn.Quizz.repositories;

import com.isge.gsn.Quizz.models.Moment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MomentsRepository extends JpaRepository<Moment, Long> {

   /* @Query(nativeQuery = true ,value = "SELECT * FROM moments m WHERE m.game_id = :id")
    List<Moment> momentsByGame(@Param(value = "id") long id);*/
}
