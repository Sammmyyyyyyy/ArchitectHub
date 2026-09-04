package net.dipesh.archFlow.ArchitectHub.service.project;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.dipesh.archFlow.ArchitectHub.entity.Project;
import net.dipesh.archFlow.ArchitectHub.entity.ProjectMember;
import net.dipesh.archFlow.ArchitectHub.entity.User;
import net.dipesh.archFlow.ArchitectHub.enums.ProjectPermission;
import net.dipesh.archFlow.ArchitectHub.enums.ProjectRole;
import net.dipesh.archFlow.ArchitectHub.exceptions.UserNotFoundException;
import net.dipesh.archFlow.ArchitectHub.repository.ProjectMemberRepository;
import net.dipesh.archFlow.ArchitectHub.repository.ProjectRepository;
import net.dipesh.archFlow.ArchitectHub.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectAuthService projectAuthService;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Transactional
    public  ProjectMember addProjectMember(Long currentUserId, Long projectId, Long userId, ProjectRole role) {

        //authorization
        projectAuthService.checkPermission(currentUserId, projectId, ProjectPermission.ADD_MEMBER);

        //check Project
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("project not found"));
        //check user
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        //check duplicate membership
        if (projectMemberRepository.existsByProjectIdAndUserId(projectId, userId)) {
            throw new RuntimeException("User is already member of this project");
        }

        ProjectMember projectMember = new ProjectMember();
        projectMember.setProject(project);
        projectMember.setUser(user);
        projectMember.setRole(role);

        return projectMemberRepository.save(projectMember);

    }


    @Transactional
    public void removeProjectMember(Long currentUserId, Long projectId, Long userId) {
        projectAuthService.checkPermission(currentUserId, projectId, ProjectPermission.REMOVE_MEMBER);
        ProjectMember projectMember = projectMemberRepository.findByProjectIdAndUserId(projectId,userId).orElseThrow(() -> new RuntimeException("User is not a member of this project"));
        projectMemberRepository.delete(projectMember);
    }

    @Transactional
    public ProjectMember updateProjectMember(Long currentUserId, Long projectId, Long userId, ProjectRole role) {
        projectAuthService.checkPermission(currentUserId, projectId, ProjectPermission.UPDATE_MEMBER_ROLE);

        ProjectMember member = projectMemberRepository.findByProjectIdAndUserId(projectId,userId).orElseThrow(() -> new RuntimeException("User is not a member of this project"));
        member.setRole(role);
        return projectMemberRepository.save(member);
    }

    @Transactional
    public List<ProjectMember> getProjectMembers(Long currentUserId, Long projectId) {
        projectAuthService.checkPermission(currentUserId,projectId,ProjectPermission.VIEW_MEMBER);
        return projectMemberRepository.findByProjectId(projectId);
    }

}
