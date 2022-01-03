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
        Event returnedEvent = eventDao.create(formattedEvent);
        return message.getChannel().block().createMessage("Event created! Id: " + returnedEvent.getId());
    }


    public MessageCreateMono parseDelete(Message message){
        int id;
        String content = message.getContent().substring(8);
        try {
            id = Integer.parseInt(content);
        } catch (Exception e) {
            return message.getChannel().block().createMessage("Error deleting Id: " + content);
        }
        Boolean result = eventDao.delete(id);
        if (result = true) {
            return message.getChannel().block().createMessage("Event deleted.");
        } else {
            return message.getChannel().block().createMessage("Could not delete event with Id: " + content);
        }
    }

    public MessageCreateMono parseInfo(Message message){
        int id;
        String content = message.getContent().substring(6);
        try {
            id = Integer.parseInt(content);
        } catch (Exception e) {
            return message.getChannel().block().createMessage("Error parsing Id: " + content);
        }
        Event event = eventDao.getById(id);
        if (event != null) {
            return message.getChannel().block().createMessage(event.toString());
        }
        return message.getChannel().block().createMessage("Unable to fine event with Id: " + id);
    }

}
