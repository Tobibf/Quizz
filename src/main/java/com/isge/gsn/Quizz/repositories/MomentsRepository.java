package com.isge.gsn.Quizz.repositories;

import com.isge.gsn.Quizz.models.Moment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MomentsRepository extends JpaRepository<Moment, Long> {

    @Query(nativeQuery = true ,value = "SELECT * FROM moments m WHERE m.game_id = :id")
    List<Moment> momentsByGame(@Param(value = "id") long id);
}
