package net.dipesh.archFlow.ArchitectHub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.dipesh.archFlow.ArchitectHub.enums.UserRole;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_user_email",columnNames = {"user_email"})
        },
        indexes = {
                @Index(name = "idx_user_email",columnList = "user_email")
        }
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "user_name",nullable = false,length = 100)
    private String name;

    @NotBlank
    @Email
    @Column(name = "user_email",nullable = false,length = 100)
    private String email;

    @NotBlank
    @Size(min=8,max=100)
    @Column(name = "user_password",nullable = false,length = 255)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<ProjectMember> projectMembers = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRole role;

    @OneToMany(mappedBy = "requestedBy")
    private List<ChangeRequest> requests = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<AuditLog> auditLogs = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Organization> organizations = new ArrayList<>();


}

