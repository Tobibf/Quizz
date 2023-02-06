package com.isge.gsn.Quizz.utils;

import com.isge.gsn.Quizz.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtUtils {
    private final String secretKey = "MySecretKeyForThisGamingApplicationAndISGEProject";

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


        String jwt = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return jwt;
    }


    public String verifyJwt(String jwt) {
        try {

            Jws<Claims> jws;

            jws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(jwt);

            //Return role if Token is valid
            return jws.getBody().get("Role").toString();
        } catch (Exception e) {
           return "";
        }
    }
}
