package com.employee_self_service.security.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.token-expiration-time.user}")
    private long jwtExpirationUser;
    @Value("${jwt.token-expiration-time.admin}")
    private long jwtExpirationAdmin;
    @Value("${jwt.refresh-token-expiration-time.user}")
    private long refreshExpirationUser;
    @Value("${jwt.refresh-token-expiration-time.admin}")
    private long refreshExpirationAdmin;

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    //    CREATE TOKEN FOR USER
    public String generateTokenForUser(UserDetails userDetails, String subject, String moduleType) {
        return generateTokenForUser(new HashMap<>(), userDetails, subject, moduleType);
    }

    public String generateTokenForUser(Map<String, Object> extraClaims, UserDetails userDetails, String subject, String moduleType) {
        return buildToken(extraClaims, userDetails, jwtExpirationUser, subject, moduleType);
    }


    //    CREATE TOKEN FOR ADMIN
    public String generateTokenForAdmin(UserDetails userDetails, String subject, String moduleType) {
        return generateTokenForAdmin(new HashMap<>(), userDetails, subject, moduleType);
    }

    public String generateTokenForAdmin(Map<String, Object> extraClaims, UserDetails userDetails, String subject, String moduleType) {
        return buildToken(extraClaims, userDetails, jwtExpirationAdmin, subject, moduleType);
    }


    //    CREATE REFRESH TOKEN FOR USER
    public String generateRefreshTokenForUser(UserDetails userDetails, String subject, String moduleType) {
        return buildToken(new HashMap<>(), userDetails, refreshExpirationUser, subject, moduleType);
    }

    public String generateRefreshTokenForAdmin(UserDetails userDetails, String subject, String moduleType) {
        return buildToken(new HashMap<>(), userDetails, refreshExpirationAdmin, subject, moduleType);
    }


    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration, String subject, String moduleType) throws InvalidKeyException {
        return Jwts
                .builder()
                .claims(extraClaims)
                .header().add(getHeader(moduleType)).and()
                .subject(subject != null ? subject : userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey())   //By default, it will use HS512. If you want to use any other, add 'SignatureAlgorithm.X' as second parameter.
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractEmail(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith((SecretKey) getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Map<String, Object> getHeader(String moduleType) {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("token", "JWT".toUpperCase().trim());
        headerMap.put("creator", moduleType.toUpperCase().trim());
        return headerMap;
    }

    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
}