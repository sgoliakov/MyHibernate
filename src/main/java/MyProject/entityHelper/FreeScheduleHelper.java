package MyProject.entityHelper;

import MyProject.Intefaces.intefacesDAO.FreeScheduleDao;
import MyProject.Intefaces.intefacesDAO.MyDAOFactory;
import MyProject.Intefaces.intefacesDAO.WorkDaysDao;
import MyProject.Intefaces.intefacesDAO.WorkingShiftDao;
import MyProject.entity.FreeSchedule;
import MyProject.entity.Plan;
import MyProject.entity.WorkDays;
import MyProject.entity.WorkingShift;
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

public class FreeScheduleHelper implements FreeScheduleDao {
    private static SessionFactory sessionFactory;

    public FreeScheduleHelper(){
        sessionFactory = HibernateUtil.getFactory();
    }


    @Override
    public List<FreeSchedule> getAll() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(FreeSchedule.class);
        Root<FreeSchedule> root = criteriaQuery.from(FreeSchedule.class);
        criteriaQuery.select(root);
        Query query = session.createQuery(criteriaQuery);
        List<FreeSchedule> freeSchedules = query.getResultList();

        session.close();

        return freeSchedules;
    }

    @Override
    public void createAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        deleteAll();
        session.createSQLQuery("ALTER TABLE freeschedule AUTO_INCREMENT=0").executeUpdate();

        MyDAOFactory factory = FactoryDAO.getFactory();
        WorkDaysDao workDaysDao = factory.getWorkDaysDao();
        WorkingShiftDao workingShiftDao = factory.getWorkingShiftDao();
        
            List<WorkDays> days = workDaysDao.getAll();
            List<WorkingShift> shifts = workingShiftDao.getAll();

            boolean flag = false;
            int d = 0;

        while (!flag) {
            for (int j = 0; j < shifts.size(); j++) {
                FreeSchedule freeSchedule = new FreeSchedule();
                 freeSchedule.setDay(days.get(d));
                    freeSchedule.setShift(shifts.get(j));
                      session.save(freeSchedule);
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

    @Override
    public void addFree(Plan p) {
        FreeSchedule freeSchedule = FreeSchedule.builder()
                .day(p.getDay())
                .shift(p.getShift())
                .build();
        Session session = sessionFactory.openSession();
          session.beginTransaction();
           session.save(freeSchedule);
            session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeByID(int id) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        FreeSchedule day = session.find(FreeSchedule.class, id);
        session.delete(day);

        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void deleteAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "DELETE FROM FreeSchedule";
        Query query = session.createQuery(hql);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Optional<FreeSchedule> getById(int id) {
        Session session = sessionFactory.openSession();
        FreeSchedule freeSchedule = session.get(FreeSchedule.class, id);
        session.close();
        return Optional.ofNullable(freeSchedule);
    }
}
