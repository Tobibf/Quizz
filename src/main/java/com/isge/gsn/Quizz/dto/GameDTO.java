package com.isge.gsn.Quizz.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GameDTO {
    private long id;
    private double score;

    private UserDTO userDTO;

    private List<String> answersDTO;

    //Only here, to allow sending of 5 questions
    private List<QuestionDTO> questionDTOS;
}
