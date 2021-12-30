package com.schedulerbot;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.MessageCreateMono;

public class CommandHandler {
    public MessageCreateMono parseTodo(Message message){
        return message.getChannel().block().createMessage("test");
    }

    public MessageCreateMono parseCreate(Message message){
        return message.getChannel().block().createMessage("create");
    }
}
