package net.dipesh.archFlow.ArchitectHub.controller.project;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.dipesh.archFlow.ArchitectHub.dto.request.AddtechnologyRequest;
import net.dipesh.archFlow.ArchitectHub.entity.Technology;
import net.dipesh.archFlow.ArchitectHub.service.project.TechnologyService;
import net.dipesh.archFlow.ArchitectHub.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/projects/{projectId}/technologies", "/api/projects/{projectId}/techonologies"})
@RequiredArgsConstructor
@Tag(name = "Project Technologies", description = "Manage tech stack items associated with a project")
public class TechnologyController {

    private final TechnologyService technologyService;
    private final UserService userService;

    private Long getUserId(Authentication authentication) {
        return userService.getUser(authentication.getName()).getId();
    }

    @PostMapping
    @Operation(summary = "Add technology to project", description = "Adds a technology to a project's tech stack (requires CREATE_TECH_STACK permission)")
    public ResponseEntity<Technology> addTechnology(@PathVariable Long projectId, @RequestBody AddtechnologyRequest request, Authentication authentication) {
        Long userId = getUserId(authentication);
        Technology technology = technologyService.addTechnology(userId, projectId, request.getTechnologyName(), request.getTechnologyCategory(), request.getTechnologyDescription());
        return ResponseEntity.ok(technology);
    }

    @GetMapping
    @Operation(summary = "List project technologies", description = "Returns all technologies associated with a specific project")
    public ResponseEntity<List<Technology>> getTechnologies(@PathVariable Long projectId, Authentication authentication) {
        Long userId = getUserId(authentication);
        List<Technology> technologies = technologyService.getAllTechnologies(userId, projectId);
        return ResponseEntity.ok(technologies);
    }

    @GetMapping("/{technologyId}")
    @Operation(summary = "Get technology by ID", description = "Retrieves details of a specific technology by its ID")
    public ResponseEntity<Technology> getTechnologyById(@PathVariable Long projectId, @PathVariable Long technologyId, Authentication authentication) {
        Long userId = getUserId(authentication);
        Technology technology = technologyService.getTechnologyById(userId, projectId, technologyId);
        return ResponseEntity.ok(technology);
    }

    @PutMapping("/{technologyId}")
    @Operation(summary = "Update technology", description = "Updates details of a technology in a project")
    public ResponseEntity<Technology> updateTechnology(@PathVariable Long projectId, @PathVariable Long technologyId, @RequestBody AddtechnologyRequest request, Authentication authentication) {
        Long userId = getUserId(authentication);
        Technology technology = technologyService.updateTechnology(userId, projectId, technologyId, request.getTechnologyName(), request.getTechnologyCategory(), request.getTechnologyDescription());
        return ResponseEntity.ok(technology);
    }

    @DeleteMapping("/{technologyId}")
    @Operation(summary = "Delete technology", description = "Removes a technology from a project's tech stack (requires DELETE_TECH_STACK permission)")
    public ResponseEntity<Void> deleteTechnology(@PathVariable Long projectId, @PathVariable Long technologyId, Authentication authentication) {
        Long userId = getUserId(authentication);
        technologyService.deleteTechnology(userId, projectId, technologyId);
        return ResponseEntity.ok().build();
    }
}
