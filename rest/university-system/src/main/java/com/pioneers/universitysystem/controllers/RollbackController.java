package com.pioneers.universitysystem.controllers;

import com.pioneers.universitysystem.services.RollbackService;
import liquibase.exception.LiquibaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("liquibase")
public class RollbackController {

    private final RollbackService rollbackService;

    @PostMapping("rollback")
    public void rollback(@RequestParam final int steps) throws SQLException, LiquibaseException {
        rollbackService.rollback(steps);
    }
}
