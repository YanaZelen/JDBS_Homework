package org.example.dao;

import java.util.List;

//import org.example.dto.Car;
import org.example.entity.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;

@Repository
public class CarDaoHiber implements CarDao {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public void saveCar(String brand, String model, int carNumber, int carAge) {
        sessionFactory.getCurrentSession().save(new Car(brand, model, carNumber, carAge));
    }

    @Transactional
    public List<Car> getAllCars() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }

    @Transactional
    public Car getCarById(long id) {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car where id = :id");
        query.setParameter("id", id);
        return query.getSingleResult();
    }

}
