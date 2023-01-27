package com.isge.gsn.Quizz.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameDTO {
    private long id;
    private double score;

    private UserDTO userDTO;
}
