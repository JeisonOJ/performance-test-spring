package com.jeison.perfomance_test.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserReq {

    @NotBlank(message = "Username required")
    @Size(min=4,max = 50, message = "Username must between 4 and 50 characters")
    private String userName;
    @NotBlank(message = "password required")
    private String password;
    @NotBlank(message = "email required")
    @Email(message = "Not valid email")
    @Size(max = 100, message = "Email is too long")
    private String email;
    @NotNull(message = "Role required")
    private boolean isActive;

}
