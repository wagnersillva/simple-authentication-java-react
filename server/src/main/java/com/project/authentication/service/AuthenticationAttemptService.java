package com.project.authentication.service;

import com.project.authentication.model.AuthenticationAttempt;
import com.project.authentication.repository.AuthenticatioAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthenticationAttemptService {

    @Autowired
    AuthenticatioAttemptRepository repository;

    public int attemptsAtInterval(String username, String ipAddress, Long interval) {
        LocalDateTime time = LocalDateTime.now().minusMinutes(interval);
        List<AuthenticationAttempt> failedAttempts = findAllAttempsNotValidated(username, ipAddress, time);

        return failedAttempts.size();
    }

    public void validateAllAttempts(String username){
        List<AuthenticationAttempt> attempts = findAllAttempsNotValidated(username);

        for (AuthenticationAttempt attempt : attempts){
            attempt.setAlreadyValidated(true);
        }

        repository.saveAll(attempts);
    }

    public void saveNewAttempt(AuthenticationAttempt attempt){
        repository.save(attempt);
    }

    private List<AuthenticationAttempt> findAllAttempsNotValidated(String username, String ipAddress, LocalDateTime time){
        return repository.findAllByUsernameAndIpAddressAndAttemptTimeAfterAndAlreadyValidatedIsFalse(username, ipAddress, time);
    }

    private List<AuthenticationAttempt> findAllAttempsNotValidated(String username){
        return repository.findAllByUsernameAndAlreadyValidatedIsFalse(username);
    }

}
