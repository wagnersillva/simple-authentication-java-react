package com.project.authentication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_auth_attempts")
public class AuthenticationAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "attempt_time")
    private LocalDateTime attemptTime;

    @Column(name = "already_validated")
    private Boolean alreadyValidated = false;

    public AuthenticationAttempt(String username, String password, String ipAddress, LocalDateTime attemptTime){
        this.username=username;
        this.password=password;
        this.ipAddress=ipAddress;
        this.attemptTime=attemptTime;
    }

}
