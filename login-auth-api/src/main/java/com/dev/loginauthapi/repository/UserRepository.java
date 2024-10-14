package com.dev.loginauthapi.repository;

import com.dev.loginauthapi.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
