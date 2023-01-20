package ru.otr.demoservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.otr.demoservice.dto.MessageDto;
import ru.otr.demoservice.mapper.MessageMapper;
import ru.otr.demoservice.service.MessageService;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/message")
    public String view(Model model){
        MessageDto message = new MessageDto();
        model.addAttribute("mes", message);
        model.addAttribute("messages", messageService.list());
        return "message";
    }

    @PostMapping(value = "/message", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String add(MessageDto messageDto){
        MessageMapper messageMapper = new MessageMapper();
        messageService.save(messageMapper.map(messageDto));
        return "redirect:message";
    }
}
