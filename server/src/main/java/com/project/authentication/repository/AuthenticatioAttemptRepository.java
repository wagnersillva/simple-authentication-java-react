package com.project.authentication.repository;

import com.project.authentication.model.AuthenticationAttempt;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AuthenticatioAttemptRepository extends JpaRepository<AuthenticationAttempt, Id> {

    List<AuthenticationAttempt> findAllByUsernameAndIpAddressAndAttemptTimeAfterAndAlreadyValidatedIsFalse(String username, String ipAddress, LocalDateTime time);

    List<AuthenticationAttempt> findAllByUsernameAndAlreadyValidatedIsFalse(String username);

}
