package MyProject.command.showCommand;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.*;
import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.MyDAOFactory;
import MyProject.entity.FreeSchedule;
import MyProject.entity.Schedule;
import MyProject.entity.WorkDays;
import MyProject.factory.MyDAOFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.List;

public class ShowReportCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        IEmployeeDao employeeDao = factory.getEmployeeDao();
        Long amountEmp = employeeDao.amountEmp();
        IScheduleDao scheduleDao = factory.getScheduleDao();
        List<Schedule> list = scheduleDao.getAll();
        IFreeScheduleDao freeScheduleDao = factory.getFreeScheduleDao();
        List<FreeSchedule> freeList = freeScheduleDao.getAll();
        LocalDate date = LocalDate.now();
        IWorkDaysDao workDaysDao = factory.getWorkDaysDao();
        List<WorkDays> all = workDaysDao.getAll();
        WorkDays workDay = all.get(all.size() - 1);
        LocalDate lastDay = workDay.getDay();
        request.setAttribute("dayReport", date);
        request.setAttribute("lastDay", lastDay);
        request.setAttribute("amount", amountEmp);
        request.setAttribute("schedules", list);
        request.setAttribute("freeSchedules", freeList);
        return "report.jsp";
    }
}
