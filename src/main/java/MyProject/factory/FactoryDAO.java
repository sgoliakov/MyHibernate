package MyProject.factory;

import MyProject.Intefaces.intefacesDAO.*;
import MyProject.entityHelper.*;

public class FactoryDAO implements MyDAOFactory {

    private static MyDAOFactory factory;

    private FactoryDAO() {

    }

    public static synchronized MyDAOFactory getFactory(){
        if (factory == null){
            factory = new FactoryDAO();
        }
        return factory;

    }


    @Override
    public EmployeeDao getEmployeeDao() {
        return new EmployeeHelper();
    }

    @Override
    public WorkDaysDao getWorkDaysDao() {
        return new WorkDaysHelper();
    }

    @Override
    public WorkingShiftDao getWorkingShiftDao() {
        return new WorkingShiftHelper();
    }

    @Override
    public ScheduleDao getScheduleDao() {
        return new ScheduleHelper();
    }

    @Override
    public FreeScheduleDao getFreeScheduleDao() {
        return new FreeScheduleHelper();
    }

    @Override
    public PlanDao getPlanDao() {
        return new PlanHelper();
    }


}
