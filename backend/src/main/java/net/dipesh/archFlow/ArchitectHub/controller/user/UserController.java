package net.dipesh.archFlow.ArchitectHub.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.dipesh.archFlow.ArchitectHub.dto.request.ChangeEmailRequest;
import net.dipesh.archFlow.ArchitectHub.dto.request.ChangePasswordRequest;
import net.dipesh.archFlow.ArchitectHub.dto.request.UpdateProfileRequest;
import net.dipesh.archFlow.ArchitectHub.dto.response.UserResponse;
import net.dipesh.archFlow.ArchitectHub.entity.User;
import net.dipesh.archFlow.ArchitectHub.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/users", "/api/users"})
@RequiredArgsConstructor
@Tag(name = "User Management", description = "Read, update, and delete user profile and account details")
public class UserController {

    private final UserService userService;

    //get currently logged in user
    @GetMapping("/me")
    @Operation(summary = "Get current user profile", description = "Fetches the profile details of the authenticated user")
    public ResponseEntity<UserResponse> getCurrentUser(Authentication authentication){
        String email = authentication.getName();
        User user = userService.getUser(email);

        UserResponse userResponse = mapToResponse(user);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    //delete the account
    @DeleteMapping("/me")
    @Operation(summary = "Delete my account", description = "Deletes the account of the currently logged in user")
    public ResponseEntity<?> deleteMyAccount(Authentication authentication) {
        String email = authentication.getName();
        userService.deleteCurrentUser(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // update Profile name
    @PutMapping("/me")
    @Operation(summary = "Update user display name", description = "Updates username/profile name for current user")
    public ResponseEntity<UserResponse> updateProfile(Authentication authentication, @Valid @RequestBody UpdateProfileRequest updateProfileRequest){
        String email = authentication.getName();
        User user = userService.updateProfile(email, updateProfileRequest);
        return ResponseEntity.ok(mapToResponse(user));
    }

    //update the email
    @PutMapping("/me/email")
    @Operation(summary = "Update user email", description = "Changes email address for current user")
    public ResponseEntity<UserResponse> updateEmail(Authentication authentication, @Valid @RequestBody ChangeEmailRequest changeEmailRequest){
        String email = authentication.getName();
        User user = userService.changeEmail(email, changeEmailRequest);
        return ResponseEntity.ok(mapToResponse(user));
    }

    @PutMapping("/me/password")
    @Operation(summary = "Change password", description = "Validates old password and updates to new password")
    public ResponseEntity<UserResponse> changePassword(Authentication authentication, @Valid @RequestBody ChangePasswordRequest changePasswordRequest){
        String email = authentication.getName();
        User user = userService.changePassword(email, changePasswordRequest);
        return ResponseEntity.ok(mapToResponse(user));
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
