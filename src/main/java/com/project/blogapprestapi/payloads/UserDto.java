package com.project.blogapprestapi.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private Integer id;
    @NotEmpty(message = "Full name cannot be empty!")
    private String fullName;
    @Email(message = "Email address is not valid!")
    private String emailAddress;
    @NotEmpty(message = "Password cannot be empty!")
    @Size(min = 8, max = 16, message = "Password's length must be between 8 and 16!")
    private String password;
    private String aboutBio;
}