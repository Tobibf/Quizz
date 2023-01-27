package com.isge.gsn.Quizz.restControllers;

import com.isge.gsn.Quizz.dto.QuestionDTO;
import com.isge.gsn.Quizz.models.Question;
import com.isge.gsn.Quizz.services.QuestionsService;
import com.isge.gsn.Quizz.utils.DataMapping;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/questions")
@Slf4j
@AllArgsConstructor
public class QuestionRestController {

    private final QuestionsService questionService;

    @GetMapping
    ResponseEntity<List<QuestionDTO>> findAll() {

        List<Question> questions = questionService.allQuestions();

        List<QuestionDTO> results = new ArrayList<>();
        for (Question question : questions) {
            results.add(DataMapping.toQuestionDTO(question));
        }

        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping("/add")
    ResponseEntity<String> create(@RequestBody QuestionDTO questionDTO) {

        String message = questionService.createQuestion(DataMapping.toQuestion(questionDTO));

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/view/{id}")
    ResponseEntity<QuestionDTO> read(@PathVariable long id) {

        QuestionDTO questionDTO = DataMapping.toQuestionDTO(questionService.readQuestion(id));

        return new ResponseEntity<>(questionDTO, HttpStatus.OK);
    }

    @PutMapping("/update")
    ResponseEntity<String> update(@RequestBody QuestionDTO questionDTO) {

        String message = questionService.updateQuestion(DataMapping.toQuestion(questionDTO));

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> delete(@PathVariable long id) {

        String message = questionService.deleteQuestion(id);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
