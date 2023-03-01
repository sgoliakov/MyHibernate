package MyProject.command.showCommand;

import MyProject.interfaces.intefacesCommand.CommandInfo;
import MyProject.interfaces.intefacesDAO.overalInterfacesDAO.MyDAOFactory;
import MyProject.interfaces.intefacesDAO.IPlanDao;
import MyProject.entity.Plan;
import MyProject.factory.MyDAOFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.List;

public class ShowPlanCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        IPlanDao planDao = factory.getPlanDao();
        List<Plan> plans = planDao.getAll();
        List<Plan> list = plans.stream()
                .filter(s -> s.getDay().getDay().isAfter(LocalDate.now()))
                .toList();
        request.setAttribute("planSchedule", list);
        return "showPlan.jsp";
    }
}
