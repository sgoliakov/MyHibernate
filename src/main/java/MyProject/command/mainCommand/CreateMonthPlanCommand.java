package MyProject.command.mainCommand;

import MyProject.interfaces.intefacesCommand.CommandInfo;
import MyProject.interfaces.intefacesDAO.*;
import MyProject.interfaces.intefacesDAO.overalInterfacesDAO.MyDAOFactory;
import MyProject.factory.MyDAOFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;

public class CreateMonthPlanCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        IWorkingShiftDao workingShiftDao = factory.getWorkingShiftDao();
        IWorkDaysDao workDaysDao = factory.getWorkDaysDao();
        IScheduleDao scheduleDao = factory.getScheduleDao();
        IFreeScheduleDao freeScheduleDao = factory.getFreeScheduleDao();
        IPlanDao planDao = factory.getPlanDao();
        scheduleDao.deleteAll();
        freeScheduleDao.deleteAll();
        planDao.deleteAll();
        workDaysDao.deleteAll();
        workingShiftDao.deleteAll();
        workDaysDao.createFromDate(LocalDate.now());
        workingShiftDao.createAll();
        freeScheduleDao.createAll();
        planDao.createAll();
        return "controller?action=main";
    }
}
