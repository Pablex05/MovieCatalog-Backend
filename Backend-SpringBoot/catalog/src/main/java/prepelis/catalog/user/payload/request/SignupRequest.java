package prepelis.catalog.user.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

@Getter
@Setter
public class SignupRequest {

    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 20, message = "Username requires between 4 and 20 characters")
    private String username;

    @NotBlank(message = "Email is required")
    @Size(min = 6,max = 50, message = "Email requires between 6 and 10 characters")
    @Email
    private String email;

    @NotEmpty(message = "Role is required")
    private Set<String> role;

    @NotBlank(message = "Password is required")
    @Size(min = 4, max = 120, message = "Password requires between 4 and 120 characters")
    private String password;

}
