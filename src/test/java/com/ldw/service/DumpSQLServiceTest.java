package com.ldw.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DumpSQLServiceTest {

    @Autowired
    private SendEmailService sendEmailService;
    @Autowired
    private DumpSQLService dumpSQLService;
    @Test
    void dump() throws Exception {
dumpSQLService.dump();
    }
}