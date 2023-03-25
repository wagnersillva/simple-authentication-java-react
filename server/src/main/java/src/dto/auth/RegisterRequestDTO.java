package src.dto.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Objects;

@Data
public class RegisterRequestDTO {

    @NotNull(message = "[Username] é obrigatório")
    private String username;

    @NotNull(message = "[Name] é obrigatório")
    private String name;

    @NotNull(message = "[password] é obrigatório")
    private String password;

    @NotNull(message = "[confirmPassword] é obrigatória")
    private String confirmPassword;

    public Boolean validPassword(){
        return Objects.equals(password, confirmPassword);
    }

}
