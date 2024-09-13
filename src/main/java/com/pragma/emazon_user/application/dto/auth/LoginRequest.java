package com.pragma.emazon_user.application.dto.auth;

import com.pragma.emazon_user.application.utils.Constants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginRequest {

  @NotBlank(message = Constants.USER_EMAIL_MUST_NOT_BE_BLANK)
  @NotNull(message = Constants.USER_EMAIL_MUST_NOT_BE_NULL)
  @Size(max = Constants.USER_EMAIL_LENGTH, message = Constants.USER_EMAIL_LENGTH_EXCEEDED)
  @Email(message = Constants.USER_EMAIL_INVALID)
  private String userEmail;

  @NotBlank(message = Constants.USER_PASSWORD_MUST_NOT_BE_BLANK)
  @NotNull(message = Constants.USER_PASSWORD_MUST_NOT_BE_NULL)
  @Pattern(regexp = Constants.USER_PASSWORD_REGEX, message = Constants.USER_PASSWORD_CANT_CONTAIN_WHITE_SPACES)
  private String userPassword;

}
