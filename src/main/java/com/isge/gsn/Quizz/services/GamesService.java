package com.isge.gsn.Quizz.services;

import com.isge.gsn.Quizz.models.Game;
import com.isge.gsn.Quizz.models.Question;
import com.isge.gsn.Quizz.models.Role;
import com.isge.gsn.Quizz.models.User;
import com.isge.gsn.Quizz.repositories.GamesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    /*
     * Get the list of Game parts of a specific user
     * */
    public List<Game> gamesByUser(User user) {
        Optional<User> realUser = usersService.findByFullName(user.getFullName());
        if (realUser.isPresent()) {
            return gamesRepository.findByUser(realUser.get().getId());
        }
        return null;
    }

    public Game newGamePart(Game game) {
        try {
            Game newGame = gamesRepository.save(game);
            List<Question> randQuestions = questionsService.randQuestions();

            return newGame;
        } catch (Exception exception) {
            log.info(exception.toString());
            return null;
        }
    }

    public Game readGame(long id) {
        return gamesRepository.findById(id).orElse(null);
    }

    public Game readGameForUser(long id, User user) {
        Optional<User> realUser = usersService.findByFullName(user.getFullName());
        if (realUser.isPresent()) {
            return gamesRepository.findGameByUser(id, realUser.get().getId()).orElse(null);
        }
        return null;
    }

    public Game correctGame(Game game) {
        try {
            Game newGame = gamesRepository.save(game);
            List<Question> randQuestions = questionsService.randQuestions();

            return newGame;
        } catch (Exception exception) {
            log.info(exception.toString());
            return null;
        }
    }
}
