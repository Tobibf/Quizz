package com.isge.gsn.Quizz.restControllers;

import com.isge.gsn.Quizz.dto.AnswerDTO;
import com.isge.gsn.Quizz.dto.QuestionDTO;
import com.isge.gsn.Quizz.models.Answer;
import com.isge.gsn.Quizz.services.AnswersService;
import com.isge.gsn.Quizz.utils.DataMapping;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping
    ResponseEntity<List<AnswerDTO>> findAll() {

        List<Answer> answers = answersService.allAnswers();

        List<AnswerDTO> results = new ArrayList<>();
        for (Answer answer : answers) {
            results.add(DataMapping.toAnswerDTO(answer));
        }

        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    /*
    * Get all answers which belongs to a specific question
    * */
    @PostMapping("/byQuestion")
    ResponseEntity<List<AnswerDTO>> answersByQuestion(@RequestBody QuestionDTO questionDTO) {

        List<Answer> answers = answersService.answersByQuestion(DataMapping.toQuestion(questionDTO));

        List<AnswerDTO> results = new ArrayList<>();
        for (Answer answer : answers) {
            results.add(DataMapping.toAnswerDTOWithOutQuestion(answer));
        }

        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping("/add")
    ResponseEntity<String> create(@RequestBody AnswerDTO answerDTO) {

        String message= answersService.createAnswer(DataMapping.toAnswer(answerDTO));

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/view/{id}")
    ResponseEntity<AnswerDTO> read(@PathVariable long id) {

        AnswerDTO questionDTO = DataMapping.toAnswerDTO(answersService.readAnswer(id));

        return new ResponseEntity<>(questionDTO, HttpStatus.OK);
    }

    @PutMapping("/update")
    ResponseEntity<String> update(@RequestBody AnswerDTO answerDTO) {

        String message = answersService.updateAnswer(DataMapping.toAnswer(answerDTO));

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> delete(@PathVariable long id) {

        String message = answersService.deleteAnswer(id);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
