package com.pioneers.universitysystem.services;

import liquibase.exception.LiquibaseException;

import java.sql.SQLException;

public interface RollbackService {

    void rollback(int steps) throws SQLException, LiquibaseException;
}
