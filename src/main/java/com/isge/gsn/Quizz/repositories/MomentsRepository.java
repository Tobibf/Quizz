package com.isge.gsn.Quizz.repositories;

import com.isge.gsn.Quizz.models.Moment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MomentsRepository extends JpaRepository<Moment, Long> {
}
