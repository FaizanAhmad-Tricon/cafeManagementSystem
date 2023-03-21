package com.cafe.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class JwtUtil {

    private  String secret = "faizan@928";

    private static final long Expiration_time =  new Date().getTime() + 1000*60*60;





    public Claims getClaims(String token)
    {
        return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }


    public  String getUsername(String token)
    {
        return getClaims(token).getSubject();
    }


    public  boolean isValidToken(String token  , String username)
    {
        return  !isTokenExpired(token) &&   getClaims(token).getSubject().equals(username);
    }


    public boolean isTokenExpired(String token)
    {
        return getClaims(token).getExpiration().before(new Date(System.currentTimeMillis()));
    }

    public  Date getExpiryDate(String token)
    {
        return  getClaims(token).getExpiration();
    }
    public String generateToken(String subject ,String role)
    {

        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("role", role);

       return Jwts.builder()
                .setClaims(claims)
                .setIssuer("Faizan Ahmad")

                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(Instant.now().plusMillis(3600000)) )
                .signWith(SignatureAlgorithm.HS512,secret.getBytes())
                .compact();



    }














}
