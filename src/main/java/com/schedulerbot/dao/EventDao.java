package com.schedulerbot.dao;

import com.schedulerbot.models.Event;

import java.sql.Statement;
import java.util.List;

public class EventDao implements Dao<Event> {
    @Override
    public Event getById(int id) {
//        try{
//            Statement statement = connection.createStatement();
//        }
        return null;
    }

    @Override
    public List<Event> getAll() {
        return null;
    }

    @Override
    public Event create(Event event) {
        return null;
    }

    @Override
    public Event update(Event event) {
        return null;
    }

    @Override
    public Event delete(Event event) {
        return null;
    }
//    Event getById(int id);
//    List<Event> getAll();
//    Event create(Event event);
//    Event update(Event event);
//    Event delete(Event event);
}
