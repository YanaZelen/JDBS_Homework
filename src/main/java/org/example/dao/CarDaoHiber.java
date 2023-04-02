package org.example.dao;

import org.example.CarsDTO;
import org.example.entity.Car;
import org.example.Config;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;

import static org.hibernate.resource.transaction.spi.TransactionStatus.ACTIVE;
import static org.hibernate.resource.transaction.spi.TransactionStatus.MARKED_ROLLBACK;

public class CarDaoHiber implements CarDao {

    public void createCarsTable() {
        Transaction transaction = null;
        try (Session session = Config.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS cars(id int primary key auto_increment," +
                    "brand varchar(40), model varchar(40)," +
                    "carNumber int, carAge int)");
            query.executeUpdate();
            transaction.commit();
            System.out.println("Таблица Cars создана");
        } catch (Exception e) {
            if (transaction != null || transaction.getStatus() == ACTIVE
                    || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
    }

    public void dropCarsTable() {
        Transaction transaction = null;
        try (Session session = Config.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE IF EXISTS cars;");
            query.executeUpdate();
            transaction.commit();
            System.out.println("Таблица Cars удалена");
        } catch (Exception e) {
            if (transaction != null || transaction.getStatus() == ACTIVE
                    || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
    }

    public void addCar(String brand, String model, int carNumber, int carAge) {
        Transaction transaction = null;
        try (Session session = Config.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Car car = new Car();
            car.setBrand(brand);
            car.setModel(model);
            car.setCarNumber(carNumber);
            car.setCarAge(carAge);
            session.save(car);
            transaction.commit();
            System.out.println("Новая машина " + brand + " " + model + " " + carAge + "года выпуска добавлена в таблицу");
        } catch (Exception e) {
            if (transaction != null || transaction.getStatus() == ACTIVE
                    || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
    }

    public void deleteCarById(long id) {
        Transaction transaction = null;
        try (Session session = Config.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete Car where id = :param");
            query.setParameter("param", id);
            query.executeUpdate();
            transaction.commit();
            System.out.println("Машина под id " + id + " удалена");
        } catch (Exception e) {
            if (transaction != null || transaction.getStatus() == ACTIVE
                    || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
    }




    public List<Car> getAllCars() {
        List<Car> cars = Collections.emptyList();
        Transaction transaction = null;
        try (Session session = Config.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Car> query = session.createQuery("from Car");
            cars = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null || transaction.getStatus() == ACTIVE
                    || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
        return cars;
    }


    public void cleanCarsTable() {
        Transaction transaction = null;
        try (Session session = Config.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("TRUNCATE TABLE cars");
            query.executeUpdate();
            transaction.commit();
            if (transaction != null) {
                System.out.println("Таблица очищена");
            } else {
                System.out.println("Таблица уже была пуста");
            }
        } catch (Exception e) {
            if (transaction != null || transaction.getStatus() == ACTIVE
                    || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
    }
}
