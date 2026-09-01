package com.pioneers.designpatterns.configs.db;

import lombok.ToString;
import lombok.Value;

@Value
@ToString(of = "")
public class DbConnector {
    String username;
    String password;
    String database;
    String url;
    ConnectionPool connectionPool;


}
