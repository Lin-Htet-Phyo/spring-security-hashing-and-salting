package com.example.springsecurityhashingandsalting14102022.ds;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterUser {

    @NotBlank(message = "UserName cannot be empty.")
    @Pattern(regexp = "[A-Za-z0-9 ]*", message = "UserName contains illegal characters.")
    private String userName;
    @NotBlank(message = "Password cannot be empty.")
    @Pattern(regexp = "[A-Za-z0-9]*", message = "Password contains illegal characters.")
    private String password;
    @NotBlank(message = "Password cannot be empty.")
    @Pattern(regexp = "[A-Za-z0-9]*", message = "Repeated Password contains illegal characters.")
    private String repeatedPassword;
}
