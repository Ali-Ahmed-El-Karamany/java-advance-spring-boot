package com.pioneers.designpatterns.singleton;

import com.pioneers.designpatterns.configs.db.ConnectionPool;
import com.pioneers.designpatterns.configs.db.DbConnector;

public enum EnumDbConnector {
    INSTANCE;

    private final DbConnector dbConnector = new DbConnector("", "", "", "",
                        new ConnectionPool(0, 0, 0));

    public DbConnector getDbConnector() {
        return dbConnector;
    }
}
