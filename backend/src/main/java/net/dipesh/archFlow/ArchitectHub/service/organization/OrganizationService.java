package net.dipesh.archFlow.ArchitectHub.service.organization;

import lombok.RequiredArgsConstructor;
import net.dipesh.archFlow.ArchitectHub.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;


}
