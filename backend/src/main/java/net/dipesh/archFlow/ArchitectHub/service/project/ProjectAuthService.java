package net.dipesh.archFlow.ArchitectHub.service.project;


import lombok.RequiredArgsConstructor;
import net.dipesh.archFlow.ArchitectHub.entity.ProjectMember;
import net.dipesh.archFlow.ArchitectHub.enums.ProjectPermission;
import net.dipesh.archFlow.ArchitectHub.repository.ProjectMemberRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@RequiredArgsConstructor
public class ProjectAuthService {

    private final ProjectMemberRepository projectMemberRepository;

    private boolean hasPermission(Long userId, Long projectId , ProjectPermission permission){

        ProjectMember projectMember = projectMemberRepository.findByProjectIdAndUserId(projectId,userId).orElse(null);
        if(projectMember == null){
            return false;
        }

        return hasPermission(projectMember,permission);
    }

    private boolean hasPermission(ProjectMember projectMember,ProjectPermission permission){

        Set<ProjectPermission> permissions = RolePermissionMapping.getPermissions(projectMember.getRole());
        return permissions.contains(permission);
    }

    public void checkPermission(Long userId, Long projectId, ProjectPermission permission){
        if(!hasPermission(userId,projectId,permission)){
            throw new AccessDeniedException("You don't have permission to access this resource");
        }
    }

}
