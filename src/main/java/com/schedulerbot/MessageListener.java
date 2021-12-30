package com.schedulerbot;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.MessageCreateMono;
import reactor.core.publisher.Mono;

import java.util.Objects;

public abstract class MessageListener {

    public Mono<Void> processCommand(Message eventMessage) {
        if (eventMessage.getAuthor().map(user -> !user.isBot()).orElse(false)) {
            MessageCreateMono response = parseCommand(eventMessage);
            return Mono.just(response != null ? response : Mono.empty()).then();
        }
        return Mono.empty();
//                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
////                .filter(message -> message.getContent().equalsIgnoreCase("!todo"))
//                .flatMap(this::parseCommand)
//                .filter(Objects::nonNull)
////                .flatMap(Message::getChannel)
////                .flatMap(channel -> channel.createMessage("Things to do today:\n - write a bot\n - eat lunch\n - play a game"))
//                .then();
    }

    public MessageCreateMono parseCommand(Message message){
        String messageContent = message.getContent();

        if (messageContent.equalsIgnoreCase("!todo")){
            return message.getChannel().block().createMessage("test");
        } else if (messageContent.equalsIgnoreCase("!create")){
            return message.getChannel().block().createMessage("create");
        }
        return null;
    }
}
