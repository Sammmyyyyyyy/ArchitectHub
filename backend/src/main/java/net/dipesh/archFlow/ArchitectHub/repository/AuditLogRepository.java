package net.dipesh.archFlow.ArchitectHub.repository;

import net.dipesh.archFlow.ArchitectHub.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepository extends JpaRepository<Technology, Long> {

}
