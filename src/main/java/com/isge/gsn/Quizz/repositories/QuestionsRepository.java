package com.isge.gsn.Quizz.repositories;

import com.isge.gsn.Quizz.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionsRepository extends JpaRepository<Question, Long> {

    @Query(nativeQuery = true, value = "select * from questions order by rand() limit 5")
    List<Question> randomQuestions();

    @Query(nativeQuery = true, value = "select q.* from questions q, games g," +
            " moments m where q.id=m.question_id and g.id=m.game_id and g.id = :id")
    List<Question> gameQuestions(@Param(value = "id") long id);

    @Query(nativeQuery = true, value = "select q.id, q.answer,q.content from questions q")
    List<Question> test();
}
