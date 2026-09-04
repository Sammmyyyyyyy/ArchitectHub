package net.dipesh.archFlow.ArchitectHub.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import net.dipesh.archFlow.ArchitectHub.enums.ProjectRole;

@Data
public class CreateMemberRequest {

    @NotNull
    private Long userId;

    @NotNull
    private ProjectRole role;

}
