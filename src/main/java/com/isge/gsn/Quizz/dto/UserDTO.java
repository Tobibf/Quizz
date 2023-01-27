package com.isge.gsn.Quizz.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private long id;
    private String fullName;
    private String username;
    private String password;

    private RoleDTO roleDTO;
}
