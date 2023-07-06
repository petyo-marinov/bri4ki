package bri4ka.model.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class RegisterRequestUserDTO {

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String username;
    private String email;
    private String password;
    @JsonProperty("confirm_password")
    private String confirmPassword;
    private int age;
    private String address;
}
