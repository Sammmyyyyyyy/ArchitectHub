package net.dipesh.archFlow.ArchitectHub.controller.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.dipesh.archFlow.ArchitectHub.dto.request.LoginRequest;
import net.dipesh.archFlow.ArchitectHub.dto.request.SignInRequest;
import net.dipesh.archFlow.ArchitectHub.dto.response.TokenResponse;
import net.dipesh.archFlow.ArchitectHub.service.auth.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/auth", "/api/auth"})
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "User registration, login, token refresh, and logout APIs")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    @Operation(summary = "Register new user", description = "Creates a new user account, sets auth cookies, and returns JWT tokens with user profile")
    public ResponseEntity<TokenResponse> signup(@RequestBody SignInRequest signInRequest,
                                                HttpServletResponse response) {
        TokenResponse tokens = authService.signup(signInRequest);
        addCookies(response, tokens.getAccessToken(), tokens.getRefreshToken());
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticates user credentials, sets auth cookies, and returns JWT tokens with user profile")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {

        TokenResponse tokens = authService.login(loginRequest);
        addCookies(response, tokens.getAccessToken(), tokens.getRefreshToken());

        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh access token", description = "Extracts refresh token from cookies, headers, or body and generates new tokens")
    public ResponseEntity<TokenResponse> refreshToken(
            @CookieValue(value = "refreshToken", required = false) String refreshTokenFromCookie,
            @RequestHeader(value = "X-Refresh-Token", required = false) String refreshTokenFromHeader,
            @RequestBody(required = false) TokenResponse tokenBody,
            HttpServletResponse response) {

        String token = refreshTokenFromCookie;
        if (token == null || token.isBlank()) {
            token = refreshTokenFromHeader;
        }
        if ((token == null || token.isBlank()) && tokenBody != null) {
            token = tokenBody.getRefreshToken();
        }
        if (token == null || token.isBlank()) {
            throw new RuntimeException("Refresh token is missing");
        }

        TokenResponse tokens = authService.refreshToken(token);
        addCookies(response, tokens.getAccessToken(), tokens.getRefreshToken());
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/logout")
    @Operation(summary = "Logout user", description = "Clears the authentication accessToken and refreshToken cookies")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        clearCookies(response);
        return ResponseEntity.ok("Logged out successfully");
    }

    private void addCookies(HttpServletResponse response, String accessToken, String refreshToken) {

        ResponseCookie accessCookie = ResponseCookie.from("accessToken", accessToken)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(60 * 10)
                .sameSite("Lax")
                .build();

        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(7 * 24 * 60 * 60)
                .sameSite("Lax")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
    }

    private void clearCookies(HttpServletResponse response) {
        ResponseCookie accessCookie = ResponseCookie.from("accessToken", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();

        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
    }
}
