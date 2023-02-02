package MyProject.command;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.FreeScheduleDao;
import MyProject.Intefaces.intefacesDAO.MyDAOFactory;
import MyProject.Intefaces.intefacesDAO.PlanDao;
import MyProject.entity.Plan;
import MyProject.factory.FactoryDAO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class AddNewShiftCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        MyDAOFactory factory = FactoryDAO.getFactory();
        FreeScheduleDao freeScheduleDao = factory.getFreeScheduleDao();
        PlanDao planDao = factory.getPlanDao();

        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        Optional<Plan> optional = planDao.getById(id);

        if (optional.isPresent()){
            Plan plan = optional.get();
            System.out.println(plan);
            freeScheduleDao.addFree(plan);
          request.setAttribute("Add", "added");
        }else request.setAttribute("notAdd", "unsuccessful");

        return "adminCabinet.jsp";
    }
}
