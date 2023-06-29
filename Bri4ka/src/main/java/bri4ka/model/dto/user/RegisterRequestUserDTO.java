package bri4ka.model.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class RegisterRequestUserDTO {

    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private int age;
    private String address;
}
