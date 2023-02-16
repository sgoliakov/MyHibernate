package MyProject.command;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.*;
import MyProject.entity.FreeSchedule;
import MyProject.entity.Schedule;
import MyProject.entity.WorkDays;
import MyProject.factory.FactoryDAO;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.List;

public class ReportCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        MyDAOFactory factory = FactoryDAO.getFactory();
        EmployeeDao employeeDao = factory.getEmployeeDao();
        Long amountEmp = employeeDao.amountEmp();
        ScheduleDao scheduleDao = factory.getScheduleDao();
        List<Schedule> list = scheduleDao.getAll();
        FreeScheduleDao freeScheduleDao = factory.getFreeScheduleDao();
        List<FreeSchedule> freeList = freeScheduleDao.getAll();
        LocalDate date = LocalDate.now();
        WorkDaysDao workDaysDao = factory.getWorkDaysDao();
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
