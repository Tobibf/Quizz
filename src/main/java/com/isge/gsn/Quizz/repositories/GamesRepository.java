package com.isge.gsn.Quizz.repositories;

import com.isge.gsn.Quizz.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GamesRepository extends JpaRepository<Game, Long> {

    @Query(nativeQuery = true ,value = "SELECT * FROM games g WHERE g.user_id = :id")
    List<Game> findByUser(@Param(value = "id") long id);

    @Query(nativeQuery = true ,value = "SELECT * FROM games g WHERE g.id = :id AND g.user_id = :user_id")
    Optional<Game> findGameByUser(@Param(value = "id") long id, @Param(value = "user_id") long user_id);

}
