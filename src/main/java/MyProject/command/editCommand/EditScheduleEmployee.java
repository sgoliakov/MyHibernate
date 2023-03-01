package MyProject.command.editCommand;

import MyProject.interfaces.intefacesCommand.CommandInfo;
import MyProject.interfaces.intefacesDAO.overalInterfacesDAO.MyDAOFactory;
import MyProject.interfaces.intefacesDAO.IScheduleDao;
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
        IScheduleDao scheduleDao = factory.getScheduleDao();
        int id = Integer.parseInt(request.getParameter("id"));
        Optional<List<WrapperSchedule>> optList = scheduleDao.getWrapperScheduleById(id);
        Optional<Employee> optional = factory.getEmployeeDao().getById(id);
        if (optList.isPresent() && optional.isPresent()) {
            request.setAttribute("employee", optional.get());
            request.setAttribute("listWrapperByID", optList.get());
        } else {
            request.setAttribute("isEmpty", "Not found schedule of Employee");
        }
        return "editSchedule.jsp";
    }
}
