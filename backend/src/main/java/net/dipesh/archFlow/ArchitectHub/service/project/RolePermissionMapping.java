package net.dipesh.archFlow.ArchitectHub.service.project;

import net.dipesh.archFlow.ArchitectHub.enums.ProjectPermission;
import net.dipesh.archFlow.ArchitectHub.enums.ProjectRole;

import java.util.EnumSet;
import java.util.Set;

import static net.dipesh.archFlow.ArchitectHub.enums.ProjectPermission.*;

public final class RolePermissionMapping {

    private RolePermissionMapping() {
    }

    public static Set<ProjectPermission> getPermissions(ProjectRole role) {

        return switch (role) {

            case ADMIN ->
                    EnumSet.allOf(ProjectPermission.class);

            case ARCHITECT ->
                    EnumSet.of(
                            VIEW_PROJECT,
                            UPDATE_PROJECT,

                            VIEW_ARCHITECTURE,
                            CREATE_ARCHITECTURE,
                            UPDATE_ARCHITECTURE,
                            DELETE_ARCHITECTURE,

                            VIEW_MODULE,
                            CREATE_MODULE,
                            UPDATE_MODULE,
                            DELETE_MODULE,

                            VIEW_CHANGE_REQUEST,
                            CREATE_CHANGE_REQUEST,
                            APPROVE_CHANGE_REQUEST,

                            VIEW_TECH_STACK,
                            CREATE_TECH_STACK,
                            DELETE_TECH_STACK,

                            VIEW_AUDIT_LOG
                    );

            case DEVELOPER ->
                    EnumSet.of(
                            VIEW_PROJECT,

                            VIEW_ARCHITECTURE,
                            CREATE_ARCHITECTURE,
                            UPDATE_ARCHITECTURE,

                            VIEW_MODULE,
                            CREATE_MODULE,
                            UPDATE_MODULE,

                            VIEW_CHANGE_REQUEST,
                            CREATE_CHANGE_REQUEST,

                            VIEW_TECH_STACK
                    );

            case QA_ENGINEER ->
                    EnumSet.of(
                            VIEW_PROJECT,

                            VIEW_ARCHITECTURE,

                            VIEW_MODULE,

                            VIEW_CHANGE_REQUEST,
                            CREATE_CHANGE_REQUEST,

                            VIEW_TECH_STACK
                    );

            case DEVOPS_ENGINEER ->
                    EnumSet.of(
                            VIEW_PROJECT,

                            VIEW_ARCHITECTURE,

                            VIEW_MODULE,

                            VIEW_CHANGE_REQUEST,
                            CREATE_CHANGE_REQUEST,

                            VIEW_TECH_STACK,
                            CREATE_TECH_STACK,
                            DELETE_TECH_STACK
                    );
        };
    }
}