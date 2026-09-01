package com.pioneers.designpatterns.singleton;

import com.pioneers.designpatterns.configs.db.ConnectionPool;
import com.pioneers.designpatterns.configs.db.DbConnector;

public class DoubleCheckedLockingDbConnector {
    private static volatile DoubleCheckedLockingDbConnector INSTANCE;

    private final DbConnector dbConnector;

    private DoubleCheckedLockingDbConnector() {
        dbConnector = new DbConnector(
                "", "", "", "",
                new ConnectionPool(0, 0, 0)
        );
    }

    public static DoubleCheckedLockingDbConnector getInstance() {
        if (INSTANCE == null) {
            synchronized (DoubleCheckedLockingDbConnector.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DoubleCheckedLockingDbConnector();
                }
            }
        }

        return INSTANCE;
    }

    public DbConnector getDbConnector() {
        return dbConnector;
    }
}
