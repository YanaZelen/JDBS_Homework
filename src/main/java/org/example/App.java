package org.example;

import org.example.dao.CarDaoHiber;
import org.example.dao.CarsDaoJDBC;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);

        CarDaoHiber userDao = context.getBean(CarDaoHiber.class);
    }
}
