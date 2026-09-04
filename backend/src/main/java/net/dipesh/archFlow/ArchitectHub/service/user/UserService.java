package net.dipesh.archFlow.ArchitectHub.service.user;

import lombok.RequiredArgsConstructor;
import net.dipesh.archFlow.ArchitectHub.dto.request.ChangeEmailRequest;
import net.dipesh.archFlow.ArchitectHub.dto.request.ChangePasswordRequest;

import net.dipesh.archFlow.ArchitectHub.dto.request.UpdateProfileRequest;

import net.dipesh.archFlow.ArchitectHub.entity.User;
import net.dipesh.archFlow.ArchitectHub.exceptions.PasswordMismatchException;
import net.dipesh.archFlow.ArchitectHub.exceptions.UserAlreadyExistException;
import net.dipesh.archFlow.ArchitectHub.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Get currently LoggedIn User
    public User getUser(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

    }

    // delete the currently loggedIn user
    @Transactional
    public User deleteCurrentUser(String email){
         return userRepository.deleteByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email));

    }

    @Transactional
    public User updateProfile(String email, UpdateProfileRequest updateProfileRequest){
        User user =userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email));

        user.setName(updateProfileRequest.getNewUsername());
        return userRepository.save(user);
    }

    @Transactional
    public User changePassword(String email, ChangePasswordRequest changePasswordRequest){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email));

        //verify the current pass
        if(!passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())){
            throw new PasswordMismatchException("Old Password Mismatch");
        }

        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public User changeEmail(String email, ChangeEmailRequest changeEmailRequest){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email));

        //verify the mail
        if(userRepository.existsByEmail(changeEmailRequest.getNewEmail())){
            throw new UserAlreadyExistException("Email already exists");
        }
        user.setEmail(changeEmailRequest.getNewEmail());
        return userRepository.save(user);
    }


}
