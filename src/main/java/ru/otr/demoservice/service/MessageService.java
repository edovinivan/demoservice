package ru.otr.demoservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.otr.demoservice.entity.Message;
import ru.otr.demoservice.repository.MessageRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public Message save(Message message){
        return messageRepository.save(message);
    }
    public List<Message> listAll(){
        return messageRepository.findAll(Sort.by("id"));
    }

    public List<Message> listByUsers(String idKeycloak){
        return messageRepository.findAllByUsers_Idkeycloak(idKeycloak);
    }
}
