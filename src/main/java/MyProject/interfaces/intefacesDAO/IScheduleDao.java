package MyProject.interfaces.intefacesDAO;

import MyProject.interfaces.intefacesDAO.overalInterfacesDAO.IAddition;
import MyProject.interfaces.intefacesDAO.overalInterfacesDAO.ICommon;
import MyProject.entity.Employee;
import MyProject.entity.Schedule;
import MyProject.entity.WorkDays;
import MyProject.entity.wrapperEntity.WrapperSchedule;

import java.util.List;
import java.util.Optional;

public interface IScheduleDao extends ICommon<Schedule>, IAddition<Schedule> {
    Optional<List<WrapperSchedule>> getWrapperScheduleById(int id);

    int deleteByEmployeeDate(Employee emp, WorkDays day);
}