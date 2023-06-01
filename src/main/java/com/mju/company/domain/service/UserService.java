package com.mju.company.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final ObjectMapper objectMapper;

    public String extractLoginUserId(String ssoToken){
        try {
            String tokenPayload = SignedJWT.parse(ssoToken).getPayload().toString();
            return objectMapper.readTree(tokenPayload).path("sub").asText();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
