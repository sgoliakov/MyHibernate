package MyProject.command;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.MyDAOFactory;
import MyProject.Intefaces.intefacesDAO.ScheduleDao;
import MyProject.entity.Employee;
import MyProject.entity.WorkDays;
import MyProject.factory.MyDAOFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class DeleteScheduleEmployee implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        int idDay = Integer.parseInt(request.getParameter("day"));
        int idEmployee = Integer.parseInt(request.getParameter("id"));
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        ScheduleDao scheduleDao = factory.getScheduleDao();
        Optional<WorkDays> optDay = factory.getWorkDaysDao().getById(idDay);
        Optional<Employee> optEmployee = factory.getEmployeeDao().getById(idEmployee);
        if (optDay.isPresent() && optEmployee.isPresent()) {
            int result = scheduleDao.deleteByEmployeeDate(optEmployee.get(), optDay.get());
            String attribute = "deleted :" + result + "shifts";
            request.setAttribute("deleted", attribute);
        } else
            request.setAttribute("deleted", "deleted is failed");
        return "controller?action=edit_schedule";
    }
}
