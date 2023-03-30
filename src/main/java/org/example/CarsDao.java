package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
Практическое задание

Установить MySQL базу данных, либо любую другую SQL базу данных, которая вам удобна.
Подключиться к вашей базе данных через IDEA и приложить скрин вкладки Database как подтверждение
 */
public class CarsDao {

    public void createCarsTable() {
        try (Connection connection = Config.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS cars(id int primary key auto_increment," +
                    "brand varchar(40), model varchar(40)," +
                    "carNumber int, carAge int)");
            System.out.println("Таблица Cars создана");
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void dropCarsTable() {
        try (Connection connection = Config.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS cars");
            System.out.println("Таблица Cars удалена");
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void cleanCarsTable() {
        final String DELETE_ALL_CARS = "DELETE FROM cars";
        try (Connection connection = Config.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(DELETE_ALL_CARS)) {
            int result = prepareStatement.executeUpdate();
            if (result != 0) {
                System.out.println("Таблица очищена");
            } else {
                System.out.println("Таблица уже была пуста");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addCar(String brand, String model, int carNumber, int carAge) {
        final String INSERT_NEW_CAR = "INSERT INTO cars(brand, model, carNumber, carAge)" + "VALUES(?, ?, ?, ?)";
        try (Connection connection = Config.getConnection(); PreparedStatement statement = connection.prepareStatement(INSERT_NEW_CAR)) {
            statement.setString(1, brand);
            statement.setString(2, model);
            statement.setInt(3, carNumber);
            statement.setInt(4, carAge);
            statement.execute();
            System.out.println("Новая машина " + brand + " " + model + " " + carAge + "года выпуска добавлена в таблицу");
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void deleteCar(int id) {
        final String DELETE_CAR = "DELETE FROM cars WHERE id = ?";
        try (Connection connection = Config.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_CAR)) {
            statement.setInt(1, id);
            statement.execute();
            System.out.println("Машина под id " + id + " удалена");
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


    public CarsDTO getCarById(int id) {
        final String GET_CAR = "SELECT * FROM cars WHERE id = ?";
        CarsDTO carDTO = null;
        try (Connection connection = Config.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(GET_CAR)) {
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                int carNumber = resultSet.getInt("carNumber");
                int carAge = resultSet.getInt("carAge");
                carDTO =  new CarsDTO(id, brand, model, carNumber, carAge);
            }
            return carDTO;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CarsDTO> getAllCars() {
        List<CarsDTO> cars = new ArrayList<>();
        try (Connection connection = Config.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cars");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                int carNumber = resultSet.getInt("carNumber");
                int carAge = resultSet.getInt("carAge");
                CarsDTO car = new CarsDTO(id, brand, model, carNumber, carAge);
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

}


