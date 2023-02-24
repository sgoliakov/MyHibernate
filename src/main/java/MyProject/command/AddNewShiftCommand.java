package MyProject.command;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.FreeScheduleDao;
import MyProject.Intefaces.intefacesDAO.MyDAOFactory;
import MyProject.Intefaces.intefacesDAO.PlanDao;
import MyProject.entity.Plan;
import MyProject.factory.MyDAOFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class AddNewShiftCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        FreeScheduleDao freeScheduleDao = factory.getFreeScheduleDao();
        PlanDao planDao = factory.getPlanDao();
        int id = Integer.parseInt(request.getParameter("id"));
        Optional<Plan> optional = planDao.getById(id);
        if (optional.isPresent()) {
            Plan plan = optional.get();
            freeScheduleDao.addFree(plan);
            request.setAttribute("Add", "added");
        } else request.setAttribute("notAdd", "unsuccessful");
        return "showPlan.jsp";
    }
}
