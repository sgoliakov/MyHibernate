package MyProject.Intefaces.intefacesDAO;

import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.IAddition;
import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.ICommon;
import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.ICreate;
import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.IDelete;
import MyProject.entity.FreeSchedule;

public interface IFreeScheduleDao extends
        ICommon<FreeSchedule>, IAddition<FreeSchedule>, IDelete, ICreate {
}