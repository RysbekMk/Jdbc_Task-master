package org.peaksoft;

import org.peaksoft.model.Car;
import org.peaksoft.model.User;
import org.peaksoft.service.CarServiceImpl;
import org.peaksoft.service.UserServiceImpl;
import org.peaksoft.util.Util;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException {
        // TODO: 27.09.2023   реализуйте алгоритм здесь
//        CarServiceImpl carService = new CarServiceImpl();
//        carService.createTable();
//        carService.dropTable();
//        Car car = new Car("Kia", LocalDate.of(2018,11,15),"white");
//        carService.save(car);
//        carService.removeById(2);
//        carService.getAll().stream().forEach(System.out::println);
//        carService.cleanTable();
//        System.out.println(carService.getById(13));
        UserServiceImpl userService = new UserServiceImpl();
//        userService.createTable();
//        userService.dropTable();
//        User user = new User("Rysbek","Sharapov", (byte) 24, 13L);
//        userService.save(user);
//        userService.removeById(3);
//        userService.getAll().stream().forEach(System.out::println);
        System.out.println(userService.getById(1));
    }
}
