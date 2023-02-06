package com.isge.gsn.Quizz.utils;

import com.isge.gsn.Quizz.dto.GameDTO;
import com.isge.gsn.Quizz.dto.QuestionDTO;
import com.isge.gsn.Quizz.dto.UserDTO;
import com.isge.gsn.Quizz.models.Game;
import com.isge.gsn.Quizz.models.User;
import com.isge.gsn.Quizz.services.QuestionsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class GameMapping {
    private final QuestionsService questionsService;
    @Autowired
    private final Crypt crypt;

    /*
     * Convert Objet UserDTO to objet User with the password
     * */
    public User toUserWithPassword(UserDTO userDTO) {
        if (userDTO == null) return null;

        User user = new User();

        user.setId(userDTO.getId());
        user.setFullName(userDTO.getFullName());
        user.setUserName(userDTO.getUsername());
        user.setPassWord(crypt.encryption(userDTO.getPassword()));
        user.setRole(DataMapping.toRole(userDTO.getRoleDTO()));

        return user;
    }

    /*
     * Convert Objet Game to GameDTO and Add questions with answers
     * */
    public GameDTO toGameDTOWithQuestions(Game game) {
        if (null == game) return null;

        GameDTO gameDTO = new GameDTO();

        gameDTO.setId(game.getId());
        gameDTO.setScore(game.getScore());
        gameDTO.setAnswersDTO(game.getAnswers());
        gameDTO.setUserDTO(DataMapping.toUserDTO(game.getUser()));

        //Get 5 questions of this Game

        List<QuestionDTO> gameQuestions = DataMapping.toQuestionDTOS(questionsService.gameQuestions(game.getId()));
        gameDTO.setQuestionDTOS(gameQuestions);

        return gameDTO;
    }


    /*
     * Convert a list of Games to a list of GameDTO
     * */
    public @NotNull List<GameDTO> toGameDTOSWithQuestions(@NotNull List<Game> games) {
        List<GameDTO> gameDTOS = new ArrayList<>();

        for (Game game : games) {
            gameDTOS.add(this.toGameDTOWithQuestions(game));
        }

        return gameDTOS;
    }
}
