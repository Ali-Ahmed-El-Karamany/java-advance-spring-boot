package com.pioneers.designpatterns.singleton;

import com.pioneers.designpatterns.configs.db.ConnectionPool;
import com.pioneers.designpatterns.configs.db.DbConnector;

public class EagerDbConnector {
    private static final EagerDbConnector INSTANCE = new EagerDbConnector();

    private final DbConnector dbConnector;

    private EagerDbConnector() {
        dbConnector = new DbConnector(
                "", "", "", "",
                new ConnectionPool(0, 0, 0)
        );
    }

    public static EagerDbConnector getInstance() {
        return INSTANCE;
    }

    public DbConnector getDbConnector() {
        return dbConnector;
    }
}
