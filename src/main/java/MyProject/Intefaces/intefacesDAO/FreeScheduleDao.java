package MyProject.Intefaces.intefacesDAO;

import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.CommonDAO;
import MyProject.entity.FreeSchedule;
import MyProject.entity.Plan;

public interface FreeScheduleDao extends CommonDAO<FreeSchedule> {
    void add(Plan p);

    void createAll(WorkDaysDao workDaysDao, WorkingShiftDao workingShiftDao);

    void deleteByID(int id);
}