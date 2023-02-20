package MyProject.entityDAO;

import MyProject.Intefaces.intefacesDAO.ScheduleDao;
import MyProject.entity.Employee;
import MyProject.entity.Schedule;
import MyProject.entity.WorkDays;
import MyProject.entityDAO.FK.EmpDayFK;
import MyProject.hibernateSolutions.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ScheduleImplDAO implements ScheduleDao {
    private static SessionFactory sessionFactory;

    public ScheduleImplDAO() {
        sessionFactory = HibernateUtil.getFactory();
    }

    @Override
    public List<Schedule> getById(int id) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Schedule> criteriaQuery = criteriaBuilder.createQuery(Schedule.class);
        Root<Schedule> root = criteriaQuery.from(Schedule.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("fk").get("employee").get("id"), id));
        Query<Schedule> query = session.createQuery(criteriaQuery);
        List<Schedule> schedules = query.getResultList();
        session.close();
        return schedules;
    }

    @Override
    public List<Schedule> getAll() {
        List<Schedule> list = null;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Schedule> cq = cb.createQuery(Schedule.class);
            Root<Schedule> shiftRoot = cq.from(Schedule.class);
            cq.select(shiftRoot);
            Query<Schedule> query = session.createQuery(cq);
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
        int result = session.createQuery(hql)
                .setParameter("employee_id", fk)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void deleteAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "DELETE FROM Schedule";
        session.createQuery(hql).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
