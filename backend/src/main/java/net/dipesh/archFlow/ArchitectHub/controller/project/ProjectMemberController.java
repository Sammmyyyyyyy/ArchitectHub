package net.dipesh.archFlow.ArchitectHub.controller.project;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.dipesh.archFlow.ArchitectHub.dto.request.CreateMemberRequest;
import net.dipesh.archFlow.ArchitectHub.entity.ProjectMember;
import net.dipesh.archFlow.ArchitectHub.enums.ProjectRole;
import net.dipesh.archFlow.ArchitectHub.service.project.ProjectMemberService;
import net.dipesh.archFlow.ArchitectHub.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/members")
@RequiredArgsConstructor
@Tag(name = "Project Members", description = "Add, view, update role, and remove members in a project")
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;
    private final UserService userService;

    private Long getUserId(Authentication authentication) {
        return userService.getUser(authentication.getName()).getId();
    }

    @PostMapping
    @Operation(summary = "Add project member", description = "Adds a user as a member to a project with a specific ProjectRole (requires ADD_MEMBER permission)")
    public ResponseEntity<ProjectMember> createProjectMember(
            @PathVariable Long projectId,
            @Valid @RequestBody CreateMemberRequest request,
            Authentication authentication
    ) {
        Long currentUserId = getUserId(authentication);
        ProjectMember member = projectMemberService.addProjectMember(currentUserId, projectId, request.getUserId(), request.getRole());
        return ResponseEntity.ok().body(member);
    }

    @GetMapping
    @Operation(summary = "List project members", description = "Returns all members belonging to a project")
    public ResponseEntity<List<ProjectMember>> getProjectMembers(@PathVariable Long projectId, Authentication authentication) {
        Long currentUserId = getUserId(authentication);
        List<ProjectMember> members = projectMemberService.getProjectMembers(currentUserId, projectId);
        return ResponseEntity.ok().body(members);
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update member role", description = "Updates the ProjectRole (e.g. ARCHITECT, DEVELOPER, etc.) of a project member")
    public ResponseEntity<ProjectMember> updateProjectMember(@PathVariable Long projectId, @PathVariable Long userId, @RequestParam ProjectRole role, Authentication authentication) {
        Long currentUserId = getUserId(authentication);
        ProjectMember updatedMember = projectMemberService.updateProjectMember(currentUserId, projectId, userId, role);
        return ResponseEntity.ok().body(updatedMember);
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Remove member from project", description = "Removes a member from the project (requires REMOVE_MEMBER permission)")
    public ResponseEntity<?> deleteProjectMember(@PathVariable Long projectId, @PathVariable Long userId, Authentication authentication) {
        Long currentUserId = getUserId(authentication);
        projectMemberService.removeProjectMember(currentUserId, projectId, userId);
        return ResponseEntity.ok().build();
    }

}
