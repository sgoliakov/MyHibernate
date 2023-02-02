package MyProject;
import MyProject.entity.Employee;
import MyProject.entity.Schedule;
import MyProject.entity.WorkDays;
import MyProject.entity.WorkingShift;
import MyProject.entityHelper.FK.EmpDayFK;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;



public class Main {
    public static void main(String[] args) {


//записывем объект в БД
        WorkDays day1 = WorkDays.builder()
                .day(LocalDate.of(2022, 12, 23))
                .build();

        WorkDays day2 = WorkDays.builder()
                .day(LocalDate.parse("2022-12-24"))
                .build();


        Employee employee1 = Employee.builder()
                .firstName("Marina")
                .lastName("Petrova")
                .mail("some@gmail")
                .phone("050****")
                .build();

        Employee employee2 = Employee.builder()
                .firstName("Katya")
                .lastName("Ivanova")
                .mail("some@gmail")
                .phone("050****")
                .build();


        WorkingShift shift1 = WorkingShift.builder()
                .start(LocalTime.of(0, 0))
                .end(LocalTime.of(04,00))
                .build();

        WorkingShift shift2 = WorkingShift.builder()
                .start(LocalTime.of(4, 0))
                .end(LocalTime.of(8,0))
                .build();


        EmpDayFK fk = new EmpDayFK();
        fk.setEmployee(employee1);
        fk.setWorkDay(day1);

        EmpDayFK fk1 = new EmpDayFK();
        fk1.setEmployee(employee2);
        fk1.setWorkDay(day2);


        Schedule schedule1 = Schedule.builder()
                .fk(fk)
                .shift(shift1)
                .build();

        Schedule schedule2 = Schedule.builder()
                .fk(fk1)
                .shift(shift2)
                .build();



//с помощью SessionFactory import org.hibernate.SessionFactory;

                SessionFactory factory = new Configuration().configure("hib.xml").buildSessionFactory();
////создаем Session
                Session session = factory.openSession();
                session.beginTransaction();
////добавляем объект через сессию


                session.save(shift1);
               session.save(shift2);

               session.save(day1);
                session.save(day2);

                session.save(employee1);
                session.save(employee2);

                session.save(schedule1);
               session.save(schedule2);

////подтверждаем изменения в БД
                session.getTransaction().commit();
////закрываем сессию
                session.close();
                factory.close();


    }
}