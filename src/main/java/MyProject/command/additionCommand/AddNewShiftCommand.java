package MyProject.command.additionCommand;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.IFreeScheduleDao;
import MyProject.Intefaces.intefacesDAO.IPlanDao;
import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.MyDAOFactory;
import MyProject.entity.FreeSchedule;
import MyProject.entity.Plan;
import MyProject.factory.MyDAOFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class AddNewShiftCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        IFreeScheduleDao freeScheduleDao = factory.getFreeScheduleDao();
        IPlanDao planDao = factory.getPlanDao();
        int id = Integer.parseInt(request.getParameter("id"));
        Optional<Plan> optional = planDao.getById(id);
        if (optional.isPresent()) {
            Plan plan = optional.get();
            FreeSchedule freeSchedule = FreeSchedule.builder()
                    .day(plan.getDay())
                    .shift(plan.getShift())
                    .build();
            freeScheduleDao.add(freeSchedule);
            request.setAttribute("Add", "added");
        } else request.setAttribute("notAdd", "unsuccessful");
        return "showPlan.jsp";
    }
}
