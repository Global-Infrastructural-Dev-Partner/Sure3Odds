/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gidp.sure3odds.helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gidp.sure3odds.entity.users.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author GOL
 */
@Component
public class JWTHelper {

    private static final Logger LOG = LoggerFactory.getLogger(JWTHelper.class.getName());
    @Value("${sure.conf.param.jwt.enc-key}")
    private String ENC_KEY;
    @Value("${sure.conf.param.jwt.issuer}")
    private String ISSUER;
    @Value("${sure.conf.param.jwt.validity}")
    private int validity;

    public String createToken(Users user) {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, validity);
        Date expiryDate = cal.getTime();
        try {
            Algorithm algorithm = Algorithm.HMAC256(ENC_KEY);
            String token = JWT.create()
                    .withIssuer(ISSUER)
                    .withExpiresAt(expiryDate)
                    .withIssuedAt(Calendar.getInstance().getTime())
                    .withNotBefore(Calendar.getInstance().getTime())
                    .withJWTId(user.getId().toString())
                    .withClaim("usertype", user.getUserTypeID().getName())
                    .withClaim("UserID", user.getId())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException | IllegalArgumentException exception) {
            LOG.info(exception.getMessage());
        }
        //Invalid Signing configuration / Couldn't convert Claims.
        return null;
    }

    public DecodedJWT validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(ENC_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return jwt;
        } catch (Exception exception) {
            LOG.info(exception.getMessage());
        }
        //Invalid signature/claims
        return null;
    }

    public String getUserId(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(ENC_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("UserID").asString();
        } catch (Exception exception) {
            //UTF-8 encoding not supported
            LOG.info(exception.getMessage());
        }
        //Invalid signature/claims
        return null;
    }

}
