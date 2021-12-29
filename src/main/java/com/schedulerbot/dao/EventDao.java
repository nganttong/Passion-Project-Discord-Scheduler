package com.schedulerbot.dao;

import com.schedulerbot.models.Event;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EventDao implements Dao<Event> {
    Connection connection = ConnectionFactory.getConnection();
    @Override
    public Event getById(int id) {
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM event WHERE id=" + id);
            if(resultSet.next()){
                return eventFromResultSet(resultSet);
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return null;
    }

    private Event eventFromResultSet(ResultSet resultSet) throws SQLException{
        return new Event(
                resultSet.getString(1),
                resultSet.getDate(2),
                resultSet.getTime(3),
                resultSet.getString(4));
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
