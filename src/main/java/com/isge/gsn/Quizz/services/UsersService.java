package com.isge.gsn.Quizz.services;

import com.isge.gsn.Quizz.dto.LoggedUser;
import com.isge.gsn.Quizz.models.Role;
import com.isge.gsn.Quizz.models.User;
import com.isge.gsn.Quizz.repositories.UsersRepository;
import com.isge.gsn.Quizz.utils.DataMapping;
import com.isge.gsn.Quizz.utils.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UsersService {
    private final UsersRepository usersRepository;
    private final RolesService rolesService;

    @Autowired
    private JwtUtils jwtUtils;

    public List<User> userList() {
        try {
            return usersRepository.findAll();
        } catch (Exception exception) {
            log.info(exception.toString());
            return null;
        }
    }

    /*
     * Create Users with Default Gamer profile
     * */
    public String createGamer(User user) {
        try {
            Role gameRole = rolesService.readRole(2);
            user.setRole(gameRole);

            usersRepository.save(user);
            return "User " + user.getFullName() + " created successfully";
        } catch (Exception e) {
            return "Unsuccessful creation";
        }
    }

    /*
     * Create only one Admin
     * */
    public String createAdmin(User user) {
        try {
            if (usersRepository.findAdmin().isEmpty()) {
                Role gameRole = rolesService.readRole(1);
                user.setRole(gameRole);

                usersRepository.save(user);
                return "User " + user.getFullName() + " created successfully";
            }
            return "Admin already exist";
        } catch (Exception e) {
            return "Unsuccessful creating";
        }
    }

    public User readUser(long id) {
        return usersRepository.findById(id).orElse(null);
    }

    public String updateUser(@NotNull User oldUser) {
        try {
            if (usersRepository.existsById(oldUser.getId())) {
                /*
                 * Get the oldUser to database and update attribute FullName
                 * */
                User newUser = usersRepository.findById(oldUser.getId()).orElse(null);
                newUser.setFullName(oldUser.getFullName());
                newUser.setUserName(oldUser.getUserName());
                newUser.setPassWord(oldUser.getPassWord());

                usersRepository.save(newUser);
                return "User " + newUser.getFullName() + " updated successfully";
            }
            return "User not exist";
        } catch (Exception e) {
            return "Unsuccessful updating";
        }
    }

    public String deleteUser(long id) {
        try {
            usersRepository.deleteById(id);
            return "User deleted successfully";
        } catch (Exception e) {
            return "Unsuccessful deleting";
        }
    }

    public Boolean userExist(long id) {
        return usersRepository.existsById(id);
    }

    public LoggedUser login(@NotNull User user) {
        try {

            //Try to get user
            Optional<User> trueUser = usersRepository.findByUserNameAndPassWord(user.getUserName(), user.getPassWord());

            //Verify is user with these credentials exists
            if (trueUser.isEmpty()) {
                return null;
            }
            //Generate Jwt
            String token = jwtUtils.generateJWT(trueUser.get());

            return DataMapping.toLoggedUser(trueUser.get(), token);
        } catch (Exception e) {
            log.info(e.toString());
            return null;
        }

    }

}
