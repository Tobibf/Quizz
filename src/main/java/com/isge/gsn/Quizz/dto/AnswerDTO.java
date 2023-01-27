package com.isge.gsn.Quizz.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDTO {
    private long id;
    private String content;

    private QuestionDTO questionDTO;
}
