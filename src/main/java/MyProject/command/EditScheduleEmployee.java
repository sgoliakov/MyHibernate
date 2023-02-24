package MyProject.command;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.MyDAOFactory;
import MyProject.Intefaces.intefacesDAO.ScheduleDao;
import MyProject.entity.Employee;
import MyProject.entity.wrapperEntity.WrapperSchedule;
import MyProject.factory.MyDAOFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

public class EditScheduleEmployee implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        ScheduleDao scheduleDao = factory.getScheduleDao();
        int id = Integer.parseInt(request.getParameter("id"));
        List<WrapperSchedule> listById = scheduleDao.getWrapperScheduleById(id);
        Optional<Employee> optional = factory.getEmployeeDao().getById(id);
        if (!listById.isEmpty() && optional.isPresent()) {
            request.setAttribute("employee",optional.get());
            request.setAttribute("listWrapperByID", listById);
        } else {
            request.setAttribute("isEmpty", "Not found schedule of Employee");
        }
        return "editSchedule.jsp";
    }
}
