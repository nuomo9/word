package com.example.word;

import java.sql.*;

public class ChangeId {
    // url = "jdbc:oracle:thin:@host:port:SID"
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/word";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    // 加载驱动
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    // 获取连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // 释放资源
    public static void free(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resultSet = null;
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            statement = null;
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection = null;
        }
    }

    public static void free(ResultSet[] resultSets, Statement[] statements, Connection[] connections) {
        if (resultSets != null && resultSets.length > 0) {
            for (ResultSet resultSet : resultSets) {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    resultSet = null;
                }
            }
            resultSets = null;
        }
        if (statements != null && statements.length > 0) {
            for (Statement statement : statements) {
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    statement = null;
                }
            }
            statements = null;
        }
        if (connections != null && connections.length > 0) {
            for (Connection connection : connections) {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    connection = null;
                }
            }
            connections = null;
        }
    }

    public static void main(String[] args) throws SQLException {
        String prefix = "139286929931976";
        Connection connection = getConnection();
        String sql = "update word_copy1 set insert_time = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 1; i <= 691; i++) {
            preparedStatement.setObject(1, prefix + String.valueOf(6350 + i));
            preparedStatement.setObject(2, i);
            preparedStatement.executeUpdate();
        }
        free(null, preparedStatement, connection);

    }
}
