package com.isge.gsn.Quizz.services;

import com.isge.gsn.Quizz.models.*;
import com.isge.gsn.Quizz.repositories.GamesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class GamesService {

    private final GamesRepository gamesRepository;
    private final UsersService usersService;
    private final QuestionsService questionsService;
    private final MomentsService momentsService;

    public List<Game> allGames() {
        return gamesRepository.findAll();
    }

    /*
     * Get the list of Game parts of a specific user
     * */
    public List<Game> gamesByUser(@NotNull User user) {

        if (usersService.userExist(user.getId())) {
            return gamesRepository.findByUser(user.getId());
        }
        return null;
    }

    /*
     * Create a new Game And Save Questions in moments
     * */
    public Game newGamePart(Game game) {
        try {

            Game newGame = gamesRepository.save(game);

            // Make bridge of each 5 question and the new Game
            for (Question question : questionsService.randQuestions()) {
                Moment moment = new Moment();
                moment.setGame(game);
                moment.setQuestion(question);

                momentsService.save(moment);
            }

            return newGame;
        } catch (Exception exception) {
            log.info(exception.toString());
            return null;
        }
    }

    public Game readGame(long id) {
        return gamesRepository.findById(id).orElse(null);
    }

    public Game readGameForUser(long id, @NotNull User user) {

        if (usersService.userExist(user.getId())) {
            return gamesRepository.findGameByUser(id, user.getId()).orElse(null);
        }
        return null;
    }

    public Game correctGame(Game oldGame) {
        try {
            List<String> answers = oldGame.getAnswers();
            double score = 0;

            Game newGame = gamesRepository.findById(oldGame.getId()).orElse(null);

            int index = 0;
            for (Question question : questionsService.gameQuestions(oldGame.getId())) {
                //We suppose each good answer brings 1 point
                if (question.getAnswer().equals(answers.get(index))) {
                    score++;
                }
                index++;
            }

            // Update Score and save responses of the users
            newGame.setScore(score);
            newGame.setAnswers(answers);

            return gamesRepository.save(newGame);
        } catch (Exception exception) {
            log.info(exception.toString());
            return null;
        }
    }

    public String deleteGame(long id, User user) {
        try {
            User trueUser = usersService.readUser(user.getId());
            Game deletingGame = readGame(id);

            if (trueUser.getRole().getName().equals("Gamer") &&
                    deletingGame.getUser().getId() == trueUser.getId()) {
                gamesRepository.deleteById(id);
                return "Game deleted successfully";
            }
            return "Unauthorized";
        } catch (Exception e) {
            return "Unsuccessful deleting";
        }
    }
}
