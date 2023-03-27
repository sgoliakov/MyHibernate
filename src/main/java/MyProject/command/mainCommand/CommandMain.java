package MyProject.command.mainCommand;

import MyProject.interfaces.intefacesCommand.CommandInfo;
import MyProject.interfaces.intefacesDAO.overalInterfacesDAO.MyDAOFactory;
import MyProject.interfaces.intefacesDAO.IWorkDaysDao;
import MyProject.util.commandHelper.MySortedFreeSchedule;
import MyProject.entity.Employee;
import MyProject.entity.FreeSchedule;
import MyProject.entity.WorkDays;
import MyProject.entity.wrapperEntity.WrapperSchedule;
import MyProject.factory.MyDAOFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommandMain implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        Object o = request.getSession().getAttribute("employee");
        if (o instanceof Employee employee) {
            List<FreeSchedule> allFreeSchedules = factory.getFreeScheduleDao().getAll();
            Optional<List<WrapperSchedule>> opt = factory.getScheduleDao().getWrapperScheduleById(employee.getId());
            List<WrapperSchedule> schedules = opt.orElseGet(ArrayList::new);
            List<FreeSchedule> freeSchedules = MySortedFreeSchedule.getAvailableFreeSchedule(schedules, allFreeSchedules);
            request.setAttribute("freeSchedules", freeSchedules);
        } else {
            IWorkDaysDao workDaysDao = factory.getWorkDaysDao();
            List<WorkDays> days = workDaysDao.getAll();
            request.setAttribute("workDays", days);
        }
        return "main.jsp";
    }
}
