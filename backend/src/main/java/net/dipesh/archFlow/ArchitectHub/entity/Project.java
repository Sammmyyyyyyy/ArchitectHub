package net.dipesh.archFlow.ArchitectHub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "projects",
        indexes = {
                @Index(
                        name = "idx_project_name",
                        columnList = "project_name"
                )
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=8,max=100)
    @Column(name = "project_name",nullable = false,length = 100)
    private String name;

    @Size(max=1000)
    @Column(name = "project_description",length = 1000)
    private String description;


    @Column(name = "github_url",length = 500)
    private String githubUrl;

    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "organization_id")
    private Organization organization;

    @OneToMany(mappedBy = "project")
    List<ProjectMember> members = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    List<Module> modules = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    List<ArchitectureDecision>  architectureDecisions = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    List<ChangeRequest> changeRequests = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    List<AuditLog> auditLogs = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    List<Technology> technologies = new ArrayList<>();







}
