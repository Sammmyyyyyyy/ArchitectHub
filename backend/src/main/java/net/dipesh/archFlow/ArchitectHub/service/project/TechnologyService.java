package net.dipesh.archFlow.ArchitectHub.service.project;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.dipesh.archFlow.ArchitectHub.entity.Project;
import net.dipesh.archFlow.ArchitectHub.entity.Technology;
import net.dipesh.archFlow.ArchitectHub.enums.ProjectPermission;
import net.dipesh.archFlow.ArchitectHub.repository.ProjectRepository;
import net.dipesh.archFlow.ArchitectHub.repository.TechnologyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TechnologyService {

    private final TechnologyRepository technologyRepository;
    private final ProjectAuthService  projectAuthService;
    private final ProjectRepository projectRepository;

    @Transactional
    public Technology addTechnology(Long currentUserId, Long projectId, String name, String category, String description) {
        projectAuthService.checkPermission(currentUserId, projectId, ProjectPermission.CREATE_TECH_STACK);
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project Not Found"));
        if (technologyRepository.existsByNameAndProjectId(name, projectId)) {
            throw new RuntimeException("Technology already exists for this project");
        }

        Technology technology = new Technology();
        technology.setName(name);
        technology.setCategory(category);
        technology.setDescription(description);
        technology.setProject(project);

        return technologyRepository.save(technology);
    }

    @Transactional
    public Technology getTechnologyById(Long currentUserId, Long projectId, Long id) {
        projectAuthService.checkPermission(currentUserId, projectId, ProjectPermission.VIEW_TECH_STACK);
        Technology technology = technologyRepository.findById(id).orElseThrow(() -> new RuntimeException("Technology not found"));
        if (!technology.getProject().getId().equals(projectId)) {
            throw new RuntimeException("Technology does not belong to project");
        }
        return technology;
    }

    @Transactional
    public List<Technology> getAllTechnologies(Long currentUserId, Long projectId) {
        projectAuthService.checkPermission(currentUserId, projectId, ProjectPermission.VIEW_TECH_STACK);
        return technologyRepository.findByProjectId(projectId);
    }

    @Transactional
    public Technology updateTechnology(Long currentUserId, Long projectId, Long id, String name, String category, String description) {
        projectAuthService.checkPermission(currentUserId, projectId, ProjectPermission.UPDATE_TECH_STACK);
        Technology technology = technologyRepository.findById(id).orElseThrow(() -> new RuntimeException("Technology not found"));
        if (!technology.getProject().getId().equals(projectId)) {
            throw new RuntimeException("Technology does not belong to project");
        }
        technology.setName(name);
        technology.setCategory(category);
        technology.setDescription(description);

        return technologyRepository.save(technology);
    }

    @Transactional
    public void deleteTechnology(Long currentUserId, Long projectId, Long id) {
        projectAuthService.checkPermission(currentUserId, projectId, ProjectPermission.DELETE_TECH_STACK);
        Technology technology = technologyRepository.findById(id).orElseThrow(() -> new RuntimeException("Technology not found"));
        if (!technology.getProject().getId().equals(projectId)) {
            throw new RuntimeException("Technology does not belong to project");
        }
        technologyRepository.delete(technology);
    }
}
