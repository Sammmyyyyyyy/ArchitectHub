package net.dipesh.archFlow.ArchitectHub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.dipesh.archFlow.ArchitectHub.enums.InvitationStatus;
import net.dipesh.archFlow.ArchitectHub.enums.OrganizationRole;
import java.time.LocalDateTime;

@Data
@Entity
@Table(
        name = "organization_invitation",
        indexes = {
                @Index(name = "idx_invitation_email",columnList = "email"),
                @Index(name = "idx_invitation_token",columnList = "token")
        }
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganizationInvitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrganizationRole role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InvitationStatus status;

    @Column(nullable = false,length = 100,unique = true)
    private String token;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id",nullable = false)
    private Organization organization;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_member_id",nullable = false)
    private User invitedBy;
}
