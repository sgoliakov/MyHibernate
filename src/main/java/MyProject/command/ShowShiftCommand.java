package MyProject.command;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.MyDAOFactory;
import MyProject.Intefaces.intefacesDAO.WorkingShiftDao;
import MyProject.entity.WorkingShift;
import MyProject.factory.MyDAOFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class ShowShiftCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        WorkingShiftDao workingShiftDao = factory.getWorkingShiftDao();
        List<WorkingShift> list = workingShiftDao.getAll();
        if (!list.isEmpty()) {
            request.setAttribute("listShift", list);
        } else request.setAttribute("updated", "shifts not found");
        return "showShift.jsp";
    }
}
