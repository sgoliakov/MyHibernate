package MyProject.entityHelper;

import MyProject.Intefaces.intefacesDAO.MyDAOFactory;
import MyProject.Intefaces.intefacesDAO.PlanDao;
import MyProject.Intefaces.intefacesDAO.WorkDaysDao;
import MyProject.Intefaces.intefacesDAO.WorkingShiftDao;
import MyProject.entity.*;
import MyProject.factory.FactoryDAO;
import MyProject.hibernateSolutions.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class PlanHelper implements PlanDao {

    private static SessionFactory sessionFactory;

    public PlanHelper(){
        sessionFactory = HibernateUtil.getFactory();
    }


    @Override
    public List<Plan> getAll() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Plan.class);//CriteriaQuery-подготовкаЗапроса
        Root<WorkDays> root = criteriaQuery.from(Plan.class);//условия для запроса
        criteriaQuery.select(root);
        javax.persistence.Query query = session.createQuery(criteriaQuery);
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
        Plan day = session.find(Plan.class, id);
        session.delete(day);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "DELETE FROM Plan";
        Query query = session.createQuery(hql);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void createPlan() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        MyDAOFactory factory = FactoryDAO.getFactory();
        WorkDaysDao workDaysDao = factory.getWorkDaysDao();
        WorkingShiftDao workingShiftDao = factory.getWorkingShiftDao();

        deleteAll();
        session.createSQLQuery("ALTER TABLE plan AUTO_INCREMENT=0").executeUpdate();

        List<WorkDays> days = workDaysDao.getAll();
        List<WorkingShift> shifts = workingShiftDao.getAll();

        boolean flag = false;
        int d = 0;

        while (!flag) {
            for (int j = 0; j < shifts.size(); j++) {
                Plan plan = new Plan();
                plan.setDay(days.get(d));
                plan.setShift(shifts.get(j));
                session.save(plan);
                session.flush();
            }
            d++;
            if (d == days.size()){
                flag = true;
            }
        }
        session.getTransaction().commit();
        session.close();

    }

}
