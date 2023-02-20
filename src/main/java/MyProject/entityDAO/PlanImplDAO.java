package MyProject.entityDAO;

import MyProject.Intefaces.intefacesDAO.PlanDao;
import MyProject.Intefaces.intefacesDAO.WorkDaysDao;
import MyProject.Intefaces.intefacesDAO.WorkingShiftDao;
import MyProject.entity.Plan;
import MyProject.entity.WorkDays;
import MyProject.entity.WorkingShift;
import MyProject.hibernateSolutions.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlanImplDAO implements PlanDao {
    private static SessionFactory sessionFactory;

    public PlanImplDAO() {
        sessionFactory = HibernateUtil.getFactory();
    }

    @Override
    public List<Plan> getAll() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Plan> criteriaQuery = criteriaBuilder.createQuery(Plan.class);
        Root<Plan> root = criteriaQuery.from(Plan.class);
        criteriaQuery.select(root);
        Query<Plan> query = session.createQuery(criteriaQuery);
        List<Plan> daysList = query.getResultList();
        session.close();
        return daysList;
    }

    @Override
    public Optional<Plan> getById(int id) {
        Session session = sessionFactory.openSession();
        Plan plan = session.get(Plan.class, id);
        session.close();
        return Optional.ofNullable(plan);
    }

    @Override
    public void removeByID(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Plan day = session.get(Plan.class, id);
        session.delete(day);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "DELETE FROM Plan";
        session.createQuery(hql).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void createPlan(WorkDaysDao workDaysDao, WorkingShiftDao workingShiftDao) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("ALTER TABLE plan AUTO_INCREMENT=0").executeUpdate();
        List<WorkDays> days = workDaysDao.getAll();
        List<WorkingShift> shifts = workingShiftDao.getAll();
        List<Plan> list = getPlan(days, shifts);
        for (Plan plan : list) {
            session.save(plan);
            session.flush();
        }
        session.getTransaction().commit();
        session.close();
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
