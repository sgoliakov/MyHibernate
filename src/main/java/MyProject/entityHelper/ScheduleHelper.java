package MyProject.entityHelper;

import MyProject.Intefaces.intefacesDAO.ScheduleDao;
import MyProject.entity.Employee;
import MyProject.entity.Schedule;
import MyProject.entity.WorkDays;
import MyProject.entity.WorkingShift;
import MyProject.entityHelper.FK.EmpDayFK;
import MyProject.hibernateSolutions.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ScheduleHelper implements ScheduleDao {
    private static SessionFactory sessionFactory;

    public ScheduleHelper() {
        sessionFactory = HibernateUtil.getFactory();
    }

    @Override
    public List<Schedule> getById(int id) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Schedule.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("fk").get("employee").get("id"), id));
        javax.persistence.Query query = session.createQuery(criteriaQuery);
        List<Schedule> schedules = query.getResultList();
        session.close();
        return schedules;
    }

    @Override
    public List<Schedule> getAll() {
        List<Schedule> list = null;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery(Schedule.class);
            Root<WorkingShift> shiftRoot = cq.from(Schedule.class);
            cq.select(shiftRoot);
            Query query = session.createQuery(cq);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void add(Schedule schedule) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(schedule);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public int deleteByEmployeeDate(Employee emp, WorkDays day) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        EmpDayFK fk = new EmpDayFK();
        fk.setEmployee(emp);
        fk.setWorkDay(day);
        String hql = "DELETE FROM Schedule " +
                "WHERE id = :employee_id";
        Query query = session.createQuery(hql);
        query.setParameter("employee_id", fk);
        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void deleteAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "DELETE FROM Schedule";
        Query query = session.createQuery(hql);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
