package com.isge.gsn.Quizz.services;

import com.isge.gsn.Quizz.models.Role;
import com.isge.gsn.Quizz.models.User;
import com.isge.gsn.Quizz.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UsersService {
    private final UsersRepository usersJPARepository;
    private final RolesService rolesService;

    public List<User> userList() {
        try {
            return usersJPARepository.findAll();
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

            usersJPARepository.save(user);
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
            if (usersJPARepository.findAdmin().isEmpty()) {
                Role gameRole = rolesService.readRole(1);
                user.setRole(gameRole);

                usersJPARepository.save(user);
                return "User " + user.getFullName() + " created successfully";
            }
            return "Admin already exist";
        } catch (Exception e) {
            return "Unsuccessful creating";
        }
    }

    public User readUser(Long id) {
        return usersJPARepository.findById(id).orElse(null);
    }

    public String updateUser(User oldUser) {
        try {
            if (usersJPARepository.existsById(oldUser.getId())) {
                /**
                 * Get the oldUser to database and update attribute FullName
                 * */
                User newUser = usersJPARepository.findById(oldUser.getId()).orElse(null);
                newUser.setFullName(oldUser.getFullName());

                usersJPARepository.save(newUser);
                return "User " + newUser.getFullName() + " updated successfully";
            }
            return "User not exist";
        } catch (Exception e) {
            return "Unsuccessful updating";
        }
    }

    public String deleteUser(long id) {
        try {
            usersJPARepository.deleteById(id);
            return "User deleted successfully";
        } catch (Exception e) {
            return "Unsuccessful deleting";
        }
    }

    public Optional<User> findByFullName(String fullName) {
        return usersJPARepository.findByFullName(fullName);
    }

    public Optional<User> getAdmin() {
        return usersJPARepository.findAdmin();
    }

    public Boolean userExist(long id){
        return usersJPARepository.existsById(id);
    }

}
