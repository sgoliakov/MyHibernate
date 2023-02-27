package MyProject.Intefaces.intefacesDAO;

import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.CommonDAO;
import MyProject.entity.WorkingShift;

import java.time.LocalTime;

public interface WorkingShiftDao extends CommonDAO<WorkingShift> {
    void createAll();

    void update(int id, LocalTime start, LocalTime end);
}
