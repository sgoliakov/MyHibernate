package MyProject.entityDAO;

import MyProject.Intefaces.intefacesDAO.ScheduleDao;
import MyProject.entity.Employee;
import MyProject.entity.Schedule;
import MyProject.entity.WorkDays;
import MyProject.entity.WorkingShift;
import MyProject.entity.wrapperEntity.WrapperSchedule;
import MyProject.entityDAO.FK.EmpDayFK;
import MyProject.hibernateSolutions.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class ScheduleImplDAO implements ScheduleDao {
    private static SessionFactory sessionFactory;

    public ScheduleImplDAO() {
        sessionFactory = HibernateUtil.getFactory();
    }

    @Override
    public Optional<List<WrapperSchedule>> getWrapperScheduleById(int id) {
        List<WrapperSchedule> schedules = null;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<WrapperSchedule> criteriaQuery = builder.createQuery(WrapperSchedule.class);
            Root<Schedule> root = criteriaQuery.from(Schedule.class);
            Path<WorkingShift> path1 = root.get("shift");
            Path<WorkDays> path2 = root.get("fk").get("workDay");
            criteriaQuery.select(builder.construct(WrapperSchedule.class, path1, path2));
            criteriaQuery.where(builder.equal(root.get("fk").get("employee").get("id"), id));
            Query<WrapperSchedule> query = session.createQuery(criteriaQuery);
            schedules = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(schedules);
    }

    @Override
    public Optional<Schedule> getById(int id) {
        Schedule schedule = null;
        try (Session session = sessionFactory.openSession()) {
            schedule = session.get(Schedule.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(schedule);
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
        Transaction txn = null;
        try (Session session = sessionFactory.openSession()) {
            txn = session.beginTransaction();
            session.save(schedule);
            txn.commit();
        } catch (Exception e) {
            if (txn != null) txn.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public int deleteByEmployeeDate(Employee emp, WorkDays day) {
        Transaction txn = null;
        int result = 0;
        try (Session session = sessionFactory.openSession()) {
            txn = session.beginTransaction();
            EmpDayFK fk = new EmpDayFK();
            fk.setEmployee(emp);
            fk.setWorkDay(day);
            String hql = "DELETE FROM Schedule WHERE id = :employee_id";
            result = session.createQuery(hql)
                    .setParameter("employee_id", fk)
                    .executeUpdate();
            txn.commit();
        } catch (HibernateException e) {
            if (txn != null) txn.rollback();
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void deleteAll() {
        Transaction txn = null;
        try (Session session = sessionFactory.openSession()) {
            txn = session.beginTransaction();
            String hql = "DELETE FROM Schedule";
            session.createQuery(hql).executeUpdate();
            txn.commit();
        } catch (HibernateException e) {
            if (txn != null) txn.rollback();
            e.printStackTrace();
        }
    }
}
