package com.isge.gsn.Quizz.repositories;

import com.isge.gsn.Quizz.models.Answer;
import com.isge.gsn.Quizz.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AnswersRepository  extends JpaRepository<Answer, Long> {
    @Query(nativeQuery = true ,value = "SELECT * FROM answers a WHERE a.question_id = :id")
    List<Answer> findByQuestion(@Param(value = "id") long id);
}
