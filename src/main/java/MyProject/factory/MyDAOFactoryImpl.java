package MyProject.factory;

import MyProject.Intefaces.intefacesDAO.*;
import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.MyDAOFactory;
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
    public EmployeeDao getEmployeeDao() {
        return new EmployeeImplDAO();
    }

    @Override
    public WorkDaysDao getWorkDaysDao() {
        return new WorkDaysImplDAO();
    }

    @Override
    public WorkingShiftDao getWorkingShiftDao() {
        return new WorkingShiftImplDAO();
    }

    @Override
    public ScheduleDao getScheduleDao() {
        return new ScheduleImplDAO();
    }

    @Override
    public FreeScheduleDao getFreeScheduleDao() {
        return new FreeScheduleImplDAO();
    }

    @Override
    public PlanDao getPlanDao() {
        return new PlanImplDAO();
    }
}
