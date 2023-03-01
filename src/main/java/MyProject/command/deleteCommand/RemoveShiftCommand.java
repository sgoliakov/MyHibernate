package MyProject.command.deleteCommand;

import MyProject.interfaces.intefacesCommand.CommandInfo;
import MyProject.interfaces.intefacesDAO.IFreeScheduleDao;
import MyProject.interfaces.intefacesDAO.overalInterfacesDAO.MyDAOFactory;
import MyProject.factory.MyDAOFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.HibernateException;

public class RemoveShiftCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        IFreeScheduleDao freeScheduleDao = factory.getFreeScheduleDao();
        try {
            freeScheduleDao.deleteByID(id);
            request.setAttribute("Remove", "ok");
        } catch (HibernateException e) {
            request.setAttribute("Remove", "not");
        }
        return "controller?action=free_schedule_shifts";
    }
}
