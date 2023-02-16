package MyProject.command;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.*;
import MyProject.factory.FactoryDAO;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;

public class CreateMonthPlanCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        MyDAOFactory factory = FactoryDAO.getFactory();
        WorkDaysDao workDaysDao = factory.getWorkDaysDao();
        ScheduleDao scheduleDao = factory.getScheduleDao();
        FreeScheduleDao freeScheduleDao = factory.getFreeScheduleDao();
        PlanDao planDao = factory.getPlanDao();
        scheduleDao.deleteAll();
        freeScheduleDao.deleteAll();
        planDao.deleteAll();
        workDaysDao.deleteAll();
        workDaysDao.createFromDate(LocalDate.now());
        freeScheduleDao.createAll();
        planDao.createPlan();
        return "controller?action=main";
    }
}
