package MyProject.entityHelper;

import MyProject.Intefaces.intefacesDAO.WorkingShiftDao;
import MyProject.entity.Employee;
import MyProject.entity.WorkingShift;
import MyProject.hibernateSolutions.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class WorkingShiftHelper implements WorkingShiftDao {

    private static SessionFactory sessionFactory;

    public WorkingShiftHelper() {
        sessionFactory = HibernateUtil.getFactory();
    }

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
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<WorkingShift> root = criteriaQuery.from(WorkingShift.class);
        criteriaQuery.select(root);
        Query query = session.createQuery(criteriaQuery);
        List<WorkingShift> shifts = query.getResultList();
        session.close();
        return shifts;
    }

    @Override
    public void add(WorkingShift shift) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(shift);
        session.getTransaction().commit();
        session.close();
    }

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

    @Override
    public void deleteByID(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        WorkingShift shift = session.find(WorkingShift.class, id);
        session.delete(shift);
        session.getTransaction().commit();
        session.close();
    }
}
