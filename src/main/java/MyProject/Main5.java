package MyProject;

import MyProject.hibernateSolutions.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class Main5 {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getFactory();
            Session session = factory.openSession();
        /**
            выборка HQL - работает
         */
//        Query query = session.createQuery("FROM Employee");
//        List employees = query.list();
//                session.close();
//        System.out.println(employees);

        /**
         выборка всех сущностей по какомуто-то поля HQL - работает
         */
//        String hql = "SELECT E.firstName FROM Employee E";
//        Query query = session.createQuery(hql);
//        List results = query.list();
//        System.out.println(results);

        /**
         выборка с WHERE, HQL - работает.
         выведет все данные сущности, выведет все сущности подходящие под WHERE
         */
//        String hql = "FROM Employee E WHERE E.id = 6";
//        Query query = session.createQuery(hql);
//        List results = query.list();
//        System.out.println(results);

        /**
         сортировка HQL - работает
         */
//        String hql = "FROM Employee E WHERE E.id > 1 ORDER BY E.firstName DESC";
//        Query query = session.createQuery(hql);
//        List results = query.list();
//        System.out.println(results);
        /**
         GROUP BY HQL - не работает. Надо попробовать на Schedule
         */
//        String hql = "SELECT SUM(E.salary), E.firtName FROM Employee E " +
//                "GROUP BY E.firstName";
//        Query query = session.createQuery(hql);
//        List results = query.list();
//        System.out.println(results);

        /**
         insert HQL - лучше использовать save, тут не работает
         */

//        session.beginTransaction();
//        Query query =
//            session.createQuery("INSERT INTO Employee (firstName, lastName, mail, phone) " +
//                    "select firstName, lastName, mail, phone from Employee where id = 1");
//
//            query.executeUpdate();
//        session.getTransaction().commit();
//        session.close();

/**
 update HQL - работает
 */
//        session.beginTransaction();
//        String hql = "UPDATE Employee SET firstName =: firstName  WHERE id =: employeeId";
//        Query query1 =
//                session.createQuery(hql);
//        query1.setParameter("firstName", "Serega");
//        query1.setParameter("employeeId", 4);
//        query1.executeUpdate();
//        session.getTransaction().commit();
//        session.close();
//

        /**
         удаление одной(нескольких) записей, HQL - работает
         */
//        session.beginTransaction();
//        String hql = "DELETE FROM Employee "; //  +
//             //   "WHERE id = :employee_id";
//        Query query = session.createQuery(hql);
//        //query.setParameter("employee_id", 4);
//        int result = query.executeUpdate();
//        System.out.println("Rows affected: " + result);
//        session.getTransaction().commit();
//        session.close();
    }
}
