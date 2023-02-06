package com.isge.gsn.Quizz.restControllers;

import com.isge.gsn.Quizz.dto.QuestionDTO;
import com.isge.gsn.Quizz.models.Question;
import com.isge.gsn.Quizz.services.QuestionsService;
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
@RequestMapping(value = "/api/questions")
@Slf4j
@AllArgsConstructor
/*CRUD and Listing functions*/
public class QuestionRestController {
    @Autowired
    private JwtUtils jwtUtils;
    private final QuestionsService questionService;

    @GetMapping
    ResponseEntity<List<QuestionDTO>> findAll(@RequestHeader(value = "token", defaultValue = "") String token) {
        String role = jwtUtils.verifyJwt(token);

        if (role.equals("Admin")) {
            List<Question> questions = questionService.allQuestions();

            List<QuestionDTO> results = new ArrayList<>();
            for (Question question : questions) {
                results.add(DataMapping.toQuestionDTO(question));
            }

            return new ResponseEntity<>(results, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @PostMapping("/add")
    ResponseEntity<String> create(@RequestBody QuestionDTO questionDTO, @RequestHeader(value = "token", defaultValue = "") String token) throws Exception {
        String role = jwtUtils.verifyJwt(token);

        if (role.equals("Admin")) {
            String message = questionService.createQuestion(DataMapping.toQuestion(questionDTO));

            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @GetMapping("/view/{id}")
    ResponseEntity<QuestionDTO> read(@PathVariable long id, @RequestHeader(value = "token", defaultValue = "") String token) throws Exception {
        String role = jwtUtils.verifyJwt(token);

        if (role.equals("Admin")) {

            QuestionDTO questionDTO = DataMapping.toQuestionDTO(questionService.readQuestion(id));

            return new ResponseEntity<>(questionDTO, HttpStatus.OK);

        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @PutMapping("/update")
    ResponseEntity<String> update(@RequestBody QuestionDTO questionDTO, @RequestHeader(value = "token", defaultValue = "") String token) throws Exception {
        String role = jwtUtils.verifyJwt(token);

        if (role.equals("Admin")) {
            String message = questionService.updateQuestion(DataMapping.toQuestion(questionDTO));

            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> delete(@PathVariable long id, @RequestHeader(value = "token", defaultValue = "") String token) throws Exception {
        String role = jwtUtils.verifyJwt(token);

        if (role.equals("Admin")) {

            String message = questionService.deleteQuestion(id);

            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }
}
