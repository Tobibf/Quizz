package com.isge.gsn.Quizz.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MomentDTO {
    private long id;

    private GameDTO gameDTO;
    private QuestionDTO questionDTO;
}
