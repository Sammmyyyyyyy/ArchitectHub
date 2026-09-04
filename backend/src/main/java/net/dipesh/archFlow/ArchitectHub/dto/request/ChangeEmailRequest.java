package net.dipesh.archFlow.ArchitectHub.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangeEmailRequest {

    @NotBlank
    @Email
    public String newEmail;
}