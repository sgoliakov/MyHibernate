package MyProject.command;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.MyDAOFactory;
import MyProject.Intefaces.intefacesDAO.WorkDaysDao;
import MyProject.commandHelper.MySortedFreeSchedule;
import MyProject.entity.Employee;
import MyProject.entity.FreeSchedule;
import MyProject.entity.WorkDays;
import MyProject.entity.wrapperEntity.WrapperSchedule;
import MyProject.factory.MyDAOFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class CommandMain implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        Employee employee = (Employee) request.getSession().getAttribute("employee");
        if (employee != null) {
            List<FreeSchedule> allFreeSchedules = factory.getFreeScheduleDao().getAll();
            List<WrapperSchedule> schedules = factory.getScheduleDao().getWrapperScheduleById(employee.getId());
            List<FreeSchedule> freeSchedules = MySortedFreeSchedule.getAvailableFreeSchedule(schedules, allFreeSchedules);
            request.setAttribute("freeSchedules", freeSchedules);
        } else {
            WorkDaysDao workDaysDao = factory.getWorkDaysDao();
            List<WorkDays> days = workDaysDao.getAll();
            request.setAttribute("workDays", days);
        }
        return "main.jsp";
    }
}
