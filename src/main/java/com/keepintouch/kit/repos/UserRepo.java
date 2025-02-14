package com.keepintouch.kit.repos;

import com.keepintouch.kit.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    // extra methods for db relations
    // custom query methods
    // custom finder methods
    Optional<Object> findByEmail(String email);
}
