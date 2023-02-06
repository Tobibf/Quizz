package com.isge.gsn.Quizz.utils;

import com.isge.gsn.Quizz.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Date;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

@Component
public class JwtUtils {

    public String generateJWT(@NotNull User user) {

        long milliTime = System.currentTimeMillis();
        // Allow 3600 seconds
        long expiryDuration = 60 * 60;
        long expiryTime = milliTime + expiryDuration * 1000;
        Date issueAt = new Date(milliTime);
        Date expiryAt = new Date(expiryTime);

        //Claims
        Claims claims = Jwts.claims()
                .setIssuer(Integer.toString((int) user.getId()))
                .setIssuedAt(issueAt)
                .setExpiration(expiryAt);

        claims.put("Role", user.getRole().getName());


        //Generate Jwt using Claims
        String secret = "This_is_secret";

        JwtBuilder jwt = Jwts.builder()
                .setClaims(claims)
                .signWith(HS256, secret)
                ;

        return jwt.compact();
    }


}
