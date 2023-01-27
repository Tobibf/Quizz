package com.isge.gsn.Quizz.repositories;

import com.isge.gsn.Quizz.models.Game;
import com.isge.gsn.Quizz.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GamesRepository extends JpaRepository<Game, Long> {

    @Query(nativeQuery = true ,value = "SELECT * FROM games g WHERE g.user_id = :id")
    List<Game> findByUser(@Param(value = "id") long id);

    @Query("select q from Question q order by rand() limit 5")
    List<Question> randomQuestions();
}
