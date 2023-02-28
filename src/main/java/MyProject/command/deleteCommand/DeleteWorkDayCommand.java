package MyProject.command.deleteCommand;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.MyDAOFactory;
import MyProject.Intefaces.intefacesDAO.IWorkDaysDao;
import MyProject.entity.WorkDays;
import MyProject.factory.MyDAOFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class DeleteWorkDayCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        IWorkDaysDao workDaysDao = factory.getWorkDaysDao();
        Optional<WorkDays> byId = workDaysDao.getById(id);
        if (byId.isPresent()) {
            workDaysDao.deleteByID(byId.get().getId());
            request.setAttribute("deleted", "deleted is OK");
        } else request.setAttribute("deleted", "deleted is failed");
        return "controller?action=show_work_day";
    }
}
