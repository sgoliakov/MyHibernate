package MyProject.command;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.MyDAOFactory;
import MyProject.Intefaces.intefacesDAO.PlanDao;
import MyProject.entity.Plan;
import MyProject.factory.FactoryDAO;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.List;

public class ShowPlanCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {

        MyDAOFactory factory = FactoryDAO.getFactory();
        PlanDao planDao = factory.getPlanDao();
        List<Plan> plans = planDao.getAll();
        List<Plan> list = plans.stream()
                .filter(s -> s.getDay().getDay().isAfter(LocalDate.now()))
                .toList();

        request.setAttribute("planSchedule", list);

        return "adminCabinet.jsp";
    }
}
