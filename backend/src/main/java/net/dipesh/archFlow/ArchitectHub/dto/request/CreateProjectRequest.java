package net.dipesh.archFlow.ArchitectHub.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateProjectRequest {

    @NotNull
    private String projectName;
    @NotNull
    private String projectDescription;

    private String githubUrl;
    Long organizationId;

}
