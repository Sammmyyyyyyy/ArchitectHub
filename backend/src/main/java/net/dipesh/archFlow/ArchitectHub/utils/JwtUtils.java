package net.dipesh.archFlow.ArchitectHub.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import net.dipesh.archFlow.ArchitectHub.entity.User;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    private @NonNull SecretKey getSigningKey() {
        byte[] encodedKey = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(encodedKey);
    }

    public  String generateJwt(User user){
        Map<String, Object> claims = new HashMap<>();

        claims.put("Role", user.getRole());
        claims.put("Id", user.getId());

        return Jwts.builder().
                setClaims(claims).
                subject(user.getEmail()).
                issuedAt(new Date()).
                expiration(new Date(System.currentTimeMillis() + 60 * 10 * 1000)).
                signWith(getSigningKey()).
                compact();
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    public  String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    private Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public  boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    public String generateRefreshToken(User user) {
        return  Jwts.builder()
                .subject(user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000))
                .signWith(getSigningKey())
                .compact();
    }
}
