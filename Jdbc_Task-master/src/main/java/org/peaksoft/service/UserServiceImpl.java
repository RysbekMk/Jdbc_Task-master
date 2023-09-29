package org.peaksoft.service;


import org.peaksoft.model.Car;
import org.peaksoft.model.User;
import org.peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements Service<User> {

    public void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS users("+"id SERIAL PRIMARY KEY," +
                "name VARCHAR (20) NOT NULL, " +
                "last_name VARCHAR (20) NOT NULL, " +
                "age SMALLINT, " +
                "car_id INT REFERENCES cars (id))";
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Table successfully created...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable() {
        String query = "DROP TABLE users";
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Table deleted in given database...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(User user) {
        String query = "INSERT INTO users(name, last_name, age, car_id)\n" +
                "VALUES (?,?,?,?)";
        try (Connection connection = Util.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             preparedStatement.setString(1,user.getName());
             preparedStatement.setString(2, user.getLastName());
             preparedStatement.setByte(3,user.getAge());
             preparedStatement.setInt(4, Math.toIntExact(user.getCarId()));
             preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeById(long id) {
        String query = "DELETE FROM users WHERE id = ?;";
        try (Connection connection = Util.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, (int) id);
            preparedStatement.executeUpdate();
            System.out.println("Data successfully deleted...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users;";
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
               user.setId((long) resultSet.getInt(1));
               user.setName(resultSet.getString(2));
               user.setLastName(resultSet.getString(3));
               user.setAge((byte) resultSet.getInt(4));
               user.setCarId(resultSet.getLong(5));
               users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanTable() {
        String query = "DELETE FROM users";
        try (Connection connection = Util.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
            System.out.println("Data successfully cleaned...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getById(long id) {
        User user = new User();
        String query = "SELECT * FROM users WHERE id = ?; ";
        try (Connection connection = Util.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, (int) id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               user.setId((long) resultSet.getInt(1));
               user.setName(resultSet.getString(2));
               user.setLastName(resultSet.getString(3));
               user.setAge(resultSet.getByte(4));
               user.setCarId(resultSet.getLong(5));
            }
        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
        }
        return user;
    }
}
