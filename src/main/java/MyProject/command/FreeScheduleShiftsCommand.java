package MyProject.command;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.FreeScheduleDao;
import MyProject.Intefaces.intefacesDAO.MyDAOFactory;
import MyProject.Intefaces.intefacesDAO.ScheduleDao;
import MyProject.commandHelper.MySortedFreeSchedule;
import MyProject.entity.Employee;
import MyProject.entity.FreeSchedule;
import MyProject.entity.Schedule;
import MyProject.factory.MyDAOFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.List;

public class FreeScheduleShiftsCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        ScheduleDao scheduleDao = factory.getScheduleDao();
        FreeScheduleDao freeScheduleDao = factory.getFreeScheduleDao();
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee.isAdmin()) {
            List<FreeSchedule> freeSchedules = freeScheduleDao.getAll();
            List<FreeSchedule> list = freeSchedules.stream()
                    .filter(s -> s.getDay().getDay().isAfter(LocalDate.now()))
                    .toList();
            request.setAttribute("freeSchedule", list);
        } else {
            List<Schedule> mySchedule = scheduleDao.getById(employee.getId());
            List<FreeSchedule> freeSchedules = freeScheduleDao.getAll();
            List<FreeSchedule> list = MySortedFreeSchedule.getAvailableFreeSchedule(mySchedule, freeSchedules);
            request.setAttribute("freeSchedule", list);
        }
        return "freeScheduleShift.jsp";
    }
}
