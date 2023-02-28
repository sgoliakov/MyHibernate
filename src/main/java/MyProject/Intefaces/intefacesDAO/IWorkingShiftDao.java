package MyProject.Intefaces.intefacesDAO;

import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.ICommon;
import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.ICreate;
import MyProject.entity.WorkingShift;

import java.time.LocalTime;

public interface IWorkingShiftDao extends ICommon<WorkingShift>, ICreate {
    void update(int id, LocalTime start, LocalTime end);
}