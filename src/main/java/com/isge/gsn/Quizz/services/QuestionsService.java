package com.isge.gsn.Quizz.services;

import com.isge.gsn.Quizz.models.Question;
import com.isge.gsn.Quizz.repositories.QuestionsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class QuestionsService {
    private final QuestionsRepository questionsRepository;

    public List<Question> allQuestions() {
        try {
            return questionsRepository.findAll();
        } catch (Exception exception) {
            return null;
        }
    }

    /*
    * Get 5 questions by random
    * */
    public List<Question> randQuestions() {
        try {
            return questionsRepository.randomQuestions();
        } catch (Exception exception) {
            return null;
        }
    }

    /*
    * Use to create new Question during creating of answer
    * */
    public Question save(Question question) {
        return questionsRepository.save(question);
    }

    public String createQuestion(Question question) {
        try {
            questionsRepository.save(question);
            return "Question " + question.getContent() + " created successfully";

        } catch (Exception e) {
            return "Unsuccessful creating";
        }
    }

    public Question readQuestion(long id) {
        return questionsRepository.findById(id).orElse(null);
    }

    public String updateQuestion(Question question) {
        try {
            if (questionsRepository.existsById(question.getId())) {

                Question newQuestion = questionsRepository.findById(question.getId()).orElse(null);

                newQuestion.setContent(question.getContent());
                newQuestion.setAnswer(question.getAnswer());

                questionsRepository.save(newQuestion);
                return "Question " + newQuestion.getContent() + " updated successfully";
            }
            return "Question not exist";
        } catch (Exception e) {
            return "Unsuccessful updating";
        }
    }

    public String deleteQuestion(Long id) {
        try {
            questionsRepository.deleteById(id);
            return "Question deleted successfully";
        } catch (Exception e) {
            return "Unsuccessful deleting";
        }
    }
}
