package ru.otr.demoservice.service;

import java.util.*;

public class TypeAnswer {
    private List<String> listAnswer = new ArrayList<>();

    public TypeAnswer() {
        listAnswer = Arrays.asList("Добрый день!",
                "Ваш вопрос очень важен для нас!",
                "У нас обед. Ответим позже!",
                "Ваш вопрос принят к рассмотрению.");
    }

    public String getAnswer(){
        Random random = new Random(new Date().getTime());
      
        return listAnswer.get(random.nextInt(listAnswer.size()));
    }
}
