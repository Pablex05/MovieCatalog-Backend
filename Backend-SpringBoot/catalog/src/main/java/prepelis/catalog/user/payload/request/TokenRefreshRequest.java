package prepelis.catalog.user.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TokenRefreshRequest {

  @NotBlank(message = "Refresh token is required")
  private String refreshToken;

}
