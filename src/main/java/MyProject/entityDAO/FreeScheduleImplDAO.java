package MyProject.entityDAO;

import MyProject.Intefaces.intefacesDAO.FreeScheduleDao;
import MyProject.Intefaces.intefacesDAO.WorkDaysDao;
import MyProject.Intefaces.intefacesDAO.WorkingShiftDao;
import MyProject.entity.FreeSchedule;
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

public class FreeScheduleImplDAO implements FreeScheduleDao {
    private static SessionFactory sessionFactory;

    public FreeScheduleImplDAO() {
        sessionFactory = HibernateUtil.getFactory();
    }

    @Override
    public List<FreeSchedule> getAll() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<FreeSchedule> criteriaQuery = criteriaBuilder.createQuery(FreeSchedule.class);
        Root<FreeSchedule> root = criteriaQuery.from(FreeSchedule.class);
        criteriaQuery.select(root);
        Query<FreeSchedule> query = session.createQuery(criteriaQuery);
        List<FreeSchedule> freeSchedules = query.getResultList();
        session.close();
        return freeSchedules;
    }

    @Override
    public void createAll(WorkDaysDao workDaysDao, WorkingShiftDao workingShiftDao) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("ALTER TABLE freeschedule AUTO_INCREMENT=0").executeUpdate();
        List<WorkDays> days = workDaysDao.getAll();
        List<WorkingShift> shifts = workingShiftDao.getAll();
        List<FreeSchedule> list = getListFreeSchedule(days, shifts);
        for (FreeSchedule schedule : list) {
            session.save(schedule);
            session.flush();
        }
        session.getTransaction().commit();
        session.close();
    }

    private List<FreeSchedule> getListFreeSchedule(List<WorkDays> days, List<WorkingShift> shifts) {
        List<FreeSchedule> list = new ArrayList<>();
        boolean flag = false;
        int d = 0;
        while (!flag) {
            for (WorkingShift shift : shifts) {
                FreeSchedule freeSchedule = new FreeSchedule();
                freeSchedule.setDay(days.get(d));
                freeSchedule.setShift(shift);
                list.add(freeSchedule);
            }
            d++;
            if (d == days.size())
                flag = true;
        }
        return list;
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
        FreeSchedule day = session.get(FreeSchedule.class, id);
        session.delete(day);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "DELETE FROM FreeSchedule";
        session.createQuery(hql).executeUpdate();
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
