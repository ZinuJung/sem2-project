package com.example.database;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface IDbSet<T> {
    void add(T model);

    void update(T model);

    void delete(int id);

    ObservableList<T> findAll();
}
