package com.lgcns.icst.springthymeleaf.lec4.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Component
public class JdbcUtil {

    private static DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        JdbcUtil.dataSource = dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void commit(Connection connection) throws SQLException {
        if (connection != null) {
            connection.commit();
            log.debug("JdbcUtils.commit: Commit!");
        } else {
            log.warn("JdbcUtils.commit: connection is null!");
        }
    }

    public static void rollback(Connection connection) throws SQLException {
        if (connection != null) {
            connection.rollback();
            log.debug("JdbcUtils.rollback: Rollback!");
        } else {
            log.warn("JdbcUtils.rollback: connection is null!");
        }
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Error occurred during closing connection : {}", e.getMessage(), e);
            }
        }
    }

    public static void close(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.error("Error occurred during closing preparedStatement : {}", e.getMessage(), e);
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.error("Error occurred during closing resultSet : {}", e.getMessage(), e);
            }
        }
    }
}
