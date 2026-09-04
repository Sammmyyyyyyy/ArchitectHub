package net.dipesh.archFlow.ArchitectHub.repository;

import net.dipesh.archFlow.ArchitectHub.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndProjectId(String name, Long projectId);
    List<Technology> findByProjectId(Long projectId);
}
