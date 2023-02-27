package MyProject.Intefaces.intefacesDAO;

import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.CommonDAO;
import MyProject.entity.Employee;
import MyProject.entity.Schedule;
import MyProject.entity.WorkDays;
import MyProject.entity.wrapperEntity.WrapperSchedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleDao extends CommonDAO<Schedule> {
    Optional<List<WrapperSchedule>> getWrapperScheduleById(int id);

    void add(Schedule schedule);

    int deleteByEmployeeDate(Employee emp, WorkDays day);
}
