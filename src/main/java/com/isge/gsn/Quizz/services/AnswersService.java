package com.isge.gsn.Quizz.services;

import com.isge.gsn.Quizz.models.Answer;
import com.isge.gsn.Quizz.models.Question;
import com.isge.gsn.Quizz.repositories.AnswersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AnswersService {
    private final AnswersRepository answersRepository;
    private final QuestionsService questionsService;

    public List<Answer> allAnswers() {
        try {
            return answersRepository.findAll();
        } catch (Exception exception) {
            return null;
        }
    }
    public List<Answer> answersByQuestion(Question question) {
        try {
            return answersRepository.findByQuestion(question.getId());
        } catch (Exception exception) {
            return null;
        }
    }
    public String createAnswer(Answer answer) {
        try {
            Optional<Question> optionalQuestion = Optional.ofNullable(questionsService.readQuestion(answer.getQuestion().getId()));
            Question question;
            question = optionalQuestion.orElseGet(() -> questionsService.save(answer.getQuestion()));

            answer.setQuestion(question);

            answersRepository.save(answer);
            return "Answer " + answer.getContent() + " created successfully";

        } catch (Exception e) {
            return "Unsuccessful creating";
        }
    }

    public Answer readAnswer(long id) {
        return answersRepository.findById(id).orElse(null);
    }

    public String updateAnswer(Answer answer) {
        try {
            if (answersRepository.existsById(answer.getId())) {

                Answer newAnswer = answersRepository.findById(answer.getId()).orElse(null);

                newAnswer.setContent(answer.getContent());

                answersRepository.save(newAnswer);
                return "Answer " + newAnswer.getContent() + " updated successfully";
            }
            return "Answer not exist";
        } catch (Exception e) {
            return "Unsuccessful updating";
        }
    }

    public String deleteAnswer(Long id) {
        try {
            answersRepository.deleteById(id);
            return "Answer deleted successfully";
        } catch (Exception e) {
            return "Unsuccessful deleting";
        }
    }
}
