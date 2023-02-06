package com.isge.gsn.Quizz.restControllers;

import com.isge.gsn.Quizz.dto.GameDTO;
import com.isge.gsn.Quizz.dto.UserDTO;
import com.isge.gsn.Quizz.services.GamesService;
import com.isge.gsn.Quizz.services.QuestionsService;
import com.isge.gsn.Quizz.utils.DataMapping;
import com.isge.gsn.Quizz.utils.GameMapping;
import com.isge.gsn.Quizz.utils.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private JwtUtils jwtUtils;

    /*
     * List all Games
     * */
    @GetMapping
    ResponseEntity<List<GameDTO>> allGames(@RequestHeader(value = "token", defaultValue = "") String token) {
        String role = jwtUtils.verifyJwt(token);

        if (role.equals("Admin")) {

            List<GameDTO> returnGamesDTO = gameMapping.toGameDTOSWithQuestions(gamesService.allGames());

            return new ResponseEntity<>(returnGamesDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    /*
     * Create a new Game
     * */
    @PostMapping("/newGame")
    ResponseEntity<GameDTO> newGame(@RequestBody GameDTO gameDTO, @RequestHeader(value = "token", defaultValue = "") String token) {
        String role = jwtUtils.verifyJwt(token);

        if (role.equals("Gamer")) {

            GameDTO returnGameDTO = gameMapping.toGameDTOWithQuestions(gamesService.newGamePart(DataMapping.toGame(gameDTO)));

            return new ResponseEntity<>(returnGameDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    /*
     * To send the answers of Gamer
     * */
    @PutMapping("/sendGame")
    ResponseEntity<GameDTO> sendGame(@RequestBody GameDTO gameDTO, @RequestHeader(value = "token", defaultValue = "") String token) {
        String role = jwtUtils.verifyJwt(token);

        if (role.equals("Gamer")) {

            GameDTO returnGameDTO = gameMapping.toGameDTOWithQuestions(gamesService.correctGame(DataMapping.toGame(gameDTO)));

            return new ResponseEntity<>(returnGameDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    /*
     * Get the list of Game of a specific Gamer
     * */
    @PostMapping("/gamesByUser")
    ResponseEntity<List<GameDTO>> gamesByUser(@RequestBody UserDTO userDTO, @RequestHeader(value = "token", defaultValue = "") String token) {
        String role = jwtUtils.verifyJwt(token);

        if (role.equals("Admin") || role.equals("Gamer")) {

            List<GameDTO> returnGamesDTO = gameMapping.toGameDTOSWithQuestions(gamesService.gamesByUser(DataMapping.toUser(userDTO)));

            return new ResponseEntity<>(returnGamesDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }


    /*
     * Read a Game
     * */
    @GetMapping("/view/{id}")
    ResponseEntity<GameDTO> read(@PathVariable long id, @RequestHeader(value = "token", defaultValue = "") String token) {
        String role = jwtUtils.verifyJwt(token);

        if (role.equals("Admin") || role.equals("Gamer")) {

            GameDTO readGameDTO = gameMapping.toGameDTOWithQuestions(gamesService.readGame(id));

            return new ResponseEntity<>(readGameDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    /*
     * Read a game which belong to a user
     * */
    @PostMapping("/view/{id}")
    ResponseEntity<GameDTO> readForUser(@PathVariable long id, @RequestBody UserDTO userDTO, @RequestHeader(value = "token", defaultValue = "") String token) {
        String role = jwtUtils.verifyJwt(token);

        if (role.equals("Admin") || role.equals("Gamer")) {

            GameDTO readGameDTO = gameMapping.toGameDTOWithQuestions(gamesService.readGameForUser(id, DataMapping.toUser(userDTO)));

            return new ResponseEntity<>(readGameDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    /*
     * Use to cancel a part for a user
     * */
    @DeleteMapping("/cancelGame/{id}")
    ResponseEntity<String> cancelGame(@PathVariable long id, @RequestBody UserDTO userDTO, @RequestHeader(value = "token", defaultValue = "") String token) {
        String role = jwtUtils.verifyJwt(token);

        if (role.equals("Admin") || role.equals("Gamer")) {

            String message = gamesService.deleteGame(id, DataMapping.toUser(userDTO));

            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }
}
