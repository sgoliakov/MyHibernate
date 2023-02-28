package MyProject.entityDAO;

import MyProject.Intefaces.intefacesDAO.IWorkingShiftDao;
import MyProject.entity.WorkingShift;
import MyProject.hibernateSolutions.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WorkingShiftDAOImpl implements IWorkingShiftDao {

    private static SessionFactory sessionFactory;

    public WorkingShiftDAOImpl() {
        sessionFactory = HibernateUtil.getFactory();
    }

    @Override
    public Optional<WorkingShift> getById(int id) {
        WorkingShift shift = null;
        try (Session session = sessionFactory.openSession()) {
            shift = session.get(WorkingShift.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(shift);
    }

    @Override
    public List<WorkingShift> getAll() {
        List<WorkingShift> shifts = null;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<WorkingShift> criteriaQuery = criteriaBuilder.createQuery(WorkingShift.class);
            Root<WorkingShift> root = criteriaQuery.from(WorkingShift.class);
            criteriaQuery.select(root);
            Query<WorkingShift> query = session.createQuery(criteriaQuery);
            shifts = query.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return shifts;
    }

    @Override
    public void update(int id, LocalTime start, LocalTime end) {
        Transaction txn = null;
        try (Session session = sessionFactory.openSession()) {
            txn = session.beginTransaction();
            WorkingShift workingShift = session.get(WorkingShift.class, id);
            workingShift.setStart(start);
            workingShift.setEnd(end);
            session.update(workingShift);
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
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaDelete<WorkingShift> criteriaDelete = builder.createCriteriaDelete(WorkingShift.class);
            criteriaDelete.from(WorkingShift.class);
            session.createQuery(criteriaDelete).executeUpdate();
            txn.commit();
        } catch (HibernateException e) {
            if (txn != null) txn.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void createAll() {
        Transaction txn = null;
        try (Session session = sessionFactory.openSession()) {
            txn = session.beginTransaction();
            session.createSQLQuery("ALTER TABLE workingshift AUTO_INCREMENT=0").executeUpdate();
            List<WorkingShift> list = getShift();
            for (WorkingShift shift : list) {
                session.save(shift);
            }
            txn.commit();
        } catch (HibernateException e) {
            if (txn != null) txn.rollback();
            e.printStackTrace();
        }
    }

    private List<WorkingShift> getShift() {
        List<WorkingShift> list = new ArrayList<>();
        LocalTime start = LocalTime.of(0, 0);
        LocalTime end = LocalTime.of(4, 0);
        for (int i = 0; i < 6; i++) {
            WorkingShift workingShift = WorkingShift.builder()
                    .start(start)
                    .end(end)
                    .build();
            start = start.plus(4, ChronoUnit.HOURS);
            end = end.plus(4, ChronoUnit.HOURS);
            list.add(workingShift);
        }
        return list;
    }
}
