package com.game.hyf.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

    //decoder
    //encoder
    //generate token
    //verify token
    //parse
    //read the token and get info about it
    private final Key key;
    private final long expiration;

    // Constructor to initialize the secret key and expiration time from application properties
    JwtUtils(@Value("${app.jwt.secret}")String secret, @Value("${app.jwt.expiration}") long expiration) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expiration = expiration;
    }

    /*  Helper method to parse the JWT token and return the claims
        This method uses the Jwts.parserBuilder() to create a parser with the signing key, and then parses the token to extract the claims.
        if the token is invalid or expired, this method will throw an exception (e.g., io.jsonwebtoken.ExpiredJwtException, io.jsonwebtoken.SignatureException, etc.), 
        which should be handled by the caller to determine the appropriate response (e.g., return an unauthorized error).
        The parse method is a crucial part of the JWT handling process, as it allows us to validate the token and extract the information contained within it (such as the subject, claims, and expiration). 
        It is typically used in authentication and authorization processes to verify the token's validity and to retrieve user information for further processing.
    */
    private Jws<Claims> parse(String token) {
        return Jwts.parserBuilder().setSigningKey(key)
                .build().parseClaimsJws(token);
    }

    /*  
        This method creates a JWT token by setting the claims, subject, issued at time, and expiration time, and then signing it with the secret key.
    */
    public String generateToken(String subject, Map<String, Object> claims) {
        Date now = new Date();
        Date expire = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setClaims(claims)  //provide the payload of the JWT
                .setSubject(subject)    // it is used to identify the user
                .setIssuedAt(now)       //set the issued at
                .setExpiration(expire)  //set the expiration
                .signWith(key)              //TODO decode when we set the yjt with base64 encoding
                .compact();
    }

    /*
        This method checks if the token is valid by parsing it and checking the expiration date. 
        If the token is expired, it will return false; otherwise, it returns true. 
        Note that this method may throw exceptions if the token is invalid (e.g., malformed, signature does not match, etc.)
    */
    public boolean isValid(String token) {  //we may have to handle exceptions because of parsing
        Date tokenExpiration = parse(token).getBody().getExpiration();
        return tokenExpiration.after(new Date());
    }

    /*
        This method extracts the subject (which typically represents the user identifier) from the JWT token by parsing it and retrieving the subject claim. 
        It also includes debug print statements to output the token's subject and claims for verification purposes. 
        If the token is invalid or expired, this method will throw an exception, which should be handled by the caller.
    */
    public String getSubject(String token) {
        Claims body = parse(token).getBody();
        //TODO check it in the debugger
        System.out.println("Token Subject: " + body.getSubject());
        System.out.println("Token Claims: " + body);
        
        return body.getSubject();
    }

}