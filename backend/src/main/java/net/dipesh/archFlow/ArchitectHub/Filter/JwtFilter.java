package net.dipesh.archFlow.ArchitectHub.Filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.dipesh.archFlow.ArchitectHub.service.auth.UserDetailsServiceImplementation;
import net.dipesh.archFlow.ArchitectHub.utils.JwtUtils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImplementation userDetailsService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        return path.startsWith("/auth/") || path.startsWith("/api/auth/")
                || path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs")
                || path.equals("/docs");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String accessToken = null;

        // 1. First check Authorization header: Bearer <token>
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            accessToken = authHeader.substring(7);
        }

        // 2. Fallback to httpOnly Cookie if header not present
        if (accessToken == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("accessToken".equals(cookie.getName())) {
                        accessToken = cookie.getValue();
                        break;
                    }
                }
            }
        }

        // if accessToken exists and user is not already authenticated
        if(accessToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            try{
                String email = jwtUtils.extractEmail(accessToken);

                if(email != null && jwtUtils.validateToken(accessToken)) {

                    //load from the database
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                    //create Authentication object
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );

                    //attaching  request details
                    authenticationToken.setDetails(new WebAuthenticationDetails(request));
                    //store authentication for currentRequest
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                }
            }

            catch(Exception e){
                //invalidate jwt token
                SecurityContextHolder.clearContext();
            }

        }

        filterChain.doFilter(request, response);
    }
}
