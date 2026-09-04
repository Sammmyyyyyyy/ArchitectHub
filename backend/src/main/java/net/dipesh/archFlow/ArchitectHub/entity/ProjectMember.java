package net.dipesh.archFlow.ArchitectHub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.dipesh.archFlow.ArchitectHub.enums.ProjectRole;


@Data
@Table(
        name = "project_member",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_project_user",
                        columnNames = {"user_id","project_id"})
        }
)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProjectMember{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectRole role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id",nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

}