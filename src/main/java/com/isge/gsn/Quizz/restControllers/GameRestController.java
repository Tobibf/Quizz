package com.isge.gsn.Quizz.restControllers;

import com.isge.gsn.Quizz.dto.GameDTO;
import com.isge.gsn.Quizz.dto.QuestionDTO;
import com.isge.gsn.Quizz.dto.UserDTO;
import com.isge.gsn.Quizz.services.GamesService;
import com.isge.gsn.Quizz.services.QuestionsService;
import com.isge.gsn.Quizz.utils.DataMapping;
import com.isge.gsn.Quizz.utils.GameMapping;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/games")
@Slf4j
@AllArgsConstructor
public class GameRestController {
    private final GamesService gamesService;

    private final QuestionsService questionsService;
    private final GameMapping gameMapping;

    /*
    * List all Games
    * */
    @GetMapping
    ResponseEntity<List<GameDTO>> allGames() {

        List<GameDTO> returnGamesDTO = gameMapping.toGameDTOSWithQuestions(gamesService.allGames());

        return new ResponseEntity<>(returnGamesDTO, HttpStatus.OK);
    }

    /*
    * Create a new Game
    * */
    @PostMapping("/newGame")
    ResponseEntity<GameDTO> newGame(@RequestBody GameDTO gameDTO) {

        GameDTO returnGameDTO = gameMapping.toGameDTOWithQuestions(gamesService.newGamePart(DataMapping.toGame(gameDTO)));

        return new ResponseEntity<>(returnGameDTO, HttpStatus.OK);
    }

    /*
    * To send the answers of Gamer
    * */
    @PutMapping("/sendGame")
    ResponseEntity<GameDTO> sendGame(@RequestBody GameDTO gameDTO) {

        GameDTO returnGameDTO = gameMapping.toGameDTOWithQuestions(gamesService.correctGame(DataMapping.toGame(gameDTO)));

        return new ResponseEntity<>(returnGameDTO, HttpStatus.OK);
    }

    /*
    * Get the list of Game of a specific Gamer
    * */
    @PostMapping("/gamesByUser")
    ResponseEntity<List<GameDTO>> gamesByUser(@RequestBody UserDTO userDTO) {

        List<GameDTO> returnGamesDTO = gameMapping.toGameDTOSWithQuestions(gamesService.gamesByUser(DataMapping.toUser(userDTO)));

        return new ResponseEntity<>(returnGamesDTO, HttpStatus.OK);
    }

    @GetMapping("/test")
    ResponseEntity<List<QuestionDTO>> test() {

        List<QuestionDTO> returnGamesDTO = DataMapping.toQuestionDTOS(questionsService.test());

        return new ResponseEntity<>(returnGamesDTO, HttpStatus.OK);
    }

    /*
    * Read a Game
    * */
    @GetMapping("/view/{id}")
    ResponseEntity<GameDTO> read(@PathVariable long id){

        GameDTO readGameDTO = gameMapping.toGameDTOWithQuestions(gamesService.readGame(id));

        return new ResponseEntity<>(readGameDTO, HttpStatus.OK);
    }

    /*
    * Read a game which belong to a user
    * */
    @PostMapping("/view/{id}")
    ResponseEntity<GameDTO> readForUser(@PathVariable long id, @RequestBody UserDTO userDTO){

        GameDTO readGameDTO = gameMapping.toGameDTOWithQuestions(gamesService.readGameForUser(id, DataMapping.toUser(userDTO)));

        return new ResponseEntity<>(readGameDTO, HttpStatus.OK);
    }

    /*
    * Use to cancel a part for a user
    * */
    @DeleteMapping("/cancelGame/{id}")
    ResponseEntity<String> cancelGame(@PathVariable long id, @RequestBody UserDTO userDTO){

        String message  = gamesService.deleteGame(id, DataMapping.toUser(userDTO));

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
