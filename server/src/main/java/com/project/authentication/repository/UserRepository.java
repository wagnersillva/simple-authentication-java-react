package com.project.authentication.repository;

import com.project.authentication.model.User;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Id> {

    Boolean existsByUsername(String username);

    User findByUsername(String username);

}
