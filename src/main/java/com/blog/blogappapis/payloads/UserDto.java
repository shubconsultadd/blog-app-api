package com.blog.blogappapis.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class UserDto {
    private int id;

    @NotEmpty
    @Size(min=4,message = "Username should be minimum of 4 characters!")
    private String name;

    @Email(message="Your email address is not valid!")
    private String email;

    @NotEmpty
    @Size(min=3,max = 10,message = "Password must be min of 3 chars and max of 10 chars !!")
    private String password;

    @NotEmpty
    private String about;
}
