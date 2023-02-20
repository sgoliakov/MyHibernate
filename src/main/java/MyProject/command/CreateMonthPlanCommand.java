package MyProject.command;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.*;
import MyProject.factory.MyDAOFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;

public class CreateMonthPlanCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        WorkingShiftDao workingShiftDao = factory.getWorkingShiftDao();
        WorkDaysDao workDaysDao = factory.getWorkDaysDao();
        ScheduleDao scheduleDao = factory.getScheduleDao();
        FreeScheduleDao freeScheduleDao = factory.getFreeScheduleDao();
        PlanDao planDao = factory.getPlanDao();
        scheduleDao.deleteAll();
        freeScheduleDao.deleteAll();
        planDao.deleteAll();
        workDaysDao.deleteAll();
        workingShiftDao.deleteAll();
        workDaysDao.createFromDate(LocalDate.now());
        workingShiftDao.createAll();
        freeScheduleDao.createAll(workDaysDao, workingShiftDao);
        planDao.createPlan(workDaysDao, workingShiftDao);
        return "controller?action=main";
    }
}
