package MyProject.entityDAO;

import MyProject.Intefaces.intefacesDAO.WorkingShiftDao;
import MyProject.entity.WorkingShift;
import MyProject.hibernateSolutions.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

public class WorkingShiftImplDAO implements WorkingShiftDao {

    private static SessionFactory sessionFactory;

    public WorkingShiftImplDAO() {
        sessionFactory = HibernateUtil.getFactory();
    }
//нету
    @Override
    public Optional<WorkingShift> getById(int id) {
        Session session = sessionFactory.openSession();
        WorkingShift shift = session.get(WorkingShift.class, id);
        session.close();
        return Optional.ofNullable(shift);
    }

    @Override
    public List<WorkingShift> getAll() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<WorkingShift> criteriaQuery = criteriaBuilder.createQuery(WorkingShift.class);
        Root<WorkingShift> root = criteriaQuery.from(WorkingShift.class);
        criteriaQuery.select(root);
        Query<WorkingShift> query = session.createQuery(criteriaQuery);
        List<WorkingShift> shifts = query.getResultList();
        session.close();
        return shifts;
    }
//нету
    @Override
    public void add(WorkingShift shift) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(shift);
        session.getTransaction().commit();
        session.close();
    }
//нуту
    @Override
    public void update(int id, LocalTime[] params) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        WorkingShift workingShift = session.get(WorkingShift.class, id);
        workingShift.setStart(params[0]);
        workingShift.setEnd(params[1]);
        session.update(workingShift);
        session.getTransaction().commit();
        session.close();
    }
//нету
    @Override
    public void deleteByID(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        WorkingShift shift = session.get(WorkingShift.class, id);
        session.delete(shift);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void createAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("ALTER TABLE workingshift AUTO_INCREMENT=0").executeUpdate();
        List<WorkingShift> list = getShift();
        for (WorkingShift shift : list) {
            session.save(shift);
        }
        session.getTransaction().commit();
        session.close();
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

    @Override
    public void deleteAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<WorkingShift> criteriaDelete = builder.createCriteriaDelete(WorkingShift.class);
        criteriaDelete.from(WorkingShift.class);
        session.createQuery(criteriaDelete).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
