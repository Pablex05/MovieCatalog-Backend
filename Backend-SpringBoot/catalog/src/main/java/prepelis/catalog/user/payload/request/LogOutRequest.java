package prepelis.catalog.user.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LogOutRequest {

  @NotNull
  private Long userId;

}
