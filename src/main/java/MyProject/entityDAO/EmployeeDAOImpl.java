package MyProject.entityDAO;

import MyProject.interfaces.intefacesDAO.IEmployeeDao;
import MyProject.entity.Employee;
import MyProject.hibernateSolutions.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class EmployeeDAOImpl implements IEmployeeDao {
    private final SessionFactory sessionFactory;

    public EmployeeDAOImpl() {
        sessionFactory = HibernateUtil.getFactory();
    }

    @Override
    public Optional<Employee> getById(final int id) {
        Employee employee = null;
        try (Session session = sessionFactory.openSession()) {
            employee = session.get(Employee.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(employee);
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = null;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);
            criteriaQuery.select(root);
            Query<Employee> query = session.createQuery(criteriaQuery);
            employees = query.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public void deleteAll() {
        Transaction txn = null;
        try (Session session = sessionFactory.openSession()) {
            txn = session.beginTransaction();
            String hql = "DELETE FROM Employee ";
            session.createQuery(hql).executeUpdate();
            txn.commit();
        } catch (HibernateException e) {
            if (txn != null) txn.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void add(final Employee emp) {
        Transaction txn = null;
        try (Session session = sessionFactory.openSession()) {
            txn = session.beginTransaction();
            session.save(emp);
            txn.commit();
        } catch (HibernateException e) {
            if (txn != null) txn.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void updateByID(final int id, String[] params) {
        Transaction txn = null;
        try (Session session = sessionFactory.openSession()) {
            txn = session.beginTransaction();
            Employee employeeUpdate = session.get(Employee.class, id);
            employeeUpdate.setNickName(params[0]);
            employeeUpdate.setLastName(params[1]);
            employeeUpdate.setMail(params[2]);
            employeeUpdate.setPhone(params[3]);
            employeeUpdate.setPassword(params[4]);
            session.update(employeeUpdate);
            txn.commit();
        } catch (HibernateException e) {
            if (txn != null) txn.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByID(final int id) {
        Transaction txn = null;
        try (Session session = sessionFactory.openSession()) {
            txn = session.beginTransaction();
            Employee empDelete = session.get(Employee.class, id);
            session.delete(empDelete);
            txn.commit();
        } catch (HibernateException e) {
            if (txn != null) txn.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Employee> getByLogin(final String name, final String password) {
        Employee employee = null;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);
            criteriaQuery.where(builder.and
                    (builder.equal(root.get("nickName"), name),
                            builder.equal(root.get("password"), password)));
            Query<Employee> query = session.createQuery(criteriaQuery);
            try {
                employee = query.getSingleResult();
            } catch (NoResultException e) {
                System.out.println("employee not found");
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(employee);
    }

    @Override
    public Long amountEmp() {
        Long result = 0L;
        try (Session session = sessionFactory.openSession()) {
            String hqlQuery = "select count(*) from Employee";
            result = (Long) session.createQuery(hqlQuery).getSingleResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return result;
    }
}