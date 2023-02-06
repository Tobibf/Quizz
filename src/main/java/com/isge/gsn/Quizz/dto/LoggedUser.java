package com.isge.gsn.Quizz.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoggedUser {
    private long id;
    private String fullName;
    private String token;
}
