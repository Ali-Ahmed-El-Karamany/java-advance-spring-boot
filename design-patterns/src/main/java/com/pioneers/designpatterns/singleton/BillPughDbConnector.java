package com.pioneers.designpatterns.singleton;

import com.pioneers.designpatterns.configs.db.ConnectionPool;
import com.pioneers.designpatterns.configs.db.DbConnector;

public class BillPughDbConnector {
    private final DbConnector dbConnector;

    private BillPughDbConnector() {
        dbConnector = new DbConnector(
                "", "", "", "",
                new ConnectionPool(0, 0, 0)
        );
    }

    private static class Holder {
        private static final BillPughDbConnector INSTANCE = new BillPughDbConnector();
    }

    public static BillPughDbConnector getInstance() {
        return Holder.INSTANCE;
    }

    public DbConnector getDbConnector() {
        return dbConnector;
    }
}
