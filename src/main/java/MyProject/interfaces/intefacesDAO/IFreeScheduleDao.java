package MyProject.interfaces.intefacesDAO;

import MyProject.interfaces.intefacesDAO.overalInterfacesDAO.IAddition;
import MyProject.interfaces.intefacesDAO.overalInterfacesDAO.ICommon;
import MyProject.interfaces.intefacesDAO.overalInterfacesDAO.ICreate;
import MyProject.interfaces.intefacesDAO.overalInterfacesDAO.IDelete;
import MyProject.entity.FreeSchedule;

public interface IFreeScheduleDao extends
        ICommon<FreeSchedule>, IAddition<FreeSchedule>, IDelete, ICreate {
}