package ru.otr.demoservice.controller;

import lombok.RequiredArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otr.demoservice.dto.MessageDto;
import ru.otr.demoservice.entity.Message;
import ru.otr.demoservice.mapper.MessageMapper;
import ru.otr.demoservice.service.MessageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MessageApiController {
    private final MessageService messageService;

    private final MessageMapper messageMapper;

    @GetMapping("/message")
    public ResponseEntity<List<Message>> getMessages(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
        String subject = principal.getKeycloakSecurityContext().getToken().getSubject();
        return ResponseEntity.ok(messageService.listByUsers(subject));
    }

    @PostMapping(value = "/message", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Message> message(@RequestBody MessageDto messageDto){
        return ResponseEntity.ok(messageService.saveQuery(messageMapper.map(messageDto)));
    }
}
