package ru.otr.demoservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import ru.otr.demoservice.dto.Greeting;
import ru.otr.demoservice.dto.SocketMessage;

@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(SocketMessage  socketMessage) throws InterruptedException {

        System.out.println("--------------------------------------------------");
        Thread.sleep(3000);
        return new Greeting("Hello, " + socketMessage.getName());
    }
}
