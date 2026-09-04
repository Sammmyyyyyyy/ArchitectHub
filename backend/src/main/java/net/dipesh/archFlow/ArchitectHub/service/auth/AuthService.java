package net.dipesh.archFlow.ArchitectHub.service.auth;

import lombok.RequiredArgsConstructor;
import net.dipesh.archFlow.ArchitectHub.dto.request.LoginRequest;
import net.dipesh.archFlow.ArchitectHub.dto.request.SignInRequest;
import net.dipesh.archFlow.ArchitectHub.dto.response.TokenResponse;
import net.dipesh.archFlow.ArchitectHub.dto.response.UserResponse;
import net.dipesh.archFlow.ArchitectHub.entity.User;
import net.dipesh.archFlow.ArchitectHub.enums.UserRole;
import net.dipesh.archFlow.ArchitectHub.exceptions.InvalidCredentialsException;
import net.dipesh.archFlow.ArchitectHub.exceptions.PasswordMismatchException;
import net.dipesh.archFlow.ArchitectHub.exceptions.UserAlreadyExistException;
import net.dipesh.archFlow.ArchitectHub.repository.UserRepository;
import net.dipesh.archFlow.ArchitectHub.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public TokenResponse signup(SignInRequest signInRequest) throws UserAlreadyExistException, PasswordMismatchException {

        //checking the password confirmation
        if(!signInRequest.getPassword().equals(signInRequest.getConfirmPassword())) {
            throw new PasswordMismatchException("Passwords don't match");
        }

        // checking whether email already exist
        if(userRepository.existsByEmail(signInRequest.getEmail())) {
            throw new UserAlreadyExistException("Email already in use");
        }

        // create new user
        User user = new User();
        user.setEmail(signInRequest.getEmail());
        user.setName(signInRequest.getName());
        user.setRole(UserRole.USER);
        user.setPassword(passwordEncoder.encode(signInRequest.getPassword()));
        user = userRepository.save(user);

        String accessToken = jwtUtils.generateJwt(user);
        String refreshToken = jwtUtils.generateRefreshToken(user);
        UserResponse userResponse = mapToUserResponse(user);

        return new TokenResponse(accessToken, refreshToken, userResponse);
    }

    public TokenResponse login(LoginRequest loginRequest) throws InvalidCredentialsException {

        try {
            //Email + password ko Authentication object m pack
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    );

            // spring security se credentials verify..
            authenticationManager.authenticate(authenticationToken);
        } catch (org.springframework.security.core.AuthenticationException | InvalidCredentialsException e) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        // Authentication successful -> user DB se fetch
        User user = userRepository
                .findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        //return jwt for user
        String accessToken = jwtUtils.generateJwt(user);
        String refreshToken = jwtUtils.generateRefreshToken(user);
        UserResponse userResponse = mapToUserResponse(user);

        return new TokenResponse(accessToken, refreshToken, userResponse);
    }

    // refresh Token
    public TokenResponse refreshToken(String token) {

        //validate the refresh token
        if(!jwtUtils.validateToken(token)) {
            throw new RuntimeException("Invalid token");
        }

        // extract the email from payload of refresh Token
        String email = jwtUtils.extractEmail(token);

        //extract the user from db using email
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        String accessToken = jwtUtils.generateJwt(user);
        String refreshToken = jwtUtils.generateRefreshToken(user);
        UserResponse userResponse = mapToUserResponse(user);

        return new TokenResponse(accessToken, refreshToken, userResponse);
    }

    private UserResponse mapToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(user.getRole());
        return userResponse;
    }
}
