package MyProject.command.additionCommand;

import MyProject.entity.FreeSchedule;
import MyProject.entity.Plan;
import MyProject.factory.MyDAOFactoryImpl;
import MyProject.interfaces.intefacesCommand.CommandInfo;
import MyProject.interfaces.intefacesDAO.overalInterfacesDAO.MyDAOFactory;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class AddNewShiftCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        int id = Integer.parseInt(request.getParameter("id"));
        Optional<Plan> optional = factory.getPlanDao().getById(id);
        if (optional.isPresent()) {
            Plan plan = optional.get();
            FreeSchedule freeSchedule = FreeSchedule.builder()
                    .day(plan.getDay())
                    .shift(plan.getShift())
                    .build();
            factory.getFreeScheduleDao().add(freeSchedule);
            request.setAttribute("Add", "added");
        } else request.setAttribute("notAdd", "unsuccessful");
        return "showPlan.jsp";
    }
}
