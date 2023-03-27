package MyProject.entityDAO;

import MyProject.interfaces.intefacesDAO.IWorkDaysDao;
import MyProject.entity.WorkDays;
import MyProject.util.hibernateSolutions.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class WorkDaysDAOImpl implements IWorkDaysDao {
    private final SessionFactory sessionFactory;

    public WorkDaysDAOImpl() {
        sessionFactory = HibernateUtil.getFactory();
    }

    @Override
    public List<WorkDays> getAll() {
        List<WorkDays> daysList = null;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<WorkDays> criteriaQuery = criteriaBuilder.createQuery(WorkDays.class);
            Root<WorkDays> root = criteriaQuery.from(WorkDays.class);
            criteriaQuery.select(root);
            Query<WorkDays> query = session.createQuery(criteriaQuery);
            daysList = query.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return daysList;
    }

    @Override
    public Optional<WorkDays> getById(final int id) {
        WorkDays day = null;
        try (Session session = sessionFactory.openSession()) {
            day = session.get(WorkDays.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(day);
    }

    @Override
    public void deleteByID(final int id) {
        Transaction txn = null;
        try (Session session = sessionFactory.openSession()) {
            txn = session.beginTransaction();
            WorkDays day = session.get(WorkDays.class, id);
            session.delete(day);
            txn.commit();
        } catch (HibernateException e) {
            if (txn != null) txn.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        Transaction txn = null;
        try (Session session = sessionFactory.openSession()) {
            txn = session.beginTransaction();
            String hql = "DELETE FROM WorkDays ";
            session.createQuery(hql).executeUpdate();
        } catch (HibernateException e) {
            if (txn != null) txn.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void createFromDate(final LocalDate date) {
        Transaction txn = null;
        try (Session session = sessionFactory.openSession()) {
            txn = session.beginTransaction();
            session.createSQLQuery("ALTER TABLE workdays AUTO_INCREMENT=0").executeUpdate();
            for (int i = 0; i < 30; i++) {
                WorkDays day = new WorkDays();
                day.setDay(date.plusDays(i));
                session.save(day);
            }
        } catch (HibernateException e) {
            if (txn != null) txn.rollback();
            e.printStackTrace();
        }
    }
}
