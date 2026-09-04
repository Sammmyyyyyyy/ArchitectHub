package net.dipesh.archFlow.ArchitectHub.repository;


import net.dipesh.archFlow.ArchitectHub.entity.ArchitectureDecision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchitectureRepository extends JpaRepository<ArchitectureDecision,Long> {

    List<ArchitectureDecision> findArchitectureDecisionByProjectId(Long projectId);
    ArchitectureDecision findArchitectureDecisionByIdAndProjectId(Long id,Long projectId);
}
