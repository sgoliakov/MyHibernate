package MyProject.interfaces.intefacesDAO.overalInterfacesDAO;

import MyProject.interfaces.intefacesDAO.*;

public interface MyDAOFactory {
    IEmployeeDao getEmployeeDao();

    IWorkDaysDao getWorkDaysDao();

    IWorkingShiftDao getWorkingShiftDao();

    IScheduleDao getScheduleDao();

    IFreeScheduleDao getFreeScheduleDao();

    IPlanDao getPlanDao();
}
