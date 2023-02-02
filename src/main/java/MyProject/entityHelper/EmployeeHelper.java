package MyProject.entityHelper;

import MyProject.Intefaces.intefacesDAO.EmployeeDao;
import MyProject.entity.Employee;
import MyProject.hibernateSolutions.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class EmployeeHelper implements EmployeeDao {
    private static SessionFactory sessionFactory;

    public EmployeeHelper(){
        sessionFactory = HibernateUtil.getFactory();
    }


    @Override
    public Optional<Employee> getById(int id) {
        Session session = sessionFactory.openSession();
        Employee employee = session.get(Employee.class, id);
        session.close();
        return Optional.ofNullable(employee);
    }

    @Override
    public Set<Employee> getAll() {

        Session session = sessionFactory.openSession();
//для обычных селектов транзакцию не открываем

    //первый этап подготовки запроса
//Получаем объект CriteriaBuilder для открытия CriteriaQuery
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
// Получаем объект CriteriaQuery c помощью которого будем формироваь наш запрос
CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Employee.class);//CriteriaQuery-подготовкаЗапроса
// получаем root - первостепенный, корневой ентити
//с помощью root мы указываем от какой таблицы мы отталкиваемся
//с помощью root как то можно делать специальные условия для выборки
        Root<Employee> root = criteriaQuery.from(Employee.class);//условия для запроса
//указываем что мы хотим выбрать+
        criteriaQuery.select(root); //необязательно, если просто нужно получить все значения
        //в root -размещаем условия запроса(пример ниже)
//получить работника по имени
     //   criteriaQuery.where( criteriaBuilder.equal( root.get("firstName"), "Bob" ) );


    //второй этап выполнение запроса
        Query query = session.createQuery(criteriaQuery);//сам запрос
        Set<Employee> employees = new HashSet(query.getResultList());
        session.close();

        return employees;
    }

    @Override
    public void add(Employee emp) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(emp);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void updateByID(int id, String[] params) {
      Session session = sessionFactory.openSession();
        session.beginTransaction();
        Employee employeeUpdate = session.get(Employee.class, id);
        employeeUpdate.setFirstName(params[0]);
        employeeUpdate.setLastName(params[1]);
        employeeUpdate.setMail(params[2]);
        employeeUpdate.setPhone(params[3]);
        session.update(employeeUpdate);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void deleteByID(int id) {
       Session session = sessionFactory.openSession();
        session.beginTransaction();
        Employee empDelete = session.find(Employee.class, id);
        session.delete(empDelete);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void deleteByName(String firstName, String lastName) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
    CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete criteriaDelete =builder.createCriteriaDelete(Employee.class);

        Root<Employee> root = criteriaDelete.from(Employee.class);

        criteriaDelete.where(builder.and
                (builder.equal(root.get("firstName"),firstName),
                builder.equal(root.get("lastName"),lastName)));
//еще пример сложного условия
//        cd.where(cb.or(
//                cb.and(
//                        cb.like(root.<String>get("name"), "%author%"),
//                        cb.like(root.<String>get("lastName"), "%2%")
//                ),
//                cb.equal(root.get("lastName"), "Lermontov")
//        ));

        Query query = session.createQuery(criteriaDelete);
                query.executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Optional<Employee> getByLogin(String name, String password) {

        Session session = sessionFactory.openSession();
         CriteriaBuilder builder = session.getCriteriaBuilder();
          CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);
           Root<Employee> root = criteriaQuery.from(Employee.class);
            criteriaQuery.where(builder.and
                    (builder.equal(root.get("firstName"),name),
                            builder.equal(root.get("password"),password)));

        Query query = session.createQuery(criteriaQuery);//сам запрос

        Employee employee = null;
        try {
            employee = (Employee) query.getSingleResult();

        }catch (NoResultException e){
            System.out.println("employee not found");
        }finally {
            session.close();
        }

     return Optional.ofNullable(employee);

    }

    @Override
    public Long amountEmp() {
        Session session = sessionFactory.openSession();
        String hqlQuery = "select count(*) from Employee";
        Query query = session.createQuery(hqlQuery);
        Long count = (Long) query.getSingleResult();
        return count;
    }
}
