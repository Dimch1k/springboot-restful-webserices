package com.example.springbootrestfulwebserices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Schema(
        description = "UserDto Model Information"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @Schema(
            description = "User First Name"
    )
    @NotEmpty(message = "User first name should be not null or empty")
    @Length(min = 2, max = 30, message = "User first name should be 2-30 chars long")
    private String firstName;

    @Schema(
            description = "User Last Name"
    )
    @NotEmpty(message = "User last name should be not null or empty")
    @Length(min = 2, max = 30, message = "User last name should be 2-30 chars long")
    private String lastName;

    @Schema(
            description = "User Email"
    )
    @NotEmpty(message = "User email should be not null or empty")
    @Email(message = "User email should be valid")
    private String email;

}
