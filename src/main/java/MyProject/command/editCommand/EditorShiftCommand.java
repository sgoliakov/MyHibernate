package MyProject.command.editCommand;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.MyDAOFactory;
import MyProject.Intefaces.intefacesDAO.IWorkingShiftDao;
import MyProject.entity.WorkingShift;
import MyProject.factory.MyDAOFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class EditorShiftCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        IWorkingShiftDao workingShiftDao = factory.getWorkingShiftDao();
        Optional<WorkingShift> optional = workingShiftDao.getById(id);
        if (optional.isPresent()) {
            request.setAttribute("shift", optional.get());
        } else request.setAttribute("shift", "shift not found");
        return "editorShift.jsp";
    }
}
