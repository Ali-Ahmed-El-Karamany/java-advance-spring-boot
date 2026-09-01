package com.pioneers.designpatterns.configs.db;

public record ConnectionPool(int maxOpenConnections, int maxIdleConnections, int timeOut) {

}
