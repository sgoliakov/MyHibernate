package MyProject.Intefaces.intefacesDAO;

import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.ICommon;
import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.IDelete;
import MyProject.entity.WorkDays;

import java.time.LocalDate;

public interface IWorkDaysDao extends ICommon<WorkDays>, IDelete {
    void createFromDate(LocalDate date);
}