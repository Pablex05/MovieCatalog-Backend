package prepelis.catalog.user.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {

	@NotBlank(message = "Username is required")
	private String username;

	@NotBlank(message = "Password is required")
	private String password;

}
