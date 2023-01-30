package com.isge.gsn.Quizz.services;

import com.isge.gsn.Quizz.models.Moment;
import com.isge.gsn.Quizz.repositories.MomentsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MomentsService {
    private final MomentsRepository momentsRepository;

    public Moment save(Moment moment){
        return momentsRepository.save(moment);
    }
}
