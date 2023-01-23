package ru.otr.demoservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.otr.demoservice.entity.Message;
import ru.otr.demoservice.entity.TypeMessage;
import ru.otr.demoservice.entity.Users;
import ru.otr.demoservice.repository.MessageRepository;
import ru.otr.demoservice.repository.UsersRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    private final UsersService usersService;

    public Message saveQuery(Message message){
        Message saveMessage1 = messageRepository.save(message);
        setAnswer(saveMessage1);
        return saveMessage1;
    }
    public List<Message> listAll(){
        return messageRepository.findAll(Sort.by("id"));
    }

    public List<Message> listByUsers(String idKeycloak){
        List<Message> ls = messageRepository.findAll()
                .stream()
                .filter(t->{
                    if(t.getUsers().getIdkeycloak().equals(idKeycloak))
                        return true;
                    if(t.getQuery()!=null && t.getQuery().getUsers().getIdkeycloak().equals(idKeycloak))
                        return true;
                    return false;
                }).collect(Collectors.toList());
        return ls;
    }

    public Message setAnswer(Message query){
        Users users = new Users();
        users.setName("Авто информатор");
        users.setIdkeycloak("123456789");
        users = usersService.findOrSave(users);

        TypeAnswer typeAnswer = new TypeAnswer();

        Message message = new Message();
        message.setId(null);
        message.setUsers(users);
        message.setText(typeAnswer.getAnswer());
        message.setTypeMessage(TypeMessage.ANSWER);
        message.setDatewrite(LocalDateTime.now());
        message.setQuery(query);
        return messageRepository.save(message);

    }


}
