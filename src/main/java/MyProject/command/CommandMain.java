package MyProject.command;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.MyDAOFactory;
import MyProject.Intefaces.intefacesDAO.WorkDaysDao;
import MyProject.commandHelper.MySortedFreeSchedule;
import MyProject.entity.Employee;
import MyProject.entity.FreeSchedule;
import MyProject.entity.Schedule;
import MyProject.entity.WorkDays;
import MyProject.factory.FactoryDAO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class CommandMain implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        MyDAOFactory myDAOFactory = FactoryDAO.getFactory();
        Employee employee = (Employee) request.getSession().getAttribute("employee");
        if (employee != null) {
            List<FreeSchedule> allFreeSchedules = myDAOFactory.getFreeScheduleDao().getAll();
            List<Schedule> schedules = myDAOFactory.getScheduleDao().getById(employee.getId());
            List<FreeSchedule> freeSchedules = MySortedFreeSchedule.getAvailableFreeSchedule(schedules, allFreeSchedules);
            request.setAttribute("freeSchedules", freeSchedules);
        } else {
            WorkDaysDao workDaysDao = myDAOFactory.getWorkDaysDao();
            List<WorkDays> days = workDaysDao.getAll();
            request.setAttribute("workDays", days);
        }
        return "main.jsp";
    }
}
