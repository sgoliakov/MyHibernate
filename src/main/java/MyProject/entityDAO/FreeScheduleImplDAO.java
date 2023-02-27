package MyProject.entityDAO;

import MyProject.Intefaces.intefacesDAO.FreeScheduleDao;
import MyProject.Intefaces.intefacesDAO.WorkDaysDao;
import MyProject.Intefaces.intefacesDAO.WorkingShiftDao;
import MyProject.entity.FreeSchedule;
import MyProject.entity.Plan;
import MyProject.entity.WorkDays;
import MyProject.entity.WorkingShift;
import MyProject.hibernateSolutions.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
        List<FreeSchedule> freeSchedules = null;
        try (Session session = sessionFactory.openSession()) {
            freeSchedules = session.createQuery(
                            "select f from FreeSchedule f", FreeSchedule.class)
                    .getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return freeSchedules;
    }

    @Override
    public void createAll(WorkDaysDao workDaysDao, WorkingShiftDao workingShiftDao) {
        Transaction txn = null;
        try (Session session = sessionFactory.openSession()) {
            txn = session.beginTransaction();
            session.createSQLQuery("ALTER TABLE freeschedule AUTO_INCREMENT=0").executeUpdate();
            List<WorkDays> days = workDaysDao.getAll();
            List<WorkingShift> shifts = workingShiftDao.getAll();
            List<FreeSchedule> list = getListFreeSchedule(days, shifts);
            for (FreeSchedule schedule : list) {
                session.save(schedule);
                session.flush();
            }
            txn.commit();
        } catch (HibernateException e) {
            if (txn != null) txn.rollback();
            e.printStackTrace();
        }
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
    public void add(Plan p) {
        Transaction txn = null;
        FreeSchedule freeSchedule = FreeSchedule.builder()
                .day(p.getDay())
                .shift(p.getShift())
                .build();
        try (Session session = sessionFactory.openSession()) {
            txn = session.beginTransaction();
            session.save(freeSchedule);
            txn.commit();
        } catch (HibernateException e) {
            if (txn != null) txn.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByID(int id) {
        Transaction txn = null;
        try (Session session = sessionFactory.openSession()) {
            txn = session.beginTransaction();
            FreeSchedule day = session.get(FreeSchedule.class, id);
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
            String hql = "DELETE FROM FreeSchedule";
            session.createQuery(hql).executeUpdate();
            txn.commit();
        } catch (HibernateException e) {
            if (txn != null) txn.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Optional<FreeSchedule> getById(int id) {
        FreeSchedule freeSchedule = null;
        try (Session session = sessionFactory.openSession()) {
            freeSchedule = session.get(FreeSchedule.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(freeSchedule);
    }
}
