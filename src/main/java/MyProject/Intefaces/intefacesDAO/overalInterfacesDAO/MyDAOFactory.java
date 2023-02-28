package MyProject.Intefaces.intefacesDAO.overalInterfacesDAO;

import MyProject.Intefaces.intefacesDAO.*;

public interface MyDAOFactory {
    IEmployeeDao getEmployeeDao();

    IWorkDaysDao getWorkDaysDao();

    IWorkingShiftDao getWorkingShiftDao();

    IScheduleDao getScheduleDao();

    IFreeScheduleDao getFreeScheduleDao();

    IPlanDao getPlanDao();
}
