package com.schedulerbot;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.MessageCreateMono;
import reactor.core.publisher.Mono;


public abstract class MessageListener {
    private CommandHandler handler = new CommandHandler();
    public Mono<Void> processCommand(Message eventMessage) {
        if (eventMessage.getAuthor().map(user -> !user.isBot()).orElse(false)) {
            MessageCreateMono response = parseCommand(eventMessage);
            return response != null ? response.then() : Mono.empty().then();
        }
        return Mono.empty();
    }

    public MessageCreateMono parseCommand(Message message) {
        String messageContent = message.getContent();
        System.out.println("doing message thing" + messageContent);
        if (messageContent.equalsIgnoreCase("!todo")) {
            return handler.parseTodo(message);
        } else if (messageContent.startsWith("!create ")) {
            return handler.parseCreate(message);
        } else if (messageContent.startsWith("!delete ")) {
            return handler.parseDelete(message);
        } else if (messageContent.startsWith("!info ")) {
            return handler.parseInfo(message);
        }
        return null;
    }

}
