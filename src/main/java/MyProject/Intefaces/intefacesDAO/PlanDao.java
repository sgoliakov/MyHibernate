package MyProject.Intefaces.intefacesDAO;

import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.CommonDAO;
import MyProject.entity.Plan;

public interface PlanDao extends CommonDAO<Plan> {
    void createPlan(WorkDaysDao workDaysDao, WorkingShiftDao workingShiftDao);
}