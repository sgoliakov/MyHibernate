package MyProject.command;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.MyDAOFactory;
import MyProject.entity.WorkDays;
import MyProject.factory.MyDAOFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class ShowWorkDayCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        List<WorkDays> list = factory.getWorkDaysDao().getAll();
        if (!list.isEmpty()) {
            request.setAttribute("workDays", list);
        } else request.setAttribute("isEmpty", "list is Empty");
        return "showWorkDay.jsp";
    }
}
