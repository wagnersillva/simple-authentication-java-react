package src.dto.auth;

import lombok.Data;

@Data
public class LoginRequestDTO {

    private String username;
    private String password;

}
