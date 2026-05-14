package com.example.HealthCare.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.function.Function;

@Service
public class JwtService {
private static final String SECRET_KEY ="03c6818e2605d0d551e3465c8d9dd629b8cdb95a09f645637839962c5c7cf2e3";
    public String extractUsername(String token) {
        return null;
    }
    public String extractUserName(String token ){
        return extractClainms(token,Claims::getSubject);
    }
    public<T> T extractClainms(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
