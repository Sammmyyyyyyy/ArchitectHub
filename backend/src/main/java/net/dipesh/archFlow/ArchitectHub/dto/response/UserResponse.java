package net.dipesh.archFlow.ArchitectHub.dto.response;

import lombok.Data;
import net.dipesh.archFlow.ArchitectHub.enums.UserRole;

@Data
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private UserRole role;

}
