package net.dipesh.archFlow.ArchitectHub.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateProfileRequest {

    @NotNull
    private String newUsername;
}
