package net.dipesh.archFlow.ArchitectHub.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.dipesh.archFlow.ArchitectHub.dto.response.UserResponse;
import net.dipesh.archFlow.ArchitectHub.entity.User;
import net.dipesh.archFlow.ArchitectHub.service.admin.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "Admin Operations", description = "System administrator APIs for user management and role promotion")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/users")
    @Operation(summary = "List all registered users", description = "Returns a list of all users in the system (Admin only)")
    public ResponseEntity<List<UserResponse>>  getAllUsers() {
        List<User> users = adminService.getAllUsers();
        List<UserResponse> userResponseList = new ArrayList<>();
        for(User user : users){
            userResponseList.add(mapToResponse(user));
        }

        return new  ResponseEntity<>(userResponseList, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieves user details by numeric ID (Admin only)")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        User user = adminService.getUserById(id);
        UserResponse userResponse = mapToResponse(user);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);

    }

    @DeleteMapping("/users/{id}")
    @Operation(summary = "Delete user by ID", description = "Deletes a user account from the system (Admin only)")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.ok("User has been deleted");
    }

    @PutMapping("/users/{id}")
    @Operation(summary = "Promote user to ADMIN", description = "Elevates a user to system-level ADMIN role")
    public ResponseEntity<?> makeUserAdmin(@PathVariable Long id) {
        User user = adminService.makeAdmin(id);
        return  ResponseEntity.ok(mapToResponse(user));
    }

    private UserResponse mapToResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(user.getRole());

        return userResponse;
    }
}
