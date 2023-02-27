package MyProject.command;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.MyDAOFactory;
import MyProject.Intefaces.intefacesDAO.ScheduleDao;
import MyProject.entity.Employee;
import MyProject.entity.wrapperEntity.WrapperSchedule;
import MyProject.factory.MyDAOFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

public class ShowMyScheduleCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employee");
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        ScheduleDao scheduleDao = factory.getScheduleDao();
        Optional<List<WrapperSchedule>> optional = scheduleDao.getWrapperScheduleById(employee.getId());
        if (optional.isEmpty()) {
            request.setAttribute("notExists", "You don`t have shifts");
        } else {
            List<WrapperSchedule> byId = optional.get();
            session.setAttribute("myWrapperSchedule", byId);
        }
        return "mySchedule.jsp";
    }
}
