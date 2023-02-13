package ru.otr.demoservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.otr.demoservice.dto.LoginDto;

import java.util.Date;

@RestController
@RequestMapping("/api2")
@RequiredArgsConstructor
public class TestController {

    private final RestTemplate restTemplate;

    @GetMapping("/hhh")
    public String getHhh(){
        return "OK! Demo service response " + new Date();
    }

    @PostMapping(value = "/login")
    public String getToken(){
        LoginDto loginDto = new LoginDto();
        loginDto.setPassword("123");
        loginDto.setClientId("ks-auth-client");
        loginDto.setUsername("pks");
        return getToken(loginDto);
    }


    public String getToken(LoginDto login) {
        return getAuthResponse(login);
    }

    //TODO: обработка ошибок при авторизации
    private String getAuthResponse(LoginDto loginDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        ResponseEntity<String> objectResponseEntity = restTemplate.postForEntity(
                "http://auth-service:8080/realms/kc-auth-realm/protocol/openid-connect/token",
                //"http://localhost:8180/realms/kc-auth-realm/protocol/openid-connect/token",
                new HttpEntity<>(loginDto.getCredentials(), headers), String.class);

        return objectResponseEntity.getBody();
    }



}
