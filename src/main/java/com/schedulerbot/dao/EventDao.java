package com.schedulerbot.dao;

import com.schedulerbot.config.DatabaseConfig;
import com.schedulerbot.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDao implements Dao<Event> {

    private final Connection connection = ConnectionFactory.getConnection();


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
        Event event = new Event(
                resultSet.getString(2),
                resultSet.getDate(3),
                resultSet.getTime(4),
                resultSet.getString(5));
        event.setId(resultSet.getInt(1));
        return event;
    }

    @Override
    public List<Event> getAll() {
        List<Event> events = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM event");

            while(resultSet.next()){
                Event event = eventFromResultSet(resultSet);
                events.add(event);
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return events;
    }

    @Override
    public Event create(Event event) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO event VALUES" +
                    " (DEFAULT, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, event.getTitle());
            preparedStatement.setDate(2, event.getDate());
            preparedStatement.setTime(3, event.getTime());
            preparedStatement.setString(4, event.getDescription());
            System.out.println(preparedStatement);
            int i = preparedStatement.executeUpdate();
            if (i == 1){
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if(generatedKeys.next()){
                    return getById(generatedKeys.getInt(1));
                }
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return event;
    }

    @Override
    public Event update(Event event) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE event SET" +
                    " (title=?, date=?, time=?, description=? WHERE id=?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, event.getTitle());
            preparedStatement.setDate(2,event.getDate());
            preparedStatement.setTime(3, event.getTime());
            preparedStatement.setString(4, event.getDescription());
            int i = preparedStatement.executeUpdate();

            if(i==1){
                return getById(event.getId());
            }
        }catch(SQLException exception) {
            exception.printStackTrace();
        }
        return event;
    }

    @Override
    public void delete(int id) {
        try{
            Statement statement = connection.createStatement();
            int i = statement.executeUpdate("DELETE FROM event WHERE id=" + id);
        }catch(SQLException exception){
            exception.printStackTrace();
        }
    }

//    Event getById(int id);
//    List<Event> getAll();
//    Event create(Event event);
//    Event update(Event event);
//    Event delete(Event event);
}
