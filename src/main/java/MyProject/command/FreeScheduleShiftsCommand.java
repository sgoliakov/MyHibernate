package MyProject.command;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.FreeScheduleDao;
import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.MyDAOFactory;
import MyProject.Intefaces.intefacesDAO.ScheduleDao;
import MyProject.commandHelper.MySortedFreeSchedule;
import MyProject.entity.Employee;
import MyProject.entity.FreeSchedule;
import MyProject.entity.wrapperEntity.WrapperSchedule;
import MyProject.factory.MyDAOFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class FreeScheduleShiftsCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        ScheduleDao scheduleDao = factory.getScheduleDao();
        FreeScheduleDao freeScheduleDao = factory.getFreeScheduleDao();
        Employee employee = (Employee) request.getSession().getAttribute("employee");
        if (employee.isAdmin()) {
            List<FreeSchedule> freeSchedules = freeScheduleDao.getAll();
            List<FreeSchedule> list = freeSchedules.stream()
                    .filter(s -> s.getDay().getDay().isAfter(LocalDate.now()))
                    .sorted(Comparator.comparing(s -> s.getDay().getDay()))
                    .toList();
            request.setAttribute("freeSchedule", list);
        } else {
            Optional<List<WrapperSchedule>> opt = scheduleDao.getWrapperScheduleById(employee.getId());
            List<WrapperSchedule> mySchedule = opt.orElseGet(ArrayList::new);
            List<FreeSchedule> freeSchedules = freeScheduleDao.getAll();
            List<FreeSchedule> list = MySortedFreeSchedule.getAvailableFreeSchedule(mySchedule, freeSchedules);
            request.setAttribute("freeSchedule", list);
        }
        return "freeScheduleShift.jsp";
    }
}
