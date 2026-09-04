package net.dipesh.archFlow.ArchitectHub.dto.request;

import lombok.Data;

@Data
public class SignInRequest {

    public String email;
    public String password;
    public String confirmPassword;
    public String name;
}
