package net.dipesh.archFlow.ArchitectHub.controller.project;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.dipesh.archFlow.ArchitectHub.dto.request.CreateProjectRequest;
import net.dipesh.archFlow.ArchitectHub.dto.request.UpdateProjectRequest;
import net.dipesh.archFlow.ArchitectHub.entity.Project;
import net.dipesh.archFlow.ArchitectHub.service.project.ProjectService;
import net.dipesh.archFlow.ArchitectHub.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@Tag(name = "Project Management", description = "Create, view, update, delete projects and retrieve dashboard analytics")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    private Long getUserId(Authentication authentication) {
        return userService.getUser(authentication.getName()).getId();
    }

    @PostMapping
    @Operation(summary = "Create a new project", description = "Creates a new project, assigns a personal workspace if none specified, and sets creator as ADMIN member")
    public ResponseEntity<Project> createProject(@Valid @RequestBody CreateProjectRequest createProjectRequest, Authentication authentication) {
        Long userId = getUserId(authentication);
        Project project = projectService.createProject(
                userId,
                createProjectRequest.getProjectName(),
                createProjectRequest.getProjectDescription(),
                createProjectRequest.getGithubUrl(),
                createProjectRequest.getOrganizationId()
        );

        return ResponseEntity.ok(project);
    }

    @GetMapping("/{projectId}")
    @Operation(summary = "Get project by ID", description = "Retrieves details of a project if user has view permission")
    public ResponseEntity<Project> getProject(@PathVariable("projectId") Long projectId, Authentication authentication) {
        Long userId = getUserId(authentication);
        Project project = projectService.getProject(userId, projectId);

        return ResponseEntity.ok(project);
    }

    @GetMapping
    @Operation(summary = "List all user projects", description = "Retrieves all projects where current user is a member")
    public ResponseEntity<List<Project>> getAllProjects(Authentication authentication) {
        Long userId = getUserId(authentication);
        List<Project> projects = projectService.getMyProjects(userId);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/stats")
    @Operation(summary = "Get dashboard statistics", description = "Returns aggregated counts of projects, team members, decisions, and change requests for current user")
    public ResponseEntity<Map<String, Object>> getDashboardStats(Authentication authentication) {
        Long userId = getUserId(authentication);
        List<Project> projects = projectService.getMyProjects(userId);

        Map<String, Object> stats = new HashMap<>();
        stats.put("projectsCount", projects.size());
        stats.put("teamMembersCount", projects.isEmpty() ? 1 : projects.stream().mapToInt(p -> p.getMembers() != null ? p.getMembers().size() : 1).sum());
        stats.put("decisionsCount", projects.stream().mapToInt(p -> p.getArchitectureDecisions() != null ? p.getArchitectureDecisions().size() : 0).sum());
        stats.put("changeRequestsCount", projects.stream().mapToInt(p -> p.getChangeRequests() != null ? p.getChangeRequests().size() : 0).sum());

        return ResponseEntity.ok(stats);
    }

    @PutMapping("/{projectId}")
    @Operation(summary = "Update project", description = "Updates name, description, or GitHub URL of an existing project")
    public ResponseEntity<Project> updateProject(@PathVariable Long projectId, @Valid @RequestBody UpdateProjectRequest updateProjectRequest, Authentication authentication) {
        Long userId = getUserId(authentication);
        Project project = projectService.updateProject(projectId, userId, updateProjectRequest.getName(),
                updateProjectRequest.getDescription(), updateProjectRequest.getGithubUrl());
        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/{projectId}")
    @Operation(summary = "Delete project", description = "Deletes a project if user has delete permission")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId, Authentication authentication) {
        Long userId = getUserId(authentication);
        projectService.deleteProject(userId, projectId);
        return ResponseEntity.ok().build();
    }
}
