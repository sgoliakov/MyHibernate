package MyProject.Intefaces.intefacesDAO;

import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.CommonDAO;
import MyProject.entity.WorkDays;

import java.time.LocalDate;

public interface WorkDaysDao extends CommonDAO<WorkDays> {
    void createFromDate(LocalDate date);

    void deleteByID(int id);
}