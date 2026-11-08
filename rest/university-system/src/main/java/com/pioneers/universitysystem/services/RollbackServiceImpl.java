package com.pioneers.universitysystem.services;

import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class RollbackServiceImpl implements RollbackService {

    private final DataSource dataSource;

    private static final String CHANGELOG_PATH = "db/changelog-master.xml";

    @Override
    public void rollback(int steps) throws SQLException, LiquibaseException {

        try (Connection connection = dataSource.getConnection()) {
            Database db = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(
                    new JdbcConnection(connection)
            );

            try (Liquibase liquibase
                         = new Liquibase(CHANGELOG_PATH, new ClassLoaderResourceAccessor(), db)) {
                liquibase.rollback(steps, String.valueOf(new Contexts()));
            }
        }
    }
}
