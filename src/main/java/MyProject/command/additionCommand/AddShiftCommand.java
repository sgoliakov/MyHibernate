package MyProject.command.additionCommand;

import MyProject.entity.Employee;
import MyProject.entity.FreeSchedule;
import MyProject.entity.Schedule;
import MyProject.entityDAO.FK.EmpDayFK;
import MyProject.factory.MyDAOFactoryImpl;
import MyProject.interfaces.intefacesCommand.CommandInfo;
import MyProject.interfaces.intefacesDAO.IFreeScheduleDao;
import MyProject.interfaces.intefacesDAO.overalInterfacesDAO.MyDAOFactory;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class AddShiftCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        IFreeScheduleDao freeScheduleDao = factory.getFreeScheduleDao();
        Object o = request.getSession().getAttribute("employee");
        Employee employee;
        if (o instanceof Employee) {
            employee = (Employee) o;
        } else return "controller?action=main";
        int id = Integer.parseInt(request.getParameter("id"));
        Optional<FreeSchedule> optional = freeScheduleDao.getById(id);
        if (optional.isPresent()) {
            FreeSchedule freeSchedule = optional.get();
            freeScheduleDao.deleteByID(freeSchedule.getId());
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
            request.setAttribute("notExists", "This schedule not Exists");
        }
        return "controller?action=schedule_by_id";
    }
}
