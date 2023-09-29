package org.peaksoft.service;


import org.peaksoft.model.Car;
import org.peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarServiceImpl implements Service<Car> {
    public void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS cars(" +
                "id SERIAL PRIMARY KEY," +
                "model VARCHAR(20) NOT NULL," +
                "year_of_release DATE," +
                "color VARCHAR(20) NOT NULL)";
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Table successfully created...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable() {
        String query = "DROP TABLE cars";
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Table deleted in given database...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(Car car) {
        String query = "INSERT INTO cars(model,year_Of_release,color) " +
                "VALUES (?,?,?);";
        try (Connection connection = Util.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setDate(2, Date.valueOf(car.getYearOfRelease()));
            preparedStatement.setString(3, car.getColor());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeById(long id) {
        String query = "DELETE FROM cars WHERE id = ?;";
        try (Connection connection = Util.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, (int) id);
            preparedStatement.executeUpdate();
            System.out.println("Data successfully deleted...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM cars;";
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getLong(1));
                car.setModel(resultSet.getString(2));
                car.setYearOfRelease(resultSet.getDate(3).toLocalDate());
                car.setColor(resultSet.getString(4));
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public void cleanTable() {
        String query = "DELETE FROM cars";
        try (Connection connection = Util.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
            System.out.println("Data successfully cleaned...");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Car getById(long id) {
        Car car = new Car();
        String query = "SELECT * FROM cars WHERE id = ?; ";
        try (Connection connection = Util.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, (int) id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                car.setId(resultSet.getLong(1));
                car.setModel(resultSet.getString(2));
                car.setYearOfRelease(resultSet.getDate(3).toLocalDate());
                car.setColor(resultSet.getString(4));
            }
        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
        }
        return car;
    }
}
