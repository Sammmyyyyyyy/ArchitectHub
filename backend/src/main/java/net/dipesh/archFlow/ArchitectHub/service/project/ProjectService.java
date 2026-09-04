package net.dipesh.archFlow.ArchitectHub.service.project;

import lombok.RequiredArgsConstructor;
import net.dipesh.archFlow.ArchitectHub.entity.Organization;
import net.dipesh.archFlow.ArchitectHub.entity.Project;
import net.dipesh.archFlow.ArchitectHub.entity.ProjectMember;
import net.dipesh.archFlow.ArchitectHub.entity.User;
import net.dipesh.archFlow.ArchitectHub.enums.ProjectPermission;
import net.dipesh.archFlow.ArchitectHub.enums.ProjectRole;
import net.dipesh.archFlow.ArchitectHub.repository.OrganizationRepository;
import net.dipesh.archFlow.ArchitectHub.repository.ProjectMemberRepository;
import net.dipesh.archFlow.ArchitectHub.repository.ProjectRepository;
import net.dipesh.archFlow.ArchitectHub.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final OrganizationRepository organizationRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final UserRepository userRepository;
    private final ProjectAuthService projectAuthService;

    @Transactional
    public Project createProject(Long userId, String name, String description, String githubUrl, Long organizationId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Organization organization;
        if (organizationId != null) {
            organization = organizationRepository.findById(organizationId)
                    .orElseThrow(() -> new RuntimeException("Organization not found"));
        } else {
            // Find user's existing organization or create a default personal workspace
            organization = organizationRepository.findFirstByUserId(userId).orElseGet(() -> {
                Organization defaultOrg = new Organization();
                defaultOrg.setName(user.getName() + "'s Workspace (" + user.getId() + ")");
                defaultOrg.setDescription("Default personal workspace for " + user.getName());
                defaultOrg.setUser(user);
                return organizationRepository.save(defaultOrg);
            });
        }

        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setOrganization(organization);
        project.setGithubUrl(githubUrl);

        Project savedProject = projectRepository.save(project);

        // Automatically assign creator as ADMIN project member
        ProjectMember leadMember = new ProjectMember();
        leadMember.setProject(savedProject);
        leadMember.setUser(user);
        leadMember.setRole(ProjectRole.ADMIN);
        projectMemberRepository.save(leadMember);

        return savedProject;
    }

    public Project createProject(String name, String description, String githubUrl, Long organizationId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new RuntimeException("Organization Not found"));

        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setOrganization(organization);
        project.setGithubUrl(githubUrl);

        return projectRepository.save(project);
    }

    public Project getProject(Long userId, Long projectId) {
        projectAuthService.checkPermission(userId, projectId, ProjectPermission.VIEW_PROJECT);

        return projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project Not Found"));
    }

    public List<Project> getMyProjects(Long userId) {
        return projectRepository.findProjectsByUserId(userId);
    }

    @Transactional
    public Project updateProject(Long projectId, Long userId, String name, String description, String githubUrl) {
        projectAuthService.checkPermission(userId, projectId, ProjectPermission.UPDATE_PROJECT);
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project Not Found"));

        project.setName(name);
        project.setDescription(description);
        project.setGithubUrl(githubUrl);
        return projectRepository.save(project);
    }

    @Transactional
    public void deleteProject(Long userId, Long projectId) {
        projectAuthService.checkPermission(userId, projectId, ProjectPermission.DELETE_PROJECT);
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project Not Found"));
        projectRepository.delete(project);
    }

    @Transactional
    public void deleteProject(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project Not Found"));
        projectRepository.delete(project);
    }
}
