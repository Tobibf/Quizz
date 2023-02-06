package com.isge.gsn.Quizz.repositories;

import com.isge.gsn.Quizz.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM users u WHERE u.role_id = 1")
    Optional<User> findAdmin();

    Optional<User> findByUserNameAndPassWord(String username, String password);
}
