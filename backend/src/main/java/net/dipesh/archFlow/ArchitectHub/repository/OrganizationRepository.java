package net.dipesh.archFlow.ArchitectHub.repository;

import net.dipesh.archFlow.ArchitectHub.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Optional<Organization> findFirstByUserId(Long userId);
    boolean existsByName(String name);
}
