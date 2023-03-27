package MyProject.entityDAO;

import MyProject.interfaces.intefacesDAO.IPlanDao;
import MyProject.interfaces.intefacesDAO.overalInterfacesDAO.MyDAOFactory;
import MyProject.entity.Plan;
import MyProject.entity.WorkDays;
import MyProject.entity.WorkingShift;
import MyProject.factory.MyDAOFactoryImpl;
import MyProject.util.hibernateSolutions.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlanDAOImpl implements IPlanDao {
    private final SessionFactory sessionFactory;

    public PlanDAOImpl() {
        sessionFactory = HibernateUtil.getFactory();
    }

    @Override
    public List<Plan> getAll() {
        List<Plan> daysList = null;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Plan> criteriaQuery = criteriaBuilder.createQuery(Plan.class);
            Root<Plan> root = criteriaQuery.from(Plan.class);
            criteriaQuery.select(root);
            Query<Plan> query = session.createQuery(criteriaQuery);
            daysList = query.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return daysList;
    }

    @Override
    public Optional<Plan> getById(final int id) {
        Plan plan = null;
        try (Session session = sessionFactory.openSession()) {
            plan = session.get(Plan.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(plan);
    }

    @Override
    public void deleteAll() {
        Transaction txn = null;
        try (Session session = sessionFactory.openSession()) {
            txn = session.beginTransaction();
            String hql = "DELETE FROM Plan";
            session.createQuery(hql).executeUpdate();
            txn.commit();
        } catch (HibernateException e) {
            if (txn != null) txn.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void createAll() {
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        List<WorkDays> days = factory.getWorkDaysDao().getAll();
        List<WorkingShift> shifts = factory.getWorkingShiftDao().getAll();
        Transaction txn = null;
        try (Session session = sessionFactory.openSession()) {
            txn = session.beginTransaction();
            session.createSQLQuery("ALTER TABLE plan AUTO_INCREMENT=0").executeUpdate();
            List<Plan> list = getPlan(days, shifts);
            for (Plan plan : list) {
                session.save(plan);
                session.flush();
            }
            txn.commit();
        } catch (HibernateException e) {
            if (txn != null) txn.rollback();
            e.printStackTrace();
        }
    }

    private List<Plan> getPlan(List<WorkDays> days, List<WorkingShift> shifts) {
        List<Plan> list = new ArrayList<>();
        boolean flag = false;
        int d = 0;
        while (!flag) {
            for (WorkingShift shift : shifts) {
                Plan plan = new Plan();
                plan.setDay(days.get(d));
                plan.setShift(shift);
                list.add(plan);
            }
            d++;
            if (d == days.size())
                flag = true;
        }
        return list;
    }
}