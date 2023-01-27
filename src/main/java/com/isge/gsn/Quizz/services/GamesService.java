package com.isge.gsn.Quizz.services;

import com.isge.gsn.Quizz.models.Game;
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

    /*
    * Get the list of Game parts of a specific user
    * */
    public List<Game> gamesByUser(User user){
        Optional<User> realUser = usersService.findByFullName(user.getFullName());
        if(realUser.isPresent()) {
            return gamesRepository.findByUser(realUser.get().getId());
        }
        return null;
    }

    public Game newGamePart(Game game){
        try{
            return gamesRepository.save(game);
        }catch (Exception exception){
            log.info(exception.toString());
            return null;
        }
    }
}
