package com.project.authentication.service;

import com.project.authentication.core.exceptions.NotFoundRegisterException;
import com.project.authentication.core.exceptions.UnauthorizedException;
import com.project.authentication.core.exceptions.UserBlockedException;
import com.project.authentication.model.AuthenticationAttempt;
import src.dto.auth.AuthResponseDTO;
import src.dto.auth.LoginRequestDTO;
import src.dto.auth.RegisterRequestDTO;
import com.project.authentication.model.User;
import com.project.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class AuthService {

    @Autowired
    AuthenticationAttemptService authenticationAttemptService;

    @Autowired
    UserRepository userRepository;

    public AuthResponseDTO register(RegisterRequestDTO dto){
        boolean userExists = userRepository.existsByUsername(dto.getUsername());

        if(!dto.validPassword()) throw new IllegalArgumentException("Senhas não conferem.");
        if(userExists) throw new IllegalArgumentException("Usuário já cadastrado com esse username.");

        User userCreated = userRepository.save(new User(dto));
        return new AuthResponseDTO(userCreated.getUsername(), "Usuário criado com sucesso.");
    }

    public AuthResponseDTO login(LoginRequestDTO dto, String ipAdresss){
        User user = userRepository.findByUsername(dto.getUsername());
        AuthResponseDTO authReponse = new AuthResponseDTO();

        validateUser(user);

        if(!Objects.equals(user.getPassword(), dto.getPassword())){
            int attempts = setAuthenticationAttempts(user, dto.getPassword(), ipAdresss);
            switch (attempts) {
                case 1 -> authReponse.setMessage("A senha está errada, tem 2 tentativas restantes.");
                case 2 -> authReponse.setMessage("A senha está errada, tem 1 tentativa.");
                case 3 -> authReponse.setMessageAndUserBlocked("A senha está errada, usuário bloqueado.");
            }
        } else {
            authReponse.setUsername(user.getUsername());
            authReponse.setMessage("A senha está correta, você está logado");
        }

        return authReponse;
    }

    private void validateUser(User user){
        LocalDateTime now = LocalDateTime.now();

        if(user == null) throw new NotFoundRegisterException("Usuário não encontrado.");

        LocalDateTime blockedAt = user.getBlockDate() != null ? user.getBlockDate().plusMinutes(1) : null;

        if(blockedAt != null){
            boolean canUnlock = now.isAfter(blockedAt) || now.isEqual(blockedAt);

            if(!canUnlock) throw new UserBlockedException("Usuário está bloqueado.");

            user.setBlockDate(null);
            userRepository.save(user);
            authenticationAttemptService.validateAllAttempts(user.getUsername());
        }
    }

    private void validateUser(String username){
        validateUser(userRepository.findByUsername(username));
    }

    private int setAuthenticationAttempts(User user, String passwordFail, String ipAddress){
        LocalDateTime now = LocalDateTime.now();
        saveNewAttemptFailed(user.getUsername(), passwordFail, ipAddress, now);

        int attemptsAtInterval = authenticationAttemptService.attemptsAtInterval(user.getUsername(), ipAddress, 15L);

        if(attemptsAtInterval >= 3){
            user.setBlockDate(now);
            userRepository.save(user);
        }

        return attemptsAtInterval;
    }

    private void saveNewAttemptFailed(String username, String passwordFail, String ipAddress, LocalDateTime time){
        AuthenticationAttempt authenticationAttempt = new AuthenticationAttempt(username, passwordFail, ipAddress, time);
        authenticationAttemptService.saveNewAttempt(authenticationAttempt);
    }

    public Map<String, Object> validUsername(String username){
        Map<String, Object> response = new HashMap<>();
        Boolean isValid = userExistsByUsername(username);

        if(!isValid){
            response.put("message", "Não foi encontrado registro para esse username.");
        }

        validateUser(username);

        response.put("isValid", isValid);

        return response;
    }

    private Boolean userExistsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

}
