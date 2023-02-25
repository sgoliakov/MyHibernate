package MyProject.entityDAO;

import MyProject.Intefaces.intefacesDAO.WorkDaysDao;
import MyProject.entity.WorkDays;
import MyProject.hibernateSolutions.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class WorkDaysImplDAO implements WorkDaysDao {
    private static SessionFactory sessionFactory;

    public WorkDaysImplDAO() {
        sessionFactory = HibernateUtil.getFactory();
    }

    @Override
    public List<WorkDays> getAll() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<WorkDays> criteriaQuery = criteriaBuilder.createQuery(WorkDays.class);
        Root<WorkDays> root = criteriaQuery.from(WorkDays.class);
        criteriaQuery.select(root);
        Query<WorkDays> query = session.createQuery(criteriaQuery);
        List<WorkDays> daysList = query.getResultList();
        session.close();
        return daysList;
    }

    @Override
    public Optional<WorkDays> getById(int id) {
        Session session = sessionFactory.openSession();
        WorkDays day = session.get(WorkDays.class, id);
        session.close();
        return Optional.ofNullable(day);
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        WorkDays day = session.get(WorkDays.class, id);
        session.delete(day);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "DELETE FROM WorkDays ";
        session.createQuery(hql).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void createFromDate(LocalDate date) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("ALTER TABLE workdays AUTO_INCREMENT=0").executeUpdate();
        for (int i = 0; i < 30; i++) {
            WorkDays day = new WorkDays();
            day.setDay(date.plusDays(i));
            session.save(day);
        }
        session.getTransaction().commit();
        session.close();
    }
}
