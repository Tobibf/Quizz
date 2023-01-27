package com.isge.gsn.Quizz.restControllers;

import com.isge.gsn.Quizz.dto.UserDTO;
import com.isge.gsn.Quizz.models.User;
import com.isge.gsn.Quizz.repositories.UsersRepository;
import com.isge.gsn.Quizz.services.UsersService;
import com.isge.gsn.Quizz.utils.DataMapping;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
@Slf4j
@AllArgsConstructor
public class UserRestController {
    private final UsersRepository usersJPARepository;

    private final UsersService usersService;

    @GetMapping
    ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = usersService.userList();
        List<UserDTO> results = new ArrayList<>();
        for (User user : users) {
            results.add(DataMapping.toUserDTO(user));
        }
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping("/createUser")
    ResponseEntity<String> createGamer(@RequestBody UserDTO userDTO) {
        String message = usersService.createGamer(DataMapping.toUser(userDTO));

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PostMapping("/createUser/admin/admin/Quiz")
    ResponseEntity<String> createAdmin(@RequestBody UserDTO userDTO) {
        String message = usersService.createAdmin(DataMapping.toUser(userDTO));

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/view/{id}")
    ResponseEntity<UserDTO> find(@PathVariable Long id) {

        UserDTO userModel = DataMapping.toUserDTO(usersService.readUser(id));

        return new ResponseEntity<>(userModel, HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    ResponseEntity<String> updateBook(@RequestBody UserDTO userDTO) {

        String message = usersService.updateUser(DataMapping.toUser(userDTO));

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> delete(@PathVariable Long id) {
        String message = usersService.deleteUser(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}