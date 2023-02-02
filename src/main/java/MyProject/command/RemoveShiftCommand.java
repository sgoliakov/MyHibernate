package MyProject.command;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.FreeScheduleDao;
import MyProject.Intefaces.intefacesDAO.MyDAOFactory;
import MyProject.factory.FactoryDAO;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.HibernateException;

public class RemoveShiftCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        MyDAOFactory factory = FactoryDAO.getFactory();
        FreeScheduleDao freeScheduleDao = factory.getFreeScheduleDao();
        try {
            freeScheduleDao.removeByID(id);
            request.setAttribute("Remove", "ok");
        } catch (HibernateException e) {
            request.setAttribute("Remove", "not");
        }
        return "controller?action=free_schedule_shifts";

    }
}
