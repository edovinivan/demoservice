package ru.otr.demoservice.mapper;

import lombok.RequiredArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.otr.demoservice.dto.MessageDto;
import ru.otr.demoservice.entity.Message;
import ru.otr.demoservice.entity.TypeMessage;
import ru.otr.demoservice.entity.Users;
import ru.otr.demoservice.service.UsersService;

import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor
public class MessageMapper {

    private final UsersService usersService;
    public Message map(MessageDto messageDto){

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
        String subject = principal.getKeycloakSecurityContext().getToken().getSubject();
        String name = principal.getKeycloakSecurityContext().getToken().getName();

        Users newUsers = new Users();
        newUsers.setId(null);
        newUsers.setIdkeycloak(subject);
        newUsers.setName(name);
        Users users = usersService.findOrSave(newUsers);

        Message message = new Message();
        message.setId(null);
        message.setUsers(users);
        message.setText(messageDto.getText());
        message.setTypeMessage(TypeMessage.QUERY);
        message.setDatewrite(LocalDateTime.now());
        message.setQuery(null);
        return message;
    }
}
