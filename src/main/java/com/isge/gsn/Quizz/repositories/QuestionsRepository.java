package com.isge.gsn.Quizz.repositories;

import com.isge.gsn.Quizz.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionsRepository extends JpaRepository<Question, Long> {

    @Query(nativeQuery = true, value = "select * from questions order by rand() limit 5")
    List<Question> randomQuestions();
}
