package ru.otr.demoservice.mapper;

import ru.otr.demoservice.dto.MessageDto;
import ru.otr.demoservice.entity.Message;
import ru.otr.demoservice.entity.TypeMessage;
import ru.otr.demoservice.entity.Users;

import java.time.LocalDateTime;


public class MessageMapper {
    public Message map(MessageDto messageDto){
        Users users = new Users();
        users.setId(1L);

        Message message = new Message();
        message.setId(null);
        message.setUsers(users);
        message.setText(messageDto.getText());
        message.setTypeMessage(TypeMessage.QUERY);
        message.setDatewrite(LocalDateTime.now());
        return message;
    }
}
