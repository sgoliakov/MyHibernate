package MyProject.command;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.MyDAOFactory;
import MyProject.Intefaces.intefacesDAO.WorkDaysDao;
import MyProject.entity.WorkDays;
import MyProject.factory.MyDAOFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class DeleteWorkDayCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        WorkDaysDao workDaysDao = factory.getWorkDaysDao();
        Optional<WorkDays> byId = workDaysDao.getById(id);
        if (byId.isPresent()) {
            workDaysDao.delete(byId.get().getId());
            request.setAttribute("deleted", "deleted is OK");
        } else request.setAttribute("deleted", "deleted is failed");
        return "controller?action=show_work_day";
    }
}
