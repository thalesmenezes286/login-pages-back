package com.dev.loginauthapi.repositories;

import com.dev.loginauthapi.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String login);
}