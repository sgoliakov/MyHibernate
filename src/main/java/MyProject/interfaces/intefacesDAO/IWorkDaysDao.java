package MyProject.interfaces.intefacesDAO;

import MyProject.interfaces.intefacesDAO.overalInterfacesDAO.ICommon;
import MyProject.interfaces.intefacesDAO.overalInterfacesDAO.IDelete;
import MyProject.entity.WorkDays;

import java.time.LocalDate;

public interface IWorkDaysDao extends ICommon<WorkDays>, IDelete {
    void createFromDate(LocalDate date);
}