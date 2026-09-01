package com.pioneers.designpatterns.singleton;

import com.pioneers.designpatterns.configs.db.ConnectionPool;
import com.pioneers.designpatterns.configs.db.DbConnector;

public class ThreadSafeDbConnector {
    private static ThreadSafeDbConnector INSTANCE;

    private final DbConnector dbConnector;

    private ThreadSafeDbConnector() {
        dbConnector = new DbConnector(
                "", "", "", "",
                new ConnectionPool(0, 0, 0)
        );
    }

    public static synchronized ThreadSafeDbConnector getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ThreadSafeDbConnector();
        }

        return INSTANCE;
    }

    public DbConnector getDbConnector() {
        return dbConnector;
    }
}
