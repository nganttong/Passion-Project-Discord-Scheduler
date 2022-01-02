package com.schedulerbot;

import com.schedulerbot.dao.EventDao;
import com.schedulerbot.models.Event;
import discord4j.core.object.entity.Message;
import discord4j.core.spec.MessageCreateMono;

public class CommandHandler {
    EventDao eventDao = new EventDao();
    public MessageCreateMono parseTodo(Message message){
        return message.getChannel().block().createMessage("test");
    }

    public MessageCreateMono parseCreate(Message message){
        String messageContent = message.getContent();
        String rawEvent = messageContent.substring(8);
        Event formattedEvent;
        try {
            formattedEvent = new Event(rawEvent);
        } catch (Exception e) {
            return message.getChannel().block().createMessage("Invalid formatting: " + e.getMessage());
        }
        eventDao.create(formattedEvent);
        return message.getChannel().block().createMessage("Event created!");
    }

    public MessageCreateMono parseUpdate(Message message){
        return message.getChannel().block().createMessage("update");
    }

    public MessageCreateMono parseDelete(Message message){
        return message.getChannel().block().createMessage("delete");
    }


}
