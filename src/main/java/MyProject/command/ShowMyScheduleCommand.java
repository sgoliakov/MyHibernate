package MyProject.command;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.MyDAOFactory;
import MyProject.Intefaces.intefacesDAO.ScheduleDao;
import MyProject.entity.Employee;
import MyProject.entity.Schedule;
import MyProject.factory.FactoryDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class ShowMyScheduleCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employee");
        MyDAOFactory factory = FactoryDAO.getFactory();
        ScheduleDao scheduleDao = factory.getScheduleDao();
        List<Schedule> byId = scheduleDao.getById(employee.getId());
        if (byId.isEmpty()) {
            request.setAttribute("notExists", "You don`t have shifts");
        } else {
            session.setAttribute("mySchedule", byId);
        }
        return "mySchedule.jsp";
    }
}
