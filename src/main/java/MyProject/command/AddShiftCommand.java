package MyProject.command;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.FreeScheduleDao;
import MyProject.Intefaces.intefacesDAO.MyDAOFactory;
import MyProject.entity.Employee;
import MyProject.entity.FreeSchedule;
import MyProject.entity.Schedule;
import MyProject.entityHelper.FK.EmpDayFK;
import MyProject.factory.FactoryDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class AddShiftCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        MyDAOFactory factory = FactoryDAO.getFactory();
        FreeScheduleDao freeScheduleDao = factory.getFreeScheduleDao();
        Employee employee = (Employee) session.getAttribute("employee");
        int id = Integer.parseInt(request.getParameter("id"));
        Optional<FreeSchedule> optional = freeScheduleDao.getById(id);
        if (optional.isPresent()) {
            FreeSchedule freeSchedule = optional.get();
            freeScheduleDao.removeByID(freeSchedule.getId());
            EmpDayFK fk = EmpDayFK.builder()
                    .employee(employee)
                    .workDay(freeSchedule.getDay())
                    .build();
            Schedule schedule = Schedule.builder()
                    .fk(fk)
                    .shift(freeSchedule.getShift())
                    .build();
            factory.getScheduleDao().add(schedule);
        } else {
            request.setAttribute("notExists", "This schedule not exists");
        }
        return "controller?action=schedule_by_id";
    }
}
