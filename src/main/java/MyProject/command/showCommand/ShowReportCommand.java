package MyProject.command.showCommand;

import MyProject.entity.FreeSchedule;
import MyProject.entity.Schedule;
import MyProject.entity.WorkDays;
import MyProject.factory.MyDAOFactoryImpl;
import MyProject.interfaces.intefacesCommand.CommandInfo;
import MyProject.interfaces.intefacesDAO.overalInterfacesDAO.MyDAOFactory;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.List;

public class ShowReportCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        Long amountEmp = factory.getEmployeeDao().amountEmp();
        List<Schedule> list = factory.getScheduleDao().getAll();
        List<FreeSchedule> freeList = factory.getFreeScheduleDao().getAll();
        LocalDate date = LocalDate.now();
        List<WorkDays> daysList = factory.getWorkDaysDao().getAll();
        WorkDays workDay = daysList.get(daysList.size() - 1);
        LocalDate lastDay = workDay.getDay();
        request.setAttribute("dayReport", date);
        request.setAttribute("lastDay", lastDay);
        request.setAttribute("amount", amountEmp);
        request.setAttribute("schedules", list);
        request.setAttribute("freeSchedules", freeList);
        return "report.jsp";
    }
}
