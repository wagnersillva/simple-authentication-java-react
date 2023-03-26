package src.dto.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthResponseDTO {
    private String username;
    private String message;
    private Boolean success;
    private Boolean userBlocked = false;

    public AuthResponseDTO(String username, String message){
        this.username=username;
        this.message=message;
    }

    public Boolean getSuccess() {
        return username != null;
    }

    public void setMessageAndUserBlocked(String message){
        this.message = message;
        this.userBlocked = true;
    }

}
