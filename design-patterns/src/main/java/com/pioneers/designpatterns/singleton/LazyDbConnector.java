package com.pioneers.designpatterns.singleton;

import com.pioneers.designpatterns.configs.db.ConnectionPool;
import com.pioneers.designpatterns.configs.db.DbConnector;

public class LazyDbConnector {
    private static LazyDbConnector INSTANCE;

    private final DbConnector dbConnector;

    private LazyDbConnector() {
        dbConnector = new DbConnector(
                "", "", "", "",
                new ConnectionPool(0, 0, 0)
        );
    }

    public static LazyDbConnector getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazyDbConnector();
        }

        return INSTANCE;
    }

    public DbConnector getDbConnector() {
        return dbConnector;
    }
}
