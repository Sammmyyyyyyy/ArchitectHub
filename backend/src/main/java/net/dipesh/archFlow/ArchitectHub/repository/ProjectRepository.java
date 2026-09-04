package net.dipesh.archFlow.ArchitectHub.repository;

import net.dipesh.archFlow.ArchitectHub.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    @Query("""
        SELECT pm.project
        FROM ProjectMember pm
        where pm.user.id = :userId
""")
    List<Project> findProjectsByUserId(@Param("userId") Long userId);
}
