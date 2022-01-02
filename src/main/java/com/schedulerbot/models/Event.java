package com.schedulerbot.models;

import org.springframework.cglib.core.Local;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

public class Event {
    String title;
    Date date;
    Time time;
    String description;
    int id;

    public Event(String title, Date date, Time time, String description) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Event (String rawString) throws Exception{
        String[] splitString = rawString.split("\n");
        if (splitString.length != 4){
            throw new Exception("Invalid number of fields");
        }
        this.title = splitString[0];
        this.date = new Date(new SimpleDateFormat("dd/MM/yyyy").parse(splitString[1]).getTime());
        String rawTime = splitString[2].toLowerCase(Locale.ROOT);
        if (rawTime.endsWith("am") || rawTime.endsWith("pm")){
            String rawTimestamp = rawTime.split(" ")[0];
            String[] splitColon = rawTimestamp.split(":");
            int hour = Integer.parseInt(splitColon[0]);
            int minute = Integer.parseInt(splitColon[1]);
            if (rawTime.endsWith("pm")) {
                hour += 12;
            }
            this.time = new Time(hour, minute, 0);
        } else {
            throw new Exception("Invalid time format, please format time as HH:MM AM or PM");
        }
        this.description = splitString[3];
    }
}
