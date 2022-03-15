package com.diploma.edu.source.servicies;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.util.Objects.nonNull;

@Service
public class TokenService {
    private static final Logger log = Logger.getLogger(TokenService.class);

    private static final String TOKEN_SECRET = "NetCracker2020";
    private static final String USER_EMAIL = "userEmail";
    private static final String CREATED_AT = "createdAt";

    public String createToken(String userEmail) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(TOKEN_SECRET);
            return JWT.create()
                    .withClaim(USER_EMAIL, userEmail)
                    .withClaim(CREATED_AT, new Date())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            log.log(Level.WARN,"Error during creation security token", e);
            return null;
        }
    }

    public String getUserEmailFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim(USER_EMAIL).asString();
        } catch (JWTVerificationException e) {
            log.error("Error during parsing security token", e);
            return null;
        }
    }

    public boolean isTokenValid(String token) {
        String userEmail = this.getUserEmailFromToken(token);
        return nonNull(userEmail);
    }
}
