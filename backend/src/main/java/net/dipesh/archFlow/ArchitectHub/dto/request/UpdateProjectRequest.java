package net.dipesh.archFlow.ArchitectHub.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateProjectRequest {

    @NotNull
    String name;
    @NotNull
    String description;

    String githubUrl;
}
