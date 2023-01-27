package com.isge.gsn.Quizz.repositories;

import com.isge.gsn.Quizz.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository  extends JpaRepository<Question, Long> {
}
