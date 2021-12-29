package com.schedulerbot.dao;

import java.util.List;

public interface Dao<T> {
    T getById(int id);
    List<T> getAll();
    T create(T t);
    T update(T t);
    void delete(int id);
}
