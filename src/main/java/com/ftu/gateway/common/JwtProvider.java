package com.ftu.gateway.common;

import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.rsa.RSAVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    public String getStringKey(String keyName){
        String data = "";
        ClassPathResource cpr = new ClassPathResource(keyName);
        try {
            byte[] bdata = FileCopyUtils.copyToByteArray(cpr.getInputStream());
            data = new String(bdata, StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.warn("IOException", e);
        }
        return data;
    }

    public Boolean validateTokenRSA(String authToken) {
        try {
            Verifier verifier = RSAVerifier.newVerifier(getStringKey("public_key.pem"));
            JWT.getDecoder().decode(authToken, verifier);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public Map<String, Object> getAllClaims(String token){
        // Build an EC verifier using an EC Public Key
        Verifier verifier = RSAVerifier.newVerifier(getStringKey("public_key.pem"));

        // Verify and decode the encoded string JWT to a rich object
        JWT jwt = JWT.getDecoder().decode(token, verifier);

        return jwt.getAllClaims();
    }

    public Set<String> getListGroupFromTokenRSA(String token) {
        List<String> groups = (List<String>) getAllClaims(token).get("groups");
        return new HashSet<String>(groups);
    }

    public String getUserNameFromTokenRSA(String token) {
        return getAllClaims(token).get("sub").toString();
    }
}