package net.dipesh.archFlow.ArchitectHub.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddtechnologyRequest {

    @NotNull
    @Size(max=100)
    private String technologyName;

    @NotNull
    @Size(max=1000)
    private String technologyDescription;

    @NotNull
    @Size(max=100)
    private String technologyCategory;
}
