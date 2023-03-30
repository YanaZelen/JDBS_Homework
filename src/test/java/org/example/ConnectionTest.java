package org.example;

import lombok.Data;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

public class ConnectionTest {

    @Test
    @SneakyThrows
    public void testConnection() {
        Connection connection = Config.getConnection();
        System.out.println(connection.isValid(1));
        Assert.assertFalse(connection.isClosed());
    }
}
