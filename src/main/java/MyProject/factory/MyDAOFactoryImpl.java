package MyProject.factory;

import MyProject.interfaces.intefacesDAO.*;
import MyProject.interfaces.intefacesDAO.overalInterfacesDAO.MyDAOFactory;
import MyProject.entityDAO.*;

public class MyDAOFactoryImpl implements MyDAOFactory {
    private static MyDAOFactory factory;

    private MyDAOFactoryImpl() {
    }

    public static synchronized MyDAOFactory getFactory() {
        if (factory == null) {
            factory = new MyDAOFactoryImpl();
        }
        return factory;
    }

    @Override
    public IEmployeeDao getEmployeeDao() {
        return new EmployeeDAOImpl();
    }

    @Override
    public IWorkDaysDao getWorkDaysDao() {
        return new WorkDaysDAOImpl();
    }

    @Override
    public IWorkingShiftDao getWorkingShiftDao() {
        return new WorkingShiftDAOImpl();
    }

    @Override
    public IScheduleDao getScheduleDao() {
        return new ScheduleDAOImpl();
    }

    @Override
    public IFreeScheduleDao getFreeScheduleDao() {
        return new FreeScheduleDAOImpl();
    }

    @Override
    public IPlanDao getPlanDao() {
        return new PlanDAOImpl();
    }
}
