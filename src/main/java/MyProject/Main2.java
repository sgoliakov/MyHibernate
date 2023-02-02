package MyProject;

import MyProject.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main2 {
    public static void main(String[] args) {

//как прочитать
        Employee empFromDB = null;
        //с помощью SessionFactory import org.hibernate.SessionFactory;

        SessionFactory factory = new Configuration().configure("hib.xml").buildSessionFactory();
////создаем Session
        Session session = factory.openSession();
        session.beginTransaction();
////прочитаем объект через сессию
        empFromDB = session.find(Employee.class, 1);
////подтверждаем изменения в БД
        session.getTransaction().commit();
////закрываем сессию
        session.close();

        System.out.println(empFromDB);

//как обновить
        session = factory.openSession();
        session.beginTransaction();
        Employee employeeUpdate = session.find(Employee.class, 2);
        employeeUpdate.setFirstName("Marina");
        session.update(employeeUpdate);
        session.getTransaction().commit();
        session.close();

//как удалить
        session = factory.openSession();
        session.beginTransaction();
        Employee empDelete = session.find(Employee.class,2);
        session.delete(empDelete);
        session.getTransaction().commit();
        session.close();
        factory.close();

    }
}
