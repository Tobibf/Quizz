package com.isge.gsn.Quizz.restControllers;

import com.isge.gsn.Quizz.dto.AnswerDTO;
import com.isge.gsn.Quizz.dto.QuestionDTO;
import com.isge.gsn.Quizz.models.Answer;
import com.isge.gsn.Quizz.services.AnswersService;
import com.isge.gsn.Quizz.utils.DataMapping;
import com.isge.gsn.Quizz.utils.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/answers")
@Slf4j
@AllArgsConstructor
/*CRUD,Listing and other functions*/
public class AnswerRestController {
    private final AnswersService answersService;
    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping
    ResponseEntity<List<AnswerDTO>> findAll(@RequestHeader(value = "token", defaultValue = "") String token) {
        String role = jwtUtils.verifyJwt(token);

        if (role.equals("Admin")) {

            List<Answer> answers = answersService.allAnswers();

            List<AnswerDTO> results = new ArrayList<>();
            for (Answer answer : answers) {
                results.add(DataMapping.toAnswerDTO(answer));
            }

            return new ResponseEntity<>(results, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    /*
     * Get all answers which belongs to a specific question
     * */
    @PostMapping("/byQuestion")
    ResponseEntity<List<AnswerDTO>> answersByQuestion(@RequestBody QuestionDTO questionDTO, @RequestHeader(value = "token", defaultValue = "") String token) {
        String role = jwtUtils.verifyJwt(token);

        if (role.equals("Admin")) {

            List<Answer> answers = answersService.answersByQuestion(DataMapping.toQuestion(questionDTO));

            List<AnswerDTO> results = new ArrayList<>();
            for (Answer answer : answers) {
                results.add(DataMapping.toAnswerDTOWithOutQuestion(answer));
            }

            return new ResponseEntity<>(results, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @PostMapping("/add")
    ResponseEntity<String> create(@RequestBody AnswerDTO answerDTO, @RequestHeader(value = "token", defaultValue = "") String token) {
        String role = jwtUtils.verifyJwt(token);

        if (role.equals("Admin")) {

            String message = answersService.createAnswer(DataMapping.toAnswer(answerDTO));

            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @GetMapping("/view/{id}")
    ResponseEntity<AnswerDTO> read(@PathVariable long id, @RequestHeader(value = "token", defaultValue = "") String token) {
        String role = jwtUtils.verifyJwt(token);

        if (role.equals("Admin")) {

            AnswerDTO questionDTO = DataMapping.toAnswerDTO(answersService.readAnswer(id));

            return new ResponseEntity<>(questionDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @PutMapping("/update")
    ResponseEntity<String> update(@RequestBody AnswerDTO answerDTO, @RequestHeader(value = "token", defaultValue = "") String token) {
        String role = jwtUtils.verifyJwt(token);

        if (role.equals("Admin")) {

            String message = answersService.updateAnswer(DataMapping.toAnswer(answerDTO));

            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> delete(@PathVariable long id, @RequestHeader(value = "token", defaultValue = "") String token) {
        String role = jwtUtils.verifyJwt(token);

        if (role.equals("Admin")) {

            String message = answersService.deleteAnswer(id);

            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }
}
