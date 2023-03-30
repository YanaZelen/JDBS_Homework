package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        CarsDao userDao = new CarsDao();
        userDao.dropCarsTable();
        userDao.createCarsTable();
        userDao.addCar("Peugeot", "308", 185, 2018);
        userDao.addCar("Peugeot", "5008", 174, 2005);
        userDao.addCar("Peugeot", "107", 191, 2013);
        userDao.addCar("Toyota", "Corolla Fielder", 201, 2000);
        userDao.addCar("Toyota", "Land Cruiser", 103, 2001);
        userDao.addCar("Toyota", "Camry ", 222, 2016);
        System.out.println(userDao.getAllCars());
        userDao.deleteCar(3);
        System.out.println(userDao.getAllCars());
        CarsDTO userDTO = userDao.getCarById(1);
        System.out.println("Попытка найти пользователя с id " + 1);
        System.out.println(userDTO);
        userDao.cleanCarsTable();
        System.out.println(userDao.getAllCars());
        userDao.cleanCarsTable();
    }
}
