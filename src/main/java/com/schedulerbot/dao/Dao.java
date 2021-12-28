package com.schedulerbot.dao;

import com.schedulerbot.models.Event;

import java.util.List;

public interface Dao<T> {
    T getById(int id);
    List<T> getAll();
    T create(T t);
    T update(T t);
    T delete(T t);
}
