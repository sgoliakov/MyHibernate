package MyProject.command.updateCommand;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.MyDAOFactory;
import MyProject.Intefaces.intefacesDAO.IWorkingShiftDao;
import MyProject.entity.WorkingShift;
import MyProject.factory.MyDAOFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class UpdateShiftCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String first = request.getParameter("start");
        String second = request.getParameter("end");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime start = LocalTime.parse(first, format);
        LocalTime end = LocalTime.parse(second, format);
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        IWorkingShiftDao workingShiftDao = factory.getWorkingShiftDao();
        Optional<WorkingShift> optional = workingShiftDao.getById(id);
        if (optional.isPresent()) {
            workingShiftDao.update(id, start, end);
            request.setAttribute("updated", "shift is updated");
        } else request.setAttribute("updated", "update is failed");
        return "controller?action=show_shift";
    }
}
