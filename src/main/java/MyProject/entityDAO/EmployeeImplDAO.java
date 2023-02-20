package MyProject.entityDAO;

import MyProject.Intefaces.intefacesDAO.EmployeeDao;
import MyProject.entity.Employee;
import MyProject.hibernateSolutions.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class EmployeeImplDAO implements EmployeeDao {
    private static SessionFactory sessionFactory;

    public EmployeeImplDAO() {
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
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root);
        Query<Employee> query = session.createQuery(criteriaQuery);
        Set<Employee> employees = new HashSet<>(query.getResultList());
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

    //нету, надо(editor)
    @Override
    public void updateByID(int id, String[] params) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Employee employeeUpdate = session.get(Employee.class, id);
        employeeUpdate.setNickName(params[0]);
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
        Employee empDelete = session.get(Employee.class, id);
        session.delete(empDelete);
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
                (builder.equal(root.get("nickName"), name),
                        builder.equal(root.get("password"), password)));
        Query<Employee> query = session.createQuery(criteriaQuery);
        Employee employee = null;
        try {
            employee = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("employee not found");
        } finally {
            session.close();
        }
        return Optional.ofNullable(employee);
    }

    @Override
    public Long amountEmp() {
        Session session = sessionFactory.openSession();
        String hqlQuery = "select count(*) from Employee";
        return (Long) session.createQuery(hqlQuery).getSingleResult();
    }
}