package com.isge.gsn.Quizz.utils;

import com.isge.gsn.Quizz.dto.*;
import com.isge.gsn.Quizz.models.*;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class DataMapping {

  //  @Autowired
  //  private static PasswordEncoder passwordEncoder;


    /*
    * Convert Objet RoleDTO to Role
    * */
    public static Role toRole(RoleDTO roleDTO){
        if(roleDTO == null)    return null;

        Role role = new Role();

        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());

        return role;
    }

    /*
     * Convert Objet Role to RoleDTO
     * */
    public static RoleDTO toRoleDTO(Role role){
        if(null == role)    return null;

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());

        return roleDTO;
    }

    /*
    * Convert UserDTO to User
    * */
    public static User toUser(UserDTO userDTO){
        if (userDTO == null)   return null;

        User user = new User();

        user.setFullName(userDTO.getFullName());
        user.setUserName(userDTO.getUsername());
     //   user.setPassWord(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(toRole(userDTO.getRoleDTO()));

        return user;
    }

    /*
     * Convert User to UserDTO
     * */
    public static UserDTO toUserDTO(User user){
        if (null == user) return null;

        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setFullName(user.getFullName());
        userDTO.setUsername(user.getUserName());
        userDTO.setPassword(null);
        userDTO.setRoleDTO(toRoleDTO(user.getRole()));

        return userDTO;
    }

    /*
    * Convert Objet GameDTO to Game
    * */
    public static Game toGame(GameDTO gameDTO){
        if(gameDTO == null)   return null;

        Game game = new Game();

        game.setId(gameDTO.getId());
        game.setScore(0);   //Because when we wanted to create a new game the score will be calculated
        game.setUser(toUser(gameDTO.getUserDTO()));

        return game;
    }

    /*
    * Convert Objet Game to GameDTO
    * */
    public static GameDTO toGameDTO(Game game){
        if(null == game)    return null;

        GameDTO gameDTO = new GameDTO();

        gameDTO.setId(game.getId());
        gameDTO.setScore(game.getScore());
        gameDTO.setUserDTO(toUserDTO(game.getUser()));

        return gameDTO;
    }

    /*
     * Convert Objet Game to GameDTO
     * */
    public static GameDTO toGameDTOWithQuestion(Game game){
        if(null == game)    return null;

        GameDTO gameDTO = new GameDTO();

        gameDTO.setId(game.getId());
        gameDTO.setScore(game.getScore());
        gameDTO.setUserDTO(toUserDTO(game.getUser()));

        return gameDTO;
    }


    /*
    * Convert Objet QuestionDTO to Question
    * */
    public static Question toQuestion(QuestionDTO questionDTO){
        if(questionDTO == null)    return null;

        Question question = new Question();

        question.setId(questionDTO.getId());
        question.setContent(questionDTO.getContent());
        question.setAnswer(questionDTO.getAnswer());

        return question;
    }

    /*
    * Convert Objet Question to QuestionDTO
    * */
    public static QuestionDTO toQuestionDTO(Question question){
        if(null == question)    return null;

        QuestionDTO questionDTO = new QuestionDTO();

        questionDTO.setId(question.getId());
        questionDTO.setContent(question.getContent());
        questionDTO.setAnswer(question.getAnswer());
        questionDTO.setAnswers(toAnswersDTOWithOutQuestion(question.getAnswers()));

        return questionDTO;
    }

    /*
     * Convert Objet Question to QuestionDTO with out answers
     * */
    public static QuestionDTO toQuestionDTOWithOutAnswers(Question question){
        if(null == question)    return null;

        QuestionDTO questionDTO = new QuestionDTO();

        questionDTO.setId(question.getId());
        questionDTO.setContent(question.getContent());
        questionDTO.setAnswer(question.getAnswer());

        return questionDTO;
    }

    /*
    * Convert Objet AnswerDTO to Answer
    **/
    public static Answer toAnswer(AnswerDTO answerDTO){
        if(answerDTO == null)   return null;

        Answer answer = new Answer();

        answer.setId(answerDTO.getId());
        answer.setContent(answerDTO.getContent());
        answer.setQuestion(toQuestion(answerDTO.getQuestionDTO()));

        return answer;
    }

    /*
    * Convert Objet Answer to AnswerDTO
    * */
    public static AnswerDTO toAnswerDTO(Answer answer){
        if(null == answer)  return null;

        AnswerDTO answerDTO = new AnswerDTO();

        answerDTO.setId(answer.getId());
        answerDTO.setContent(answer.getContent());
        answerDTO.setQuestionDTO(toQuestionDTOWithOutAnswers(answer.getQuestion()));

        return answerDTO;
    }

    /*
     * Convert Objet Answer to AnswerDTO WithOut Question
     * */
    public static AnswerDTO toAnswerDTOWithOutQuestion(Answer answer){
        if(null == answer)  return null;

        AnswerDTO answerDTO = new AnswerDTO();

        answerDTO.setId(answer.getId());
        answerDTO.setContent(answer.getContent());

        return answerDTO;
    }

    /*
     * To convert a list of Answer to a list of AnswerDTO
     * */
    private static List<AnswerDTO> toAnswersDTOWithOutQuestion(List<Answer> answers) {
        List<AnswerDTO> answerDTOS = new ArrayList<>();
        for (Answer answer : answers){
            answerDTOS.add(toAnswerDTOWithOutQuestion(answer));
        }

        return answerDTOS;
    }
}
